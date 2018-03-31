package entities.factories;

import control.Map;
import entities.Monster;
import ui.Messages;

public class MonsterFactory extends AbstractEntityFactory
{
	private Character cha;
	private int life;
	private int minDamage;
	private int maxDamage;
	private int critChance;
	private int defense;
	private int blockChance;
	private int absorbChance;
	private int range;
	private Map map;
	private Messages messages;
	private String name;
	private int exp;
	private int level;
	private int maxPhysicalPoints;
	private int maxMagicPoints;
	
	public MonsterFactory(Character inCha, int inLife, int inMinDamage, int inMaxDamage, int inCritChance, int inDefense, int inBlockChance, int inAbsorbChance, int inRange, String inName,
						  Map inMap, Messages inMessages, int inExp, int inLevel, int inMaxPhysicalPoints, int inMaxMagicPoints)
	{
		cha = inCha;
		life = inLife;
		minDamage = inMinDamage;
		maxDamage = inMaxDamage;
		critChance = inCritChance;
		defense = inDefense;
		blockChance = inBlockChance;
		absorbChance = inAbsorbChance;
		range = inRange;
		map = inMap;
		messages = inMessages;
		name = inName;
		exp = inExp;
		level = inLevel;
		maxPhysicalPoints = inMaxPhysicalPoints;
		maxMagicPoints = inMaxMagicPoints;
	}
	
	public void setMap(Map inMap)
	{
		map = inMap;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public Monster construct(int inRow, int inColumn)
	{
		return new Monster(cha, inRow, inColumn, map, messages, defense, name, life, life, minDamage, maxDamage, critChance, blockChance, absorbChance,
				range, exp, level, maxPhysicalPoints, maxPhysicalPoints, maxMagicPoints, maxMagicPoints);
	}
}
