package entities.equipment.factories;

import entities.equipment.Weapon;
import entities.factories.AbstractEntityFactory;

public class WeaponFactory extends AbstractEntityFactory
{
	private int minDamage;
	private int maxDamage;
	private int critChance;
	private int intelligence;
	private String name;
	private int range;
	private int minLevel;
	private int maxLevel;

	public WeaponFactory(int inMinDamage, int inMaxDamage, int inCritChance, int inIntelligence, String inName, int inRange, int inMinLevel, int inMaxLevel)
	{
		minDamage = inMinDamage;
		maxDamage = inMaxDamage;
		critChance = inCritChance;
		intelligence = inIntelligence;
		name = inName;
		minLevel = inMinLevel;
		maxLevel = inMaxLevel;
		range = inRange;
	}
	
	public int getMinLevel(){return minLevel;}

	public int getMaxLevel(){return maxLevel;}
	
	public Weapon construct(int inRow, int inColumn)
	{
		return new Weapon(minDamage, maxDamage, critChance, intelligence, name, 'w', inRow, inColumn, range, minLevel, maxLevel);
	}
}
