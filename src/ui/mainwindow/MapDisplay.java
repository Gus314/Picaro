package ui.mainwindow;

import control.Coordinate;
import control.Grave;
import control.Map;
import entities.*;
import entities.creatures.Creature;
import entities.creatures.Monster;
import entities.creatures.Player;
import entities.equipment.Item;
import entities.furniture.Furniture;
import enums.Faction;
import skills.AreaSkill;
import skills.FloorSkill;
import skills.Skill;
import skills.TargetSkill;
import enums.MapDisplayMode;
import ui.IRefreshable;

import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

public class MapDisplay extends JPanel
{
	public class MapMouseListener implements MouseInputListener
	{
		private MapDisplay mapDisplay;

		public MapMouseListener(MapDisplay inMapDisplay)
		{
			mapDisplay = inMapDisplay;
		}

		private Coordinate getMouseCoordinate(MouseEvent e)
		{
			JPanel mapPanel = mapDisplay.getMapPanel();
			double height = mapPanel.getHeight();
			double width = mapPanel.getWidth();
			double rows = mapDisplay.getMap().getRows();
			double columns = mapDisplay.getMap().getColumns();
			double currentHeight = e.getY();
			double currentWidth = e.getX();
			double heightRatio = currentHeight / height;
			double widthRatio = currentWidth / width;
			int row = (int) Math.round(rows * heightRatio);
			int column = (int) Math.round(columns * widthRatio);

			return new Coordinate(row, column);
		}

