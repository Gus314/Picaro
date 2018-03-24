package entities.factories;

import entities.Armour;

public class ArmourFactory
{
	private int defense;
	private int blockChance;
	private int absorbChance;
	private String name;
	private int level;
	
	public ArmourFactory(int inBlockChance, int inAbsorbChance, int inDefense, String inName, int inLevel)
	{
		blockChance = inBlockChance;
		absorbChance = inAbsorbChance;
		defense = inDefense;
		name = inName;
		level = inLevel;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public Armour construct(int inRow, int inColumn)
	{
		return new Armour(blockChance, absorbChance, defense, name, 'a', inRow, inColumn, level);
	}
}
