package entities.equipment;

import entities.Entity;

public abstract class Item extends Entity
{
	private int minLevel;
	private int maxLevel;
	
	public Item(Character inCha, int inRow, int inColumn, String inName, int inMinLevel, int inMaxLevel)
	{	
		super(inCha, inRow, inColumn, inName);
		minLevel = inMinLevel;
		maxLevel = inMaxLevel;
	};

	public int getMinLevel(){return minLevel;}

	public int getMaxLevel(){return maxLevel;}

	public boolean blocksLineOfSight(){ return false;}
}
