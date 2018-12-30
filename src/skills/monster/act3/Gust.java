package skills.monster.act3;

import control.Controller;
import control.Coordinate;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.StatType;
import skills.AreaSkill;
import enums.SkillType;
import skills.CombatHelper;
import statuses.Recklessness;

import java.io.Serializable;
import java.util.List;

public class Gust extends AreaSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }


    private static final int cost = 15;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Gust";
    private static final int range = 4;

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public int getRadius() {
        return 4;
    }

    @Override
    public String action(Creature source, List<Creature> targets)
    {
        String message = source.getName() + " created a strong gust of wind.";

        for(Creature target: targets)
        {
            int newRow = target.getRow() - Controller.getGenerator().nextInt(getRadius()) + Controller.getGenerator().nextInt(getRadius());
            int newColumn = target.getRow() - Controller.getGenerator().nextInt(getRadius()) + Controller.getGenerator().nextInt(getRadius());
            Coordinate newC1 = new Coordinate(target.getRow(), newColumn);
            Coordinate newC2 = new Coordinate(newRow, target.getColumn());
            Coordinate newC3 = new Coordinate(newRow, newColumn);

            if(target.getMap().isEmpty(newC3))
            {
                target.setRow(newC3.getRow());
                target.setColumn(newC3.getColumn());
            }
            else if(target.getMap().isEmpty(newC2))
            {
                target.setRow(newC2.getRow());
                target.setColumn(newC2.getColumn());
            }
            else if(target.getMap().isEmpty(newC1))
            {
                target.setRow(newC1.getRow());
                target.setColumn(newC1.getColumn());
            }
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
