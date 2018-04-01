package entities.equipment.factories;

import entities.equipment.Weapon;

public class WeaponFactory
{
	private int minDamage;
	private int maxDamage;
	private int critChance;
	private int intelligence;
	private String name;
	private int level;
	private int range;
	private int radius;
	
	public WeaponFactory(int inMinDamage, int inMaxDamage, int inCritChance, int inIntelligence, String inName, int inLevel, int inRange, int inRadius)
	{
		minDamage = inMinDamage;
		maxDamage = inMaxDamage;
		critChance = inCritChance;
		intelligence = inIntelligence;
		name = inName;
		level = inLevel;
		range = inRange;
		radius = inRadius;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public Weapon construct(int inRow, int inColumn)
	{
		return new Weapon(minDamage, maxDamage, critChance, intelligence, name, 'w', inRow, inColumn, level, range, radius);
	}
}
