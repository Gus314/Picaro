package skills.monster.act3;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.StatType;
import skills.TargetSkill;
import enums.SkillType;
import statuses.Recklessness;

import java.io.Serializable;

public class Consume extends TargetSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    private static final int cost = 25;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Consume";
    private static final int range = 4;

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        int intensity = 30;
        int change = (target.getLife() > intensity) ? target.getLife() : intensity;

        target.changeStat(StatType.LIFE, intensity * -1);
        source.changeStat(StatType.LIFE, intensity);
        subtractCost(source);
        return source.getName() + " consumed " + change + " life from " + target + ".";
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
