package skills.monster.act2;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import skills.TargetSkill;
import enums.SkillType;
import statuses.Naive;
import statuses.Recklessness;

import java.io.Serializable;

public class Charm extends TargetSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    private static final int cost = 5;
    private static final SkillType skillType = SkillType.MAGICAL;
    private static final String name = "Charm";
    private static final int range = 4;

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        Naive naive = new Naive();
        source.addStatusEffect(naive);
        subtractCost(source);
        return source.getName() + " charmed " + target.getName();
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
