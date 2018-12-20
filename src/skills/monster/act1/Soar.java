package skills.monster.act1;

import control.Controller;
import entities.Entity;
import entities.Floor;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.FloorSkill;
import skills.TargetSkill;

import java.util.Collection;

public class Soar extends FloorSkill
{
    @Override
    public String getDescription()
    {
        return "Soar to a new position.";
    }

    private static final int cost = 5;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Soar";
    private static final int range = 16;

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public String action(Creature source, Floor floor, Collection<Entity> additions)
    {
        source.setRow(floor.getRow());
        source.setColumn(floor.getColumn());
        subtractCost(source);
        return source.getName() + " soared.";
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
        return SkillBehaviour.SUPPORT;
    }
}

