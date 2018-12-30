package skills.monster.act3;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import skills.TargetSkill;
import enums.SkillType;
import statuses.Blind;
import statuses.Naive;
import statuses.Recklessness;
import statuses.Stun;

import java.io.Serializable;

public class Gaze extends TargetSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    private static final int cost = 15;
    private static final SkillType skillType = SkillType.MAGICAL;
    private static final String name = "Gaze";
    private static final int range = 4;

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        Stun stun = new Stun(target, 5);
        source.addStatusEffect(stun);
        subtractCost(source);
        return source.getName() + " gazed at " + target.getName();
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
