package skills.monster.act1;

import control.Controller;
import control.Coordinate;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.AreaSkill;
import skills.SelfSkill;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Set extends SelfSkill implements Serializable
{
    private static final int cost = 5;
    private static final SkillType skillType = SkillType.MAGICAL;
    private static final String name = "Set";
    private static final int radius = 7;

    @Override
    public String getDescription()
    {
        return "Monster skill.";
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public SkillType getSkillType() {
        return skillType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String action(Creature source)
    {
        String message = source.getName() + " gathered its friends.";

        Collection<Creature> targets = source.getSurroundingCreatures(radius, source.getFaction());
        List<Creature> targetsList = new ArrayList<Creature>();
        targetsList.addAll(targets);

        for(Coordinate availableFloor : source.getSurroundingAvailableFloor())
        {
            if(!targetsList.isEmpty())
            {
                targetsList.get(0).setRow(availableFloor.getRow());
                targetsList.get(0).setColumn(availableFloor.getColumn());
                targetsList.remove(0);
            }
        }

        subtractCost(source);
        return message;
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.SUPPORT;
    }
}