		@Override
		public void mouseClicked(MouseEvent e)
		{
			// Button 3 should reset mode.
			if(e.getButton() == MouseEvent.BUTTON3)
			{
				highlights.clear();
				examineLabel.setText("");
				refresh();
				mapDisplay.changeMode(MapDisplayMode.NORMAL);
				return;
			}

			if(mapDisplay.getMode() == MapDisplayMode.ATTACK)
			{
				Coordinate coord = getMouseCoordinate(e);
				List<Entity> here = map.atPosition(coord.getRow(), coord.getColumn());
				if(Entity.containsMonster(here))
				{
					Monster monster  =Entity.getMonster(here);

                   if(map.isInLineOfSight(player, monster, player.getRange()))
				   {
				      boolean killed = player.attack(monster);
				      if(killed)
					  {
					  	map.removeEntity(monster);
					  }
					   mapDisplay.changeMode(MapDisplayMode.NORMAL);
				   }
				   map.takeTurns();
                   refresh();
				}
			}
			else if(mapDisplay.getMode() == MapDisplayMode.TARGET)
			{
				Coordinate coord = getMouseCoordinate(e);
				List<Entity> here = map.atPosition(coord.getRow(), coord.getColumn());
				if (Entity.containsMonster(here))
				{
					Monster monster = Entity.getMonster(here);
					if (map.isInLineOfSight(player, monster, ((TargetSkill) selectedSkill).getRange()))
					{
						messages.addMessage(((TargetSkill) selectedSkill).action(player, monster));
						mapDisplay.changeMode(MapDisplayMode.NORMAL);
						map.takeTurns();
						refresh();
					}
				}
			}
			else if(mapDisplay.getMode() == MapDisplayMode.AREA)
			{
				Coordinate coord = getMouseCoordinate(e);
                AreaSkill areaSkill = (AreaSkill)selectedSkill;
                int range = areaSkill.getRange();
                int radius = areaSkill.getRadius();

                ArrayList<Creature> monsters = new ArrayList<Creature>();

                Entity centralEntity =  map.atPosition(coord.getRow(), coord.getColumn()).get(0);
                boolean floorTargeted = false;
				if(centralEntity == null)
				{
					// Target the floor.
					centralEntity = new TargetPoint(coord.getRow(), coord.getColumn());
					map.addEntry(centralEntity);
					floorTargeted = true;
				}

                boolean centreVisible = map.isInLineOfSight(player, centralEntity, range);

				if(floorTargeted)
				{
					map.removeEntity(centralEntity);
				}

                if(!centreVisible)
				{
					return;
				}

				for(int i = -radius; i < radius; i++)
				{
					for(int j = -radius; j < radius; j++)
					{
						Monster monster = Entity.getMonster(map.atPosition(coord.getRow()+i, coord.getColumn()+j));
						if(monster != null)
						{
							monsters.add(monster);
						}
					}
				}

				if(monsters.size() == 0)
				{
					return;
				}

				messages.addMessage(areaSkill.action(player, monsters));
				for(Creature monster: monsters)
				{
					if (monster.getLife() <= 0)
					{
						player.killed((Monster)monster);
						map.removeEntity(monster);
					}
				}
				mapDisplay.changeMode(MapDisplayMode.NORMAL);
				map.takeTurns();
				refresh();
			}
			else if(mapDisplay.getMode() == MapDisplayMode.EXAMINE)
			{
				// Do nothing.
			}
			else if(mapDisplay.getMode() == MapDisplayMode.FLOOR)
			{
				Coordinate coord = getMouseCoordinate(e);
				List<Entity> here = map.atPosition(coord.getRow(), coord.getColumn(), true);
				if((here.size() == 1) && (here.get(0) instanceof Floor))
				{
					Floor floor = (Floor) here.get(0);
					FloorSkill floorSkill = (FloorSkill) selectedSkill;
					Collection<Entity> additions = new ArrayList<Entity>();

					messages.addMessage(floorSkill.action(player, floor, additions));
					map.addEntries(additions);

					mapDisplay.changeMode(MapDisplayMode.NORMAL);
					map.takeTurns();
					refresh();
				}
			}
			else
			{
				System.out.println("MapDisplay::mouseClicked - unexpected actions display mode.");
				// TODO: Throw exception.
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mouseDragged(MouseEvent e) {}

		@Override
		public void mouseMoved(MouseEvent e)
		{
			if(mapDisplay.getMode() == MapDisplayMode.ATTACK || mapDisplay.getMode() == MapDisplayMode.TARGET)
			{
				TargetSkill targetSkill = (TargetSkill) selectedSkill;
				int range = (mapDisplay.getMode() == MapDisplayMode.ATTACK) ? player.getRange() : targetSkill.getRange();
				Coordinate coord = getMouseCoordinate(e);
				List<Entity> here = map.atPosition(coord.getRow(), coord.getColumn());
				Entity ent = (here.size() > 0) ? here.get(0) : null;
				if(ent != null)
				{
					highlights.clear();
					refresh(); // Inefficient.
					if(map.isInLineOfSight(player, ent, range))
					{
						addHighlight(coord, Color.GREEN);
					}
					else
					{
						addHighlight(coord, Color.RED);
					}
				}
			}
			else if(mapDisplay.getMode() == MapDisplayMode.AREA)
			{
				highlights.clear();
				refresh(); // Inefficient.

				AreaSkill areaSkill = (AreaSkill) selectedSkill;
				int range = areaSkill.getRange();
				int radius = areaSkill.getRadius();
				Coordinate coord = getMouseCoordinate(e);

                boolean floorTargeted = false;
				List<Entity> centreEntities = map.atPosition(coord.getRow(), coord.getColumn());
				Entity centreEntity = null;
				if(centreEntities.size() == 0)
				{
					// Use the floor as the central target.
					centreEntity = new TargetPoint(coord.getRow(), coord.getColumn());
					map.addEntry(centreEntity);
					floorTargeted = true;
				}
				else
				{
					centreEntity = centreEntities.get(0);
				}

				boolean centreInRange = map.isInLineOfSight(player, centreEntity, range);
                if(floorTargeted)
				{
					map.removeEntity(centreEntity);
				}

				for(int i = -radius; i < radius; i++)
				{
					for(int j = -radius; j < radius; j++)
					{
						Coordinate adjustedCoord = new Coordinate(coord.getRow() + i, coord.getColumn() + j);
						List<Entity> entities = map.atPosition(adjustedCoord.getRow(), adjustedCoord.getColumn());

						if(entities.size() != 0)
						{
							addHighlight(adjustedCoord, centreInRange ? Color.GREEN : Color.RED);
						}
					}
				}
			}
			else if(mapDisplay.getMode() == MapDisplayMode.EXAMINE)
			{
				highlights.clear();
				refresh(); // Inefficient.
				Coordinate coord = getMouseCoordinate(e);
				List<Entity> here = map.atPosition(coord.getRow(), coord.getColumn());
				if(here.size() > 0)
				{
					Entity ent = here.get(0);
					boolean permanentlyVisible = map.getPermanentlyVisible().contains(ent);
					final int lineOfSight = 8; // TODO: Refactor.
					boolean currentlyVisible = map.isInLineOfSight(player, ent, lineOfSight);

					if(permanentlyVisible || currentlyVisible)
					{
						addHighlight(coord, Color.YELLOW);
						mapDisplay.setExamineText(ent.getName());
					}
				}
			}
			else if(mapDisplay.getMode() == MapDisplayMode.FLOOR)
			{
				highlights.clear();
				refresh(); // Inefficient.
				Coordinate coord = getMouseCoordinate(e);
				List<Entity> here = map.atPosition(coord.getRow(), coord.getColumn(), true);

				if((here.size() == 1) && (here.get(0) instanceof Floor))
				{
					// Use the floor as the target.
					Entity floorEntity = new TargetPoint(coord.getRow(), coord.getColumn());
					map.addEntry(floorEntity);
					final int lineOfSight = 8; // TODO: Refactor.
					boolean inRange = map.isInLineOfSight(player, floorEntity, lineOfSight);
					map.removeEntity(floorEntity);
					if(inRange)
					{
						// Avoid adding red highlights as they may expose information, e.g. floor position.
						addHighlight(coord, Color.GREEN);
					}
				}
			}
		}
	}

	private Map map;
	private Vector<JLabel> cells;
	private Player player;
	private Stats stats;
	private Status status;
	private HashMap<Coordinate, Color> highlights;
	private MapDisplayMode mode;
	private MapMouseListener mapMouseListener;
	private Messages messages;
	private Skill selectedSkill;
	private JLabel examineLabel;
    private JPanel mapPanel;
    private static final String blank = " ";
    private Collection<IRefreshable> refreshables;
    public JPanel getMapPanel(){return mapPanel;}

	public void setSelectedSkill(Skill inSelectedSkill)
	{
		selectedSkill = inSelectedSkill;
	}

	public MapDisplayMode getMode()
	{
		return mode;
	}

	public void addHighlight(Coordinate coordinate, Color color)
	{
	   highlights.put(coordinate, color);
	   cells.get(coordinate.getRow()*map.getColumns() + coordinate.getColumn()).setBackground(color);
	}

	public void setExamineText(String text)
	{
		examineLabel.setText(text);
	}

	public void setMap(Map inMap)
	{
		map = inMap;
		mapPanel.removeAll();
		cells = new Vector<JLabel>();
		highlights = new HashMap<Coordinate, Color>();
		initialise();
		map.addEntry(player);
		player.setMap(map);
		refresh();
	}
	
	public MapDisplay(Map inMap, Player inPlayer, Stats inStats, Status inStatus, Messages inMessages)
	{
		super();

		mode = MapDisplayMode.NORMAL;
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		mapPanel = new JPanel();
		GridBagLayout grid = new GridBagLayout();
		mapPanel.setLayout(grid);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty = 50;
		constraints.gridy = 0;
		add(mapPanel, constraints);
		map = inMap;
		cells = new Vector<JLabel>();
		initialise();
		player = inPlayer;
		map.addEntry(player);
		player.setMap(map);
		stats = inStats;
		status = inStatus;
		mapMouseListener = new MapMouseListener(this);
		mapPanel.addMouseListener(mapMouseListener);
		mapPanel.addMouseMotionListener(mapMouseListener);
		highlights = new HashMap<>();
		messages = inMessages;
		examineLabel = new JLabel(" ");
		examineLabel.setSize(examineLabel.getPreferredSize());
		constraints.weighty = 1;
		constraints.gridy = 1;
		add(examineLabel, constraints);
		refreshables = new Vector<IRefreshable>();
		refreshables.add(stats);
		refreshables.add(status);
		refresh();
	}
	
	public Map getMap()
	{
		return map;
	}

	public void addRefreshable(IRefreshable refreshable)
	{
		refreshables.add(refreshable);
	}

	private void initialise()
	{
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weighty = 1;
		constraints.weightx = 1;

		JLabel sizeLabel = new JLabel("@");
		Dimension desiredSize = sizeLabel.getPreferredSize(); // Allow labels to all have fixed size.

		for(int i = 0; i < map.getRows(); i++)
		{
			for(int j = 0; j < map.getColumns(); j++)
			{
				JLabel l = new JLabel(blank);
				l.setBackground(Color.BLACK);
				l.setForeground(Color.WHITE);
				l.setOpaque(true);
				l.setMaximumSize(desiredSize);
				l.setMinimumSize(desiredSize);
				l.setPreferredSize(desiredSize);
				cells.add(l);

				constraints.gridx = j;
				constraints.gridy = i;
				mapPanel.add(l, constraints);
			}
		}
	}

	public void changeMode(MapDisplayMode inMapDisplayMode)
	{
		getParent().getParent().requestFocus();
		mapPanel.removeMouseListener(mapMouseListener);
		mapPanel.removeMouseMotionListener(mapMouseListener);
		highlights.clear();
		mode = inMapDisplayMode;
		refresh();

		switch(inMapDisplayMode)
		{
			case ATTACK:
			case TARGET:
			case AREA:
			case EXAMINE:
			case FLOOR:
			{
				mapPanel.addMouseMotionListener(mapMouseListener);
				mapPanel.addMouseListener(mapMouseListener);
		        break;
			}
			case NORMAL:
			{
				break;
			}
			default:
			{
				// TODO: Throw an exception.
				System.out.println("MapDisplay::changeMode - unexpected mode.");
				break;
			}
		}
	}

	private static Color determineForegroundColour(Entity ent)
	{
		if(ent instanceof Floor || ent instanceof  Wall || ent instanceof DownStairs || ent instanceof UpStairs)
		{
			return Color.WHITE;
		}
		else if(ent instanceof Monster)
		{
			boolean foe = ((Monster) ent).getFaction().equals(Faction.FOE);
			return foe ? Color.RED : Color.GREEN;
		}
		else if(ent instanceof  Player)
		{
			return Color.GREEN;
		}
		else if(ent instanceof Furniture)
		{
			return Color.CYAN;
		}
		else if(ent instanceof Item)
		{
			return Color.YELLOW;
		}
		else
		{
			System.out.println("MapDisplay::determineForegroundColor() - unexpected entity type.");
			return Color.WHITE;
		}
	}


	public void refresh()
	{
		// Death takes priority over winning the game.
		Optional<Grave> grave = map.obtainGrave();
		if(grave.isPresent())
		{
			MainWindow mainWindow = (MainWindow) getParent().getParent();
			mainWindow.gameOver(grave.get());
		}
        else if(map.getPlayer().getWonGame())
		{
			player.setCauseOfDeath("Won the game!");
			player.createGrave();
			MainWindow mainWindow = (MainWindow) getParent().getParent();
			mainWindow.gameOver(grave.get());
		}

		int numEntries = map.getRows() * map.getColumns();
		for(int i = 0; i < numEntries; i++)
		{
			cells.get(i).setText(blank);
			cells.get(i).setBackground(Color.BLACK); // Clear highlights.
		}

		List<Entity> entries = map.getMapEntries();
		HashSet<Entity> visibleEntries = map.lineOfSight(player, player.getSightRadius(), true);
        HashSet<Entity> permanentlyVisible = map.getPermanentlyVisible();


		for(Entity ent: entries)
		{
			int row = ent.getRow();
			int column = ent.getColumn();
			int position = row*map.getColumns() + column;

			if(! (visibleEntries.contains(ent) || permanentlyVisible.contains(ent)) )
			{
				// Do not draw invisible entries but also do not over-write other entities already drawn, e.g. floor.
				continue;
			}

			// TODO : Ensure monsters take priority over items for display.
			// TODO: Add layer system.
			if(row < 0 || column < 0)
			{
				entries.remove(ent);
			}
			else if(cells.get(position).getText().equals(blank)|| cells.get(position).getText().contains((new Floor(0, 0)).getChar().toString()))
			{
				cells.get(position).setForeground(determineForegroundColour(ent));
				cells.get(position).setText(ent.getChar().toString());
			}

			if(highlights.containsKey(new Coordinate(row, column)))
			{
				cells.get(position).setBackground(highlights.get(position));
			}
		}

		for(IRefreshable refreshable: refreshables)
		{
			refreshable.refresh();
		}
	}
}
