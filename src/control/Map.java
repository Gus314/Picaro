package control;

import entities.*;
import entities.furniture.Furniture;
import enums.Quadrant;

import java.io.Serializable;
import java.util.*;

public class Map implements Serializable
{
	private List<Entity> mapEntries;
	private HashSet<Entity> permanentlyVisible;
	private int rows;
	private int columns;
	
	public Map(int inRows, int inColumns)
	{
		rows = inRows;
		columns = inColumns;
		mapEntries = new ArrayList<>();
		permanentlyVisible = new HashSet<>();
	}

	public int getRows()
	{
		return rows;
	}
	
	public int getColumns()
	{
		return columns;
	}
	
	public List<Entity> getMapEntries()
	{
		return mapEntries;
	}

	public HashSet<Entity> getPermanentlyVisible()
	{
		return permanentlyVisible;
	}

	public void addEntry(Entity ent)
	{
		mapEntries.add(ent);
	}

	public void addEntries(Collection<Entity> ents)
	{
		mapEntries.addAll(ents);
	}

	public List<Entity> atPosition(int row, int column, boolean includeFloor)
	{
		List<Entity> result = new ArrayList<Entity>();
		for(Entity ent: mapEntries)
		{
			boolean isFloor = ent instanceof Floor;
			boolean floorStatusValid = !(includeFloor ^ isFloor);
			if(ent.getRow() == row && ent.getColumn() == column && floorStatusValid)
			{
				result.add(ent);
			};
		}
		return result;
	}

	public List<Entity> atPosition(int row, int column)
	{
		return atPosition(row, column, false);
	}

	public boolean isInLineOfSight(Entity source, Entity target, int radius)
	{
		return lineOfSight(source, radius).contains(target);
	}

	public Player getPlayer()
    {
         for(Entity entity: mapEntries)
         {
             if(entity instanceof Player)
             {
                 return (Player) entity;
             }
         }

         System.out.println("Map::getPlayer - failed to find player.");
         return null;
    }

	public HashSet<Entity> lineOfSight(Entity source, int radius)
	{
		HashSet<Entity> result = new HashSet<>();

		int initialRow = source.getRow();
		int initialColumn = source.getColumn();

		final double pi = 3.1415926535897;

		// The entity is visible to itself.
		result.add(source);

		for(int angle = 0; angle < 360; angle++)
		{
			boolean blocked = false;

			double angleRads = (angle / 180.0) * pi;
			int row = initialRow;
			int column = initialColumn;
			Quadrant quadrant = (angle < 90) ? Quadrant.Q1 :
					            (angle < 180) ? Quadrant.Q2 :
								(angle < 270) ? Quadrant.Q3 :
										        Quadrant.Q4;

			// The entity is at distance 0.
			for(int distance = 1; distance <= radius; distance++)
			{
				double adjustedAngleRads = (quadrant == Quadrant.Q1) ? angleRads :
						(quadrant == Quadrant.Q2) ? angleRads - (pi/2.0) :
								(quadrant == Quadrant.Q3) ? angleRads - pi :
										angleRads - ((3.0 * pi) / 2.0);

				int rowShift = (int)Math.ceil(Math.sin(adjustedAngleRads) * (double)distance);
				int columnShift = (int)Math.ceil(Math.cos(adjustedAngleRads) * (double)distance);

				if(rowShift == 0 && columnShift == 0)
				{
					continue; // If rounding causes the source to be chosen, bypass it.
				}

				int movedRow = (quadrant == Quadrant.Q1) ? row - rowShift :
						       (quadrant == Quadrant.Q2) ? row - columnShift:
							   (quadrant == Quadrant.Q3) ? row + rowShift:
											               row + columnShift;


				int movedColumn = (quadrant == Quadrant.Q1) ? column + columnShift :
						          (quadrant == Quadrant.Q2) ? column - rowShift :
								  (quadrant == Quadrant.Q3) ? column - columnShift :
										                      column + rowShift;

				List<Entity> here = atPosition(movedRow, movedColumn);
				if( (here != null))
				{
					result.addAll(here);
					for(Entity ent: here)
					{
						if((source instanceof Player) && isPermanentlyVisible(ent))
						{
							permanentlyVisible.add(ent);
						}
						if(ent.blocksLineOfSight())
						{
							blocked = true;
						}
					}
				}

				if(blocked)
				{
					break;
				}
			}
		}

		return result;
	}

	private boolean isPermanentlyVisible(Entity ent)
	{
	   return(ent instanceof Wall || ent instanceof DownStairs || ent instanceof UpStairs || ent instanceof Furniture);
	}

	public void removeEntity(Entity ent)
	{
		mapEntries.remove(ent);
	}

	private void processDeaths()
	{
		Collection<Monster> killed = new ArrayList<Monster>();
		// TODO: Make this more efficient, not requiring frequent calling.
		for(Entity ent: mapEntries)
		{
			if(ent instanceof Monster)
			{
				Monster monster = (Monster) ent;
				if(monster.getLife() <= 0)
				{
					killed.add(monster);
				}
			}
		}

		for(Monster monster: killed)
		{
			getPlayer().killed(monster);
			mapEntries.remove(monster);
		}

		if(getPlayer().getLife() <= 0)
		{
			if(getPlayer().getCauseOfDeath().length() == 0)
			{
				getPlayer().setCauseOfDeath("killed by the dungeon.");
			}
		}
	}

	public void takeTurns()
	{
		processDeaths();
		// Prevent concurrent modification.
		Collection<Entity> additions = new ArrayList<Entity>();

		for(Entity ent: mapEntries)
		{
			if ((ent instanceof Monster) && ((Monster) ent).getLife() > 0)
			{
				additions.addAll(((Monster) ent).takeTurn());
			}
			if((ent instanceof Creature) && ((Creature)ent).getLife()>0)
			{
				((Creature) ent).progressStatusEffects();
			}
		}

		processDeaths();
		mapEntries.addAll(additions);
	}
}
