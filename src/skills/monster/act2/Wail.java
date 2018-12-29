package skills.monster.act2;

import entities.creatures.Creature;
import enums.SkillBehaviour;
import skills.AreaSkill;
import enums.SkillType;
import statuses.Recklessness;
import statuses.Stun;

import java.io.Serializable;
import java.util.List;

public class Wail extends AreaSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }


    private static final int cost = 8;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Wail";
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
        String message = source.getName() + " let out a horrific wail.";

        for(Creature target: targets)
        {
            Stun stun = new Stun(target, 2);
            target.addStatusEffect(stun);
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
