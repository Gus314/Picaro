package entities.factories;

import entities.Weapon;

public class WeaponFactory
{
	private int minDamage;
	private int maxDamage;
	private int critChance;
	private String name;
	private int level;
	
	public WeaponFactory(int inMinDamage, int inMaxDamage, int inCritChance, String inName, int inLevel)
	{
		minDamage = inMinDamage;
		maxDamage = inMaxDamage;
		critChance = inCritChance;
		name = inName;
		level = inLevel;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public Weapon construct(int inRow, int inColumn)
	{
		return new Weapon(minDamage, maxDamage, critChance, name, 'w', inRow, inColumn, level);
	}
}
