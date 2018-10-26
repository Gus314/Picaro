package entities.equipment;

import enums.WeaponModificationType;
import sun.awt.windows.WEmbeddedFrame;

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

	public int changeStat(WeaponModificationType type, int intensity)
	{
		switch(type)
		{
			case CRITCHANCE:
			{
				int oldCritChance = critChance;
				critChance = critChance + intensity;
				if(critChance < 0)
				{
					critChance = 0;
				}
				if(critChance > 100)
				{
					critChance = 100;
				}
				return critChance - oldCritChance;
			}
			case DAMAGE:
			{
				int oldMinDamage = minDamage;
				minDamage = minDamage + intensity;
				maxDamage = maxDamage + intensity;
				if(minDamage < 0)
				{
					minDamage = 0;
				}
				if(maxDamage < 0)
				{
					maxDamage = 0;
				}
				return minDamage - oldMinDamage;
			}
			case INTELLIGENCE:
			{
				int oldIntelligence = intelligence;
				intelligence = intelligence + intensity;
				if(intelligence < 0)
				{
					intelligence = 0;
				}
				return intelligence - oldIntelligence;
			}
			default:
			{
				System.out.println("Weapon::changeStat() - unexpected type.");
				return 0;
			}
		}
	}
}
