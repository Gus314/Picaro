package ui;

import control.Coordinate;
import control.Map;
import control.TurnHandler;
import entities.Creature;
import entities.Entity;
import entities.Floor;
import entities.Player;
import enums.MapDisplayMode;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;
import java.util.ArrayList;
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
			double height = mapDisplay.getHeight();
			double width = mapDisplay.getWidth();
			double rows = mapDisplay.getMap().getRows();
			double columns = mapDisplay.getMap().getColumns();
			double currentHeight = e.getY();
			double currentWidth = e.getX();
			double heightRatio = currentHeight / height;
			double widthRatio = currentWidth / width;
			int row = (int) Math.floor(rows * heightRatio);
			int column = (int) Math.floor(columns * widthRatio);
			return new Coordinate(row, column);
		}

		@Override
		public void mouseClicked(MouseEvent e)
		{
			// Button 2 should reset mode.
			if(e.getButton() == MouseEvent.BUTTON2)
			{
				highlights.clear();
				refresh();
				mapDisplay.changeMode(MapDisplayMode.NORMAL);
				return;
			}

			if(mapDisplay.getMode() == MapDisplayMode.ATTACK)
			{
				Coordinate coord = getMouseCoordinate(e);
				Entity ent = map.atPosition(coord.getRow(), coord.getColumn());
				if(ent instanceof Creature)
				{
                   if(map.isInLineOfSight(player, ent, player.getRange()))
				   {
				      boolean killed = player.attack((Creature)ent, messages);
				      if(killed)
					  {
					  	map.removeEntity(ent);
					  }
				   }
				   map.takeTurns();
                   refresh();
				}
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
			if(mapDisplay.getMode() == MapDisplayMode.ATTACK)
			{
				Coordinate coord = getMouseCoordinate(e);
				Entity ent = map.atPosition(coord.getRow(), coord.getColumn());
				if(ent != null)
				{
					highlights.clear();
					refresh(); // Inefficient.
					if(map.isInLineOfSight(player, ent, player.getRange()))
					{
						addHighlight(coord, Color.GREEN);
					}
					else
					{
						addHighlight(coord, Color.RED);
					}
				}
			}
		}
	}

	private Map map;
	private Vector<JLabel> cells;
	private Player player;
	private Stats stats;
	private HashMap<Coordinate, Color> highlights;
	private MapDisplayMode mode;
	private MapMouseListener mapMouseListener;
	private Messages messages;

	public MapDisplayMode getMode()
	{
		return mode;
	}

	public void addHighlight(Coordinate coordinate, Color color)
	{
	   highlights.put(coordinate, color);
	   cells.get(coordinate.getRow()*map.getColumns() + coordinate.getColumn()).setForeground(color);
	   System.out.println(coordinate.getRow() + "," + coordinate.getColumn());

	}

	public void setMap(Map inMap)
	{
		map = inMap;
		this.removeAll();
		cells = new Vector<JLabel>();
		highlights = new HashMap<Coordinate, Color>();
		initialise();
		map.addEntry(player);
		refresh();
	}
	
	public MapDisplay(Map inMap, Player inPlayer, Stats inStats, Messages inMessages)
	{
		super();
		mode = MapDisplayMode.NORMAL;
		GridLayout grid = new GridLayout(inMap.getRows(), inMap.getColumns());
		this.setLayout(grid);
		map = inMap;
		cells = new Vector<JLabel>();
		initialise();
		player = inPlayer;
		map.addEntry(player);
		stats = inStats;
		mapMouseListener = new MapMouseListener(this);
		addMouseListener(mapMouseListener);
		addMouseMotionListener(mapMouseListener);
		highlights = new HashMap<>();
		messages = inMessages;
		refresh();
	}
	
	public Map getMap()
	{
		return map;
	}
	
	private void initialise()
	{
		int entries = map.getRows() * map.getColumns();
		for(int i = 0; i < entries; i++)
		{
			JLabel l = new JLabel(" ");
			cells.add(l);
			this.add(l);
		}
	}

	public void changeMode(MapDisplayMode inMapDisplayMode)
	{
		getParent().getParent().requestFocus();
		System.out.println("focusydo");
        removeMouseListener(mapMouseListener);
        removeMouseMotionListener(mapMouseListener);
		highlights.clear();
		mode = inMapDisplayMode;
		refresh();

		switch(inMapDisplayMode)
		{
			case ATTACK:
			{
				addMouseMotionListener(mapMouseListener);
				addMouseListener(mapMouseListener);
		        break;
			}
			case NORMAL:
			{
				break;
			}
			default:
			{
				// TODO: Throw an exception.
				break;
			}
		}
	}
	
	public void refresh()
	{
		int numEntries = map.getRows() * map.getColumns();
		for(int i = 0; i < numEntries; i++)
		{
			cells.get(i).setText(" ");
		}

		final int sightRadius = 8;
		ArrayList<Entity> entries = map.getMapEntries();
		HashSet<Entity> visibleEntries = map.lineOfSight(player, sightRadius);
        HashSet<Entity> permanentlyVisible = map.getPermanentlyVisible();

		for(Entity ent: entries)
		{
			int row = ent.getRow();
			int column = ent.getColumn();
			int position = row*map.getColumns() + column;

			if(! (visibleEntries.contains(ent) || permanentlyVisible.contains(ent)) )
			{
				// Do not draw invisible entries;
				cells.get(position).setText(" ");
				continue;
			}

			if(row < 0 || column < 0)
			{
				entries.remove(ent);
			}
			else if((!(ent instanceof Floor)) || cells.get(position).getText().equals(" "))
			{
				cells.get(position).setText(ent.getChar().toString());
			}

			if(highlights.containsKey(new Coordinate(row, column)))
			{
				cells.get(position).setForeground(highlights.get(position));
			}
			else
			{
				cells.get(position).setForeground(Color.BLACK);
			}
		}
		stats.refresh();
	}
}
