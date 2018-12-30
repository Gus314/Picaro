package skills.monster.act3;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import skills.TargetSkill;
import enums.SkillType;
import statuses.Poison;
import statuses.Recklessness;
import statuses.Stun;

import java.io.Serializable;

public class Web extends TargetSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    private static final int cost = 25;
    private static final SkillType skillType = SkillType.MAGICAL;
    private static final String name = "Web";
    private static final int range = 1;

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        int duration = 3;
        Stun stun = new Stun(target, duration);
        source.addStatusEffect(stun);

        Poison poison = new Poison(target, duration, 3);
        source.addStatusEffect(poison);
        subtractCost(source);

        return source.getName() + " caught " + target.getName() + " in a web.";
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
