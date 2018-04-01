package entities.equipment.factories;

import entities.equipment.Armour;
import enums.ArmourType;

public class ArmourFactory
{
    private ArmourType armourType;
	private int defense;
	private int magicDefense;
	private int blockChance;
	private int absorbChance;
	private String name;
	private int level;
	
	public ArmourFactory(ArmourType inArmourType, int inBlockChance, int inAbsorbChance, int inDefense, int inMagicDefense, String inName, int inLevel)
	{
		armourType = inArmourType;
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
		return new Armour(armourType, blockChance, absorbChance, defense, magicDefense, name, 'a', inRow, inColumn, level);
	}
}
