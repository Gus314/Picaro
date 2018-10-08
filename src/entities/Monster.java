package entities;

import control.Controller;
import control.Map;
import entities.ai.Act;
import entities.ai.ActFactory;
import entities.ai.Brain;
import entities.ai.MoveFactory;
import enums.*;
import skills.AreaSkill;
import skills.Skill;
import skills.TargetSkill;
import ui.mainwindow.Messages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Monster extends Creature implements Serializable {
	private int minLevel;
	private int maxLevel;
	private Brain brain;

	public boolean passable() {
		return false;
	}

	public Monster(Character inCha, int inRow, int inColumn, Map inMap, Messages inMessages, Faction inFaction, int inDefense, String inName, int inMaxLife, int inLife, int inMinDamage, int inMaxDamage, int inCritChance, int inBlockChance,
				   int inAbsorbChance, int inRange, int inExp, int inPhysicalPoints, int inMaxPhysicalPoints, int inMagicPoints, int inMaxMagicPoints, int inIntelligence, int inMagicDefense, Collection<Skill> inSkills, int inMinLevel, int inMaxLevel) {
		super(inCha, inRow, inColumn, inMap, inMessages, inFaction, inDefense, inName, inMaxLife, inLife, inMinDamage, inMaxDamage, inCritChance, inBlockChance,
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

	private Collection<Entity> act(Player player, Monster monster, Messages messages, Behaviour behaviour)
	{
		// TODO: What if the support skill is not a summon skill?
		// TODO: What if there is no nearby empty floor?

		Collection<Floor> floors = findNearbyEmptyFloors();
		List<Floor> floorList = new ArrayList<Floor>();
		floorList.addAll(floors);
        if(floorList.size() == 0 && behaviour == Behaviour.SUPPORT)
        {
			System.out.println("Monster::act - unable to find target for support skill.");
			return new ArrayList<Entity>();
		}

		Entity target = determineTarget(behaviour, monster, player, floorList);

		Act action = ActFactory.construct(behaviour, monster, messages);
		return action.act(target);
	}

	private Entity determineTarget(Behaviour behaviour, Monster monster, Player player, List<Floor> floorList)
	{
		switch(behaviour)
		{
			// TODO: More sophisticated targetting.
			case SUPPORT:
			{
				return floorList.get(Controller.getGenerator().nextInt(floorList.size()));
			}
			case ATTACK:
			{
				return matchingFaction(player) ? null : player; // TODO: Attack other creatures.
			}
			case DEFEND:
			case RETREAT:
			{
				// TODO: More sophisticated targetting.
				return monster;
			}
			default:
			{
				System.out.println("Monster::determineTarget - unexpected behaviour.");
				return monster;
			}
		}
	}

	private int getPlayerRange()
	{
		// TODO: Refactor - currently returns 99999 if beyond 20 and seems very inefficient, could use path-finding algorithm to help.
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

	public boolean hasSkills(SkillBehaviour behaviour)
	{
		for(Skill skill: getSkills())
		{
			if(skill.getSkillBehaviour() == behaviour && canUse(skill))
			{
				return true;
			}
		}

		return false;
	}

	public Collection<Skill> getSkills(SkillBehaviour behaviour)
	{
		Collection<Skill> result = new ArrayList<Skill>();

		for(Skill skill: getSkills())
		{
			if(skill.getSkillBehaviour() == behaviour && canUse(skill))
			{
				result.add(skill);
			}
		}

		return result;
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

	public Collection<Entity> takeTurn()
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

		Behaviour behaviour = brain.determineBehaviour();
		TurnType turnType = brain.determineTurnType(player != null, behaviour);

		switch(turnType)
		{
			case MOVE:
			{
				move(behaviour, this, getMap());
				return new ArrayList<Entity>();
			}
			case ACT:
			{
				return act(player, this, getMessages(), behaviour);
			}
			default:
			{
				System.out.println("Monster::takeTurn() - unexpected turn type.");
				return new ArrayList<Entity>();
			}
		}
	}
}
