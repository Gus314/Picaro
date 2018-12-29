package skills.monster.act2;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.StatType;
import skills.TargetSkill;
import enums.SkillType;
import statuses.Naive;
import statuses.Recklessness;

import java.io.Serializable;

public class Scream extends TargetSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    private static final int cost = 10;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Scream";
    private static final int range = 4;

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        int intensity = 5;
        target.changeStat(StatType.LIFE, -intensity);
        target.changeStat(StatType.MP, -intensity);
        target.changeStat(StatType.PP, -intensity);
        subtractCost(source);
        return source.getName() + " let out a blood-curdling scream, costing " + target.getName() + " " + intensity + " life, mp and pp";
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
