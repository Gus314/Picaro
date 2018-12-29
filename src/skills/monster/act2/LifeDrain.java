package skills.monster.act2;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.StatType;
import skills.TargetSkill;
import enums.SkillType;
import statuses.Recklessness;

import java.io.Serializable;

public class LifeDrain extends TargetSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    private static final int cost = 10;
    private static final SkillType skillType = SkillType.MAGICAL;
    private static final String name = "Life Drain";
    private static final int range = 1;

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        int intensity = 20;
        int change = (target.getLife() > intensity) ? target.getLife() : intensity;

        target.changeStat(StatType.LIFE, intensity * -1);
        source.changeStat(StatType.LIFE, intensity);
        subtractCost(source);
        return source.getName() + " drained " + change + " life from " + target + ".";
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
