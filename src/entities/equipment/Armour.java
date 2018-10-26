package entities.equipment;

import enums.ArmourModificationType;
import enums.ArmourType;

public class Armour extends Item
{
	private int defense;
	private int magicDefense;
	private int blockChance;
	private int absorbChance;
	private ArmourType armourType;
	
	public Armour(ArmourType inArmourType, int inBlockChance, int inAbsorbChance, int inDefense, int inMagicDefense, String inName, Character inCha, int inRow, int inColumn, int inMinLevel, int inMaxLevel)
	{
		super(inCha, inRow, inColumn, inName, inMinLevel, inMaxLevel);
		defense = inDefense;
		magicDefense = inMagicDefense;
		blockChance = inBlockChance;
		absorbChance = inAbsorbChance;
		armourType = inArmourType;
	}

	public int changeStat(ArmourModificationType type, int intensity)
	{
		switch(type)
		{
			case ABSORBCHANCE:
			{
				int oldAbsorbChance = absorbChance;
				absorbChance += intensity;
				if(absorbChance < 0)
				{
					absorbChance = 0;
				}
				if(absorbChance > 100)
				{
					absorbChance = 100;
				}
				return absorbChance - oldAbsorbChance;
			}
			case BLOCKCHANCE:
			{
				int oldBlockChance = blockChance;
				blockChance += intensity;
				if(blockChance < 0)
				{
					blockChance = 0;
				}
				if(blockChance > 100)
				{
					blockChance = 100;
				}
				return blockChance - oldBlockChance;
			}
			case DEFENSE:
			{
				int oldDefense = defense;
				defense += intensity;
				if(defense < 0)
				{
					defense = 0;
				}
				return defense - oldDefense;
			}
			case MAGICDEFENSE:
			{
				int oldMagicDefense = magicDefense;
				magicDefense += intensity;
				if(magicDefense < 0)
				{
					magicDefense = 0;
				}
				return magicDefense - oldMagicDefense;
			}
			default:
			{
				System.out.println("Armour::changeStat() - unexpected type.");
				return 0;
			}
		}
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
