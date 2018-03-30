package entities;

public class Weapon extends Item
{
	private int minDamage;
	private int maxDamage;
	private int critChance;
	private int range;
	private int radius;

	public Weapon(int inMinDamage, int inMaxDamage, int inCritChance, String inName, Character inCha, int inRow, int inColumn, int inLevel, int inRange, int inRadius)
	{
		super(inCha, inRow, inColumn, inName, inLevel);
		minDamage = inMinDamage;
		maxDamage = inMaxDamage;
		critChance = inCritChance;
		range = inRange;
		radius = inRadius;
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

	public int getRange(){ return range;}

	public int getRadius(){return radius;}
}
