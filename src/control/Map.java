package control;

import entities.*;
import enums.Quadrant;

import java.util.ArrayList;
import java.util.HashSet;

public class Map 
{
	private ArrayList<Entity> mapEntries;
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
	
	public ArrayList<Entity> getMapEntries()
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

	public Entity atPosition(int row, int column)
	{
		for(Entity ent: mapEntries)
		{
			if(ent.getRow() == row && ent.getColumn() == column && (!(ent instanceof Floor)))
				return ent;
		}
		return null;
	}

	public boolean isInLineOfSight(Entity source, Entity target, int radius)
	{
		return lineOfSight(source, radius).contains(target);
	}

	public Entity find(Coordinate coordinate)
	{
		for(Entity ent: mapEntries)
		{
			if(ent.getRow() == coordinate.getRow() && ent.getColumn() == coordinate.getColumn())
			{
				return ent;
			}
		}
		return null; // Todo: Throw exception.
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

				int rowShift = (int)Math.floor(Math.sin(adjustedAngleRads) * (double)distance);
				int columnShift = (int)Math.floor(Math.cos(adjustedAngleRads) * (double)distance);

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

				Entity here = atPosition(movedRow, movedColumn);
				if( (here != null) && here.blocksLineOfSight())
				{
					result.add(here);
					if((source instanceof Player) && isPermanentlyVisible(here))
					{
						permanentlyVisible.add(here);
					}
					break;
				}
			}
		}

		return result;
	}

	private boolean isPermanentlyVisible(Entity ent)
	{
	   return(ent instanceof Wall || ent instanceof Stairs);
	}

	public void removeEntity(Entity ent)
	{
		mapEntries.remove(ent);
	}
	
	public void takeTurns()
	{
		for(Entity ent: mapEntries)
			if(ent instanceof Monster)
				((Monster)ent).takeTurn();
	}
}
