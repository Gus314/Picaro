package entities.equipment;

public class Weapon extends Item
{
	private int minDamage;
	private int maxDamage;
	private int critChance;
	private int intelligence;
	private int range;
	private int radius;

	public Weapon(int inMinDamage, int inMaxDamage, int inCritChance, int inIntelligence, String inName, Character inCha, int inRow, int inColumn, int inRange, int inMinLevel, int inMaxLevel)
	{
		super(inCha, inRow, inColumn, inName, inMinLevel, inMaxLevel);
		minDamage = inMinDamage;
		maxDamage = inMaxDamage;
		critChance = inCritChance;
		intelligence = inIntelligence;
		range = inRange;
	}

	public int getIntelligence(){return intelligence;}

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
}
