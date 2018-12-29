package skills.monster.act2;

import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.StatType;
import skills.AreaSkill;
import enums.SkillType;
import statuses.Recklessness;
import statuses.Strong;

import java.io.Serializable;
import java.util.List;

public class Sacrifice extends AreaSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }


    private static final int cost = 7;
    private static final SkillType skillType = SkillType.MAGICAL;
    private static final String name = "Sacrifice";
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
        int damage = 5;
        String message = source.getName() + " performed a sacrifice, taking " + damage + " damage itself.";

        for(Creature target: targets)
        {
            Strong strong = new Strong(target, 3, 5);
            target.addStatusEffect(strong);
        }

        subtractCost(source);
        source.changeStat(StatType.LIFE, damage * -1);

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
