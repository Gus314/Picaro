package skills.monster.act2;

import entities.creatures.Creature;
import enums.SkillBehaviour;
import skills.TargetSkill;
import enums.SkillType;
import statuses.Groggy;

import java.io.Serializable;

public class Haunt extends TargetSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    private static final int cost = 8;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Haunt";
    private static final int range = 4;

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        Groggy groggy = new Groggy(source, 3, 5);
        source.addStatusEffect(groggy);
        subtractCost(source);
        return source.getName() + " let out a haunting cry.";
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
