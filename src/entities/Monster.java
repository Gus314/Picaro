package entities;

import control.Controller;
import control.Map;
import entities.ai.act.Act;
import entities.ai.act.ActFactory;
import entities.ai.Brain;
import entities.ai.move.MoveFactory;
import enums.*;
import skills.AreaSkill;
import skills.Skill;
import skills.TargetSkill;
import ui.mainwindow.Messages;

import java.io.Serializable;
import java.util.*;

public class Monster extends Creature implements Serializable {
	private int minLevel;
	private int maxLevel;
	private Brain brain;
	private boolean playerSighted;
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
		playerSighted = false;
	}

	protected void setPlayerSighted(boolean inPlayerSighted)
	{
		playerSighted = inPlayerSighted;
	}

	protected boolean isPlayerSighted()
	{
		return playerSighted;
	}

	public int getMinLevel() {
		return minLevel;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	private void move(Behaviour behaviour, Monster monster, Map map)
	{
		java.util.Map<Faction, List<Creature>> targets = findTargets();
		MoveFactory.construct(behaviour, monster, map).move(targets);
	}

	private Collection<Entity> act(Monster monster, Messages messages, Behaviour behaviour)
	{
		Collection<Floor> floors = findNearbyEmptyFloors(getSightRadius());
		java.util.Map<Faction, List<Creature>> targets = findTargets();

		Act action = ActFactory.construct(behaviour, monster, messages);
		return action.act(targets, floors);
	}

	private java.util.Map<Faction, List<Creature>> findTargets()
	{
		java.util.Map<Faction, List<Creature>> result = new HashMap<Faction, List<Creature>>();
		for(Faction faction: Faction.values())
		{
			result.put(faction, new ArrayList<Creature>());
		}

		Set<Entity> visible = getMap().lineOfSight(this, getSightRadius());
		for(Entity entity: visible)
		{
			if(entity instanceof  Creature)
			{
				Creature creature = (Creature) entity;
				List<Creature> factionCreatures = result.get(creature.getFaction()); // Note keys added above.
				factionCreatures.add(creature);
				result.put(creature.getFaction(), factionCreatures);
			}
		}

		return result;
	}

	private OptionalInt getPlayerRange()
	{
        final int maxRange = getSightRadius();

		for(int i = 0; i < maxRange; i++)
		{
			for(Entity ent: getMap().lineOfSight(this, i))
			{
				if(ent instanceof Player)
				{
					return OptionalInt.of(i);
				}
			}
		}

		return OptionalInt.empty();
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
		// TODO: Consider other factions.
		Collection<Skill> result = new ArrayList<Skill>();

		OptionalInt playerRange = getPlayerRange();

		// If player is not in range then no skills are in range.
		if(!playerRange.isPresent())
		{
			return result;
		}

		// playerRange now guaranteed to have a value.
		for(Skill skill: getSkills())
		{
			if(skill.getSkillBehaviour() == SkillBehaviour.ATTACK)
			{
					if(skill.getTargetType() == TargetType.TARGET)
					{
						TargetSkill targetSkill = (TargetSkill)skill;
						if(targetSkill.getRange() >= playerRange.getAsInt())
						{
							result.add(skill);
						}
					}
					else if(skill.getTargetType() == TargetType.AREA)
					{
						AreaSkill areaSkill = (AreaSkill) skill;
						int furthestAttackDistance = areaSkill.getRange() + areaSkill.getRadius() - 1; // Range 4, radius 1, furthest distance is 4, not 3.
						if(furthestAttackDistance >= playerRange.getAsInt())
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
		// Return a collection of entities to be added, e.g. summoned creatures.
		playerSighted = playerSighted || getMap().isInLineOfSight(this, getMap().getPlayer(), getSightRadius());

		// If the player has never been seen then do nothing as AI computations are expensive.
		if(!playerSighted)
		{
			return new ArrayList<Entity>();
		}

		boolean foeInAttackRange = false;

		// TODO: Optimise these checks.
		for(Entity ent: getMap().lineOfSight(this, getMaxAttackRange()))
		{
			if(ent instanceof Creature)
			{
				Creature creature = (Creature)ent;
				if(!matchingFaction(creature))
				{
					foeInAttackRange = true;
					break;
				}
			}
		}

		java.util.Map<Faction, List<Creature>> targets = findTargets();
		boolean friendNear = (targets.get(getFaction()).size() > 1); // It will always be at least one due to self.

		Behaviour behaviour = brain.determineBehaviour();
		TurnType turnType = brain.determineTurnType(foeInAttackRange, friendNear, playerSighted, behaviour);

		switch(turnType)
		{
			case MOVE:
			{
				move(behaviour, this, getMap());
				return new ArrayList<Entity>();
			}
			case ACT:
			{
				return act(this, getMessages(), behaviour);
			}
			case SKIP:
			{
				System.out.println("Turn skipped!");
				return new ArrayList<Entity>();
			}
			default:
			{
				System.out.println("Monster::takeTurn() - unexpected turn type.");
				return new ArrayList<Entity>();
			}
		}
	}
}
