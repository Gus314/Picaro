package skills.monster.act3;

import control.Controller;
import control.Coordinate;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import skills.TargetSkill;
import enums.SkillType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Lure extends TargetSkill implements Serializable
{
    private static final int cost = 25;
    private static final SkillType skillType = SkillType.MAGICAL;
    private static final String name = "Lure";
    private static final int range = 7;

    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        Collection<Coordinate> surroundingFloor = source.getSurroundingAvailableFloor();
        List<Coordinate> surroundingFloorList = new ArrayList<Coordinate>();
        surroundingFloorList.addAll(surroundingFloor);

        if(!surroundingFloorList.isEmpty())
        {
            Coordinate selectedFloor = surroundingFloorList.get(Controller.getGenerator().nextInt(surroundingFloorList.size()));
            target.setRow(selectedFloor.getRow());
            target.setColumn(selectedFloor.getColumn());
            subtractCost(source);
        }

        return surroundingFloorList.isEmpty() ? source.getName() + " failed to lure " + target.getName() + "." :
                source.getName() + " lured " + target.getName() + ".";
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
