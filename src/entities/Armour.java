package entities;

public class Armour extends Item
{
	private int defense;
	private int blockChance;
	private int absorbChance;
	
	public Armour(int inBlockChance, int inAbsorbChance, int inDefense, String inName, Character inCha, int inRow, int inColumn, int inLevel)
	{
		super(inCha, inRow, inColumn, inName, inLevel);
		defense = inDefense;
		blockChance = inBlockChance;
		absorbChance = inAbsorbChance;
	}
	
	public int getDefense()
	{
		return defense;
	}
	
	public int getBlockChance()
	{
		return blockChance;
	}
	
	public int getAbsorbChance()
	{
		return absorbChance;
	}
}
