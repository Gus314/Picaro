package entities.factories;

import control.Map;
import entities.Monster;
import enums.Faction;
import skills.Skill;
import ui.mainwindow.Messages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MonsterFactory extends AbstractEntityFactory
{
	private Faction faction;
	private Character cha;
	private int life;
	private int minDamage;
	private int maxDamage;
	private int critChance;
	private int defense;
	private int blockChance;
	private int absorbChance;
	private int range;
	private int minLevel;
	private int maxLevel;
	private Map map;
	private Messages messages;
	private String name;
	private int exp;
	private int maxPhysicalPoints;
	private int maxMagicPoints;
	private int intelligence;
	private int magicDefense;
	private Collection<Skill> skills;

	public MonsterFactory(Faction inFaction, Character inCha, int inLife, int inMinDamage, int inMaxDamage, int inCritChance, int inDefense, int inBlockChance, int inAbsorbChance, int inRange, String inName,
						  Map inMap, Messages inMessages, int inExp, int inMaxPhysicalPoints, int inMaxMagicPoints, int inIntelligence, int inMagicDefense, Collection<Skill> inSkills, int inMinLevel, int inMaxLevel)
	{
		faction = inFaction;
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
		minLevel = inMinLevel;
		maxLevel = inMaxLevel;
		maxPhysicalPoints = inMaxPhysicalPoints;
		maxMagicPoints = inMaxMagicPoints;
		intelligence = inIntelligence;
		magicDefense = inMagicDefense;
		skills = new ArrayList<Skill>();
		skills.addAll(inSkills);
	}

	public void setMessages(Messages inMessages){messages = inMessages;}

	public void addSkill(Skill skill)
	{
		skills.add(skill);
	}

	public void setMap(Map inMap)
	{
		map = inMap;
	}
	
	public int getMinLevel(){return minLevel;}

	public int getMaxLevel(){return maxLevel;}
	
	public Monster construct(int inRow, int inColumn)
	{
		return new Monster(cha, inRow, inColumn, map, messages, faction, defense, name, life, life, minDamage, maxDamage, critChance, blockChance, absorbChance,
				range, exp, maxPhysicalPoints, maxPhysicalPoints, maxMagicPoints, maxMagicPoints, intelligence, magicDefense, skills, minLevel, maxLevel);
	}
}
