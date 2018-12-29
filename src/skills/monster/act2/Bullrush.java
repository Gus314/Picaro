package skills.monster.act2;

import control.Controller;
import entities.Entity;
import entities.Floor;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.FloorSkill;
import skills.TargetSkill;
import statuses.Stun;

import java.util.Collection;

public class Bullrush extends FloorSkill
{
    @Override
    public String getDescription()
    {
        return "monster skill.";
    }


    private static final int cost = 7;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "monster skill";
    private static final int range = 5;

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public String action(Creature source, Floor floor, Collection<Entity> additions)
    {
        String message = source.getName() + " dashed across the floor.";
        source.setRow(floor.getRow());
        source.setColumn(floor.getColumn());

        int duration = 5;
        for(Creature target: source.getSurroundingCreatures())
        {
            target.addStatusEffect(new Stun(target, duration));
            return target.getName() + " was stunned!";
        }

        subtractCost(source);
        return message;
    }


    @Override
    public int getCost()
    {
        return cost;
    }

    @Override
    public SkillType getSkillType()
    {
        return skillType;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.ATTACK;
    }
}
