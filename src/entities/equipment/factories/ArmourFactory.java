package entities.equipment.factories;

import entities.equipment.Armour;
import entities.factories.AbstractEntityFactory;
import enums.ArmourType;

public class ArmourFactory extends AbstractEntityFactory
{
    private ArmourType armourType;
	private int defense;
	private int magicDefense;
	private int blockChance;
	private int absorbChance;
	private String name;
	private int minLevel;
	private int maxLevel;
	
	public ArmourFactory(ArmourType inArmourType, int inBlockChance, int inAbsorbChance, int inDefense, int inMagicDefense, String inName, int inMinLevel, int inMaxLevel)
	{
		armourType = inArmourType;
		blockChance = inBlockChance;
		absorbChance = inAbsorbChance;
		defense = inDefense;
		magicDefense = inMagicDefense;
		name = inName;
		minLevel = inMinLevel;
		maxLevel = inMaxLevel;
	}

	public String getName(){return name;}
	
	public int getMinLevel(){return minLevel;}

	public int getMaxLevel(){return maxLevel;}
	
	public Armour construct(int inRow, int inColumn)
	{
		return new Armour(armourType, blockChance, absorbChance, defense, magicDefense, name, 'a', inRow, inColumn, minLevel, maxLevel);
	}
}
