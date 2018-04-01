package entities.equipment;

import entities.Entity;

public abstract class Item extends Entity
{
	private String name;
	private int minLevel;
	private int maxLevel;
	
	public Item(Character inCha, int inRow, int inColumn, String inName, int inMinLevel, int inMaxLevel)
	{	
		super(inCha, inRow, inColumn);
		name = inName;
		minLevel = inMinLevel;
		maxLevel = inMaxLevel;
	};

	public int getMinLevel(){return minLevel;}

	public int getMaxLevel(){return maxLevel;}

	public String getName()
	{
		return name;
	}

	public boolean blocksLineOfSight(){ return false;}

	public int getLevel;
}
