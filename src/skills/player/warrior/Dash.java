package skills.player.warrior;

import control.Controller;
import entities.Entity;
import entities.Floor;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.FloorSkill;
import skills.TargetSkill;

import java.util.Collection;

public class Dash extends FloorSkill
{
    @Override
    public String getDescription()
    {
        return "Dash across the floor.";
    }


    private static final int cost = 5;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Dash";
    private static final int range = 1;

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
        return source.getName() + " dashed across the floor.";
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

