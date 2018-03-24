package entities.factories;

import control.Map;
import entities.Creature;
import ui.Messages;

public class CreatureFactory extends AbstractEntityFactory
{
	private Character cha;
	private int life;
	private int attack;
	private int defense;
	private Map map;
	private Messages messages;
	private String name;
	private int exp;
	private int level;
	
	public CreatureFactory(Character inCha, int inLife, int inAttack, int inDefense, String inName, Map inMap, Messages inMessages, int inExp, int inLevel)
	{
		cha = inCha;
		life = inLife;
		attack = inAttack;
		defense = inDefense;
		map = inMap;
		messages = inMessages;
		name = inName;
		exp = inExp;
		level = inLevel;
	}
	
	public void setMap(Map inMap)
	{
		map = inMap;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public Creature construct(int inRow, int inColumn)
	{
		return new Creature(cha, inRow, inColumn, life, attack, defense, map, messages, name, exp, level);
	}
}
