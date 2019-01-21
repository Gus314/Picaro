package skills.monster.act4;

import control.Controller;
import control.Coordinate;
import entities.Floor;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.StatType;
import skills.AreaSkill;
import skills.TargetSkill;
import enums.SkillType;
import statuses.Bleed;
import statuses.Recklessness;
import statuses.Shield;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class Protection extends AreaSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    private static final int cost = 40;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Protection";
    private static final int range = 6;

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public int getRadius()
    {
        return 5;
    }

    @Override
    public String action(Creature source, List<Creature> targets)
    {
        for(Creature target: targets)
        {
            Shield shield = new Shield(target, 5, 40);
            target.addStatusEffect(shield);
        }
        subtractCost(source);
        return getName() + " extends a protective sphere.";
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
