package entities.equipment.factories;

import entities.equipment.Armour;

public class ArmourFactory
{
	private int defense;
	private int magicDefense;
	private int blockChance;
	private int absorbChance;
	private String name;
	private int level;
	
	public ArmourFactory(int inBlockChance, int inAbsorbChance, int inDefense, int inMagicDefense, String inName, int inLevel)
	{
		blockChance = inBlockChance;
		absorbChance = inAbsorbChance;
		defense = inDefense;
		magicDefense = inMagicDefense;
		name = inName;
		level = inLevel;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public Armour construct(int inRow, int inColumn)
	{
		return new Armour(blockChance, absorbChance, defense, magicDefense, name, 'a', inRow, inColumn, level);
	}
}
