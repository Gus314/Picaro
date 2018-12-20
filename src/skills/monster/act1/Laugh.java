package skills.monster.act1;

import entities.creatures.Creature;
import enums.SkillBehaviour;
import skills.AreaSkill;
import enums.SkillType;
import statuses.Recklessness;

import java.io.Serializable;
import java.util.List;

public class Laugh extends AreaSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }


    private static final int cost = 5;
    private static final SkillType skillType = SkillType.MAGICAL;
    private static final String name = "Laugh";
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
        String message = source.getName() + " began laughing hysterically.";

        for(Creature target: targets)
        {
            Recklessness recklessness = new Recklessness(target);
            target.addStatusEffect(recklessness);
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
