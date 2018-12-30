package skills.monster.act3;

import control.Controller;
import control.Coordinate;
import entities.Floor;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.StatType;
import skills.TargetSkill;
import enums.SkillType;
import statuses.Recklessness;

import java.io.Serializable;
import java.util.Collection;

public class Trample extends TargetSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    private static final int cost = 25;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Trample";
    private static final int range = 1;

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        source.attack(target);

        Collection<Coordinate> surroundingFloor = target.getSurroundingAvailableFloor();
        if(!surroundingFloor.isEmpty())
        {
            Coordinate newPosition = surroundingFloor.stream().findAny().get();
            source.setRow(newPosition.getRow());
            source.setColumn(newPosition.getColumn());
        }

        subtractCost(source);
        return source.getName() + " trampled " + target.getName() + ".";
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
