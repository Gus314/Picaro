package entities;

import entities.Item;

public class Weapon extends Item
{
	private int minDamage;
	private int maxDamage;
	private int critChance;
	
	public Weapon(int inMinDamage, int inMaxDamage, int inCritChance, String inName, Character inCha, int inRow, int inColumn, int inLevel)
	{
		super(inCha, inRow, inColumn, inName, inLevel);
		minDamage = inMinDamage;
		maxDamage = inMaxDamage;
		critChance = inCritChance;
	}
	
	public int getMinDamage()
	{
		return minDamage;
	}
	
	public int getMaxDamage()
	{
		return maxDamage;
	}
	
	public int getCritChance()
	{
		return critChance;
	}
}
