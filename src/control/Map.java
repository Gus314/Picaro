package control;

import entities.Creature;
import entities.Entity;
import entities.Floor;

import java.util.ArrayList;

public class Map 
{
	private ArrayList<Entity> mapEntries;
	private int rows;
	private int columns;
	
	public Map(int inRows, int inColumns)
	{
		rows = inRows;
		columns = inColumns;
		mapEntries = new ArrayList<Entity>();
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
	
	public void removeEntity(Entity ent)
	{
		mapEntries.remove(ent);
	}
	
	public void takeTurns()
	{
		for(Entity ent: mapEntries)
			if(ent instanceof Creature)
				((Creature)ent).takeTurn();
	}
}
