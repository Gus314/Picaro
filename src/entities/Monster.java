package entities;

import control.Controller;
import control.Map;
import entities.ai.ActFactory;
import entities.ai.Brain;
import entities.ai.MoveFactory;
import enums.*;
import skills.AreaSkill;
import skills.Skill;
import skills.TargetSkill;
import ui.mainwindow.Messages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JOptionPane;

public class Monster extends Creature {
	private int minLevel;
	private int maxLevel;
	private Brain brain;

	public boolean passable() {
		return false;
	}

	public Monster(Character inCha, int inRow, int inColumn, Map inMap, Messages inMessages, int inDefense, String inName, int inMaxLife, int inLife, int inMinDamage, int inMaxDamage, int inCritChance, int inBlockChance,
				   int inAbsorbChance, int inRange, int inExp, int inPhysicalPoints, int inMaxPhysicalPoints, int inMagicPoints, int inMaxMagicPoints, int inIntelligence, int inMagicDefense, Collection<Skill> inSkills, int inMinLevel, int inMaxLevel) {
		super(inCha, inRow, inColumn, inMap, inMessages, inDefense, inName, inMaxLife, inLife, inMinDamage, inMaxDamage, inCritChance, inBlockChance,
				inAbsorbChance, inRange, inExp, inPhysicalPoints, inMaxPhysicalPoints, inMagicPoints, inMaxMagicPoints, inIntelligence, inMagicDefense);
		minLevel = inMinLevel;
		maxLevel = inMaxLevel;
		addSkills(inSkills);
		brain = new Brain(this);
	}

	public int getMinLevel() {
		return minLevel;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	private void move(Behaviour behaviour, Monster monster, Map map) {
		MoveFactory.construct(behaviour, monster, map).move();
	}

	private void act(Player player, Monster monster, Messages messages, Behaviour behaviour) {
		ActFactory.construct(behaviour, monster, messages).act(player);

	}

	private int getPlayerRange()
	{
		// TODO: Refactor - currently returns 99999 if beyond 20 and seems very inefficient, could use path-finding algorithm to help.
		Player player = null;
        final int maxRange = 20;

		for(int i = 0; i < maxRange; i++)
		{
			for(Entity ent: getMap().lineOfSight(this, i))
			{
				if(ent instanceof Player)
				{
					return i;
				}
			}
		}

		return 99999;
	}

	private int getMaxAttackRange()
	{
		int maxRange = getRange();

		for(Skill skill: getSkills())
		{
			if(skill.getSkillBehaviour() == SkillBehaviour.ATTACK && canUse(skill))
			{
				if(skill.getTargetType() == TargetType.TARGET)
				{
					TargetSkill targetSkill = (TargetSkill)skill;
					if(targetSkill.getRange() > maxRange)
					{
						maxRange = targetSkill.getRange();
					}
				}
				else if(skill.getTargetType() == TargetType.AREA)
				{
					AreaSkill areaSkill = (AreaSkill) skill;
					int furthestAttackDistance = areaSkill.getRange() + areaSkill.getRadius() - 1; // Range 4, radius 1, furthest distance is 4, not 3.
					if(furthestAttackDistance > maxRange)
					{
						maxRange = furthestAttackDistance;
					}
				}
			}
		};

		return maxRange;
	}

	public Collection<Skill> getAttackSkillsInRange()
	{
		Collection<Skill> result = new ArrayList<Skill>();

		int playerRange = getPlayerRange();

		for(Skill skill: getSkills())
		{
			if(skill.getSkillBehaviour() == SkillBehaviour.ATTACK)
			{
					if(skill.getTargetType() == TargetType.TARGET)
					{
						TargetSkill targetSkill = (TargetSkill)skill;
						if(targetSkill.getRange() >= playerRange)
						{
							result.add(skill);
						}
					}
					else if(skill.getTargetType() == TargetType.AREA)
					{
						AreaSkill areaSkill = (AreaSkill) skill;
						int furthestAttackDistance = areaSkill.getRange() + areaSkill.getRadius() - 1; // Range 4, radius 1, furthest distance is 4, not 3.
						if(furthestAttackDistance >= playerRange)
						{
							result.add(skill);
						}
				}
			}
		}

		return result;
	}

	public void takeTurn()
	{
		Player player = null;

		for(Entity ent: getMap().lineOfSight(this, getMaxAttackRange()))
		{
			if(ent instanceof Player)
			{
				player = (Player) ent;
				break;
			}
		}

		TurnType turnType = brain.determineTurnType(player != null);
		Behaviour behaviour = brain.determineBehaviour();

		switch(turnType)
		{
			case MOVE:
			{
				move(behaviour, this, getMap());
				break;
			}
			case ACT:
			{
				act(player, this, getMessages(), behaviour);
				break;
			}
			default:
			{
				System.out.println("Monster::takeTurn() - unexpected turn type.");
				// TODO: Throw exception
			}
		}
	}
}
