package skills.monster.act4;

import control.Controller;
import control.Coordinate;
import entities.Floor;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.StatType;
import skills.SelfSkill;
import skills.TargetSkill;
import enums.SkillType;
import statuses.Bleed;
import statuses.Recklessness;

import java.io.Serializable;
import java.util.Collection;

public class Transform extends SelfSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    private static final int cost = 40;
    private static final SkillType skillType = SkillType.MAGICAL;
    private static final String name = "Transform";

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

    @Override
    public String action(Creature source)
    {
        for(StatType stat: StatType.values())
        {
            int current = source.getStat(stat);
            int change = (current > 0) ? Controller.getGenerator().nextInt(current ) : Controller.getGenerator().nextInt(10);
            boolean decrease = Controller.getGenerator().nextBoolean();
            if(decrease)
            {
                change *= -1;
            }
            source.changeStat(stat, change);
        }

        subtractCost(source);
        return source.getName() + " transformed, greatly changing stats.";
    }
}
