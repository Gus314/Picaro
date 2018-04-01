package entities.equipment;

import enums.ArmourType;

public class Armour extends Item
{
	private int defense;
	private int magicDefense;
	private int blockChance;
	private int absorbChance;
	private ArmourType armourType;
	
	public Armour(ArmourType inArmourType, int inBlockChance, int inAbsorbChance, int inDefense, int inMagicDefense, String inName, Character inCha, int inRow, int inColumn, int inLevel)
	{
		super(inCha, inRow, inColumn, inName, inLevel);
		defense = inDefense;
		magicDefense = inMagicDefense;
		blockChance = inBlockChance;
		absorbChance = inAbsorbChance;
		armourType = inArmourType;
	}
	
	public int getDefense()
	{
		return defense;
	}

	public int getMagicDefense(){ return magicDefense;}

	public int getBlockChance()
	{
		return blockChance;
	}
	
	public int getAbsorbChance()
	{
		return absorbChance;
	}

	public ArmourType getArmourType(){return armourType;}
}
