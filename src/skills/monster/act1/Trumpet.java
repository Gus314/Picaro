package skills.monster.act1;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import skills.TargetSkill;
import statuses.Bleed;
import enums.SkillType;
import statuses.Stun;

import java.io.Serializable;

public class Trumpet extends TargetSkill implements Serializable
{
    private static final int cost = 5;
    private static final SkillType skillType = SkillType.MAGICAL;
    private static final String name = "Trumpet";
    private static final int range = 5;

    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        String message = source.getName() + " trumpeted at " + target.getName();
        Stun stun = new Stun(target, 3);
        target.addStatusEffect(stun);
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
