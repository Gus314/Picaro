package skills.monster.act4;

import control.Controller;
import control.Coordinate;
import entities.Floor;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.StatType;
import skills.TargetSkill;
import enums.SkillType;
import statuses.Bleed;
import statuses.Recklessness;

import java.io.Serializable;
import java.util.Collection;

public class Reposition extends TargetSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    private static final int cost = 40;
    private static final SkillType skillType = SkillType.MAGICAL;
    private static final String name = "Reposition";
    private static final int range = 7;

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        Coordinate originalSourcePosition = source.getPosition();
        source.setPosition(target.getPosition());
        target.setPosition(originalSourcePosition);
        subtractCost(source);
        return source.getName() + " swapped position with " + target.getName() + ".";
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
