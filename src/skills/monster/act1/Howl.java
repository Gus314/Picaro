package skills.monster.act1;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import skills.AreaSkill;
import skills.TargetSkill;
import statuses.Bleed;
import enums.SkillType;
import statuses.Shield;
import statuses.Stun;

import java.io.Serializable;
import java.util.List;

public class Howl extends AreaSkill implements Serializable
{
    private static final int cost = 5;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Howl";
    private static final int range = 5;

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
    public int getRadius()
    {
        return 5;
    }

    @Override
    public String action(Creature source, List<Creature> targets)
    {
        String message = source.getName() + " howled.";

        for(Creature target: targets)
        {
            Shield shield = new Shield(target, 3, 5);
            target.addStatusEffect(shield);
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
        return SkillBehaviour.SUPPORT;
    }
}
