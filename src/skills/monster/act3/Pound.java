package skills.monster.act3;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import skills.TargetSkill;
import statuses.Bleed;
import enums.SkillType;
import statuses.Strong;

import java.io.Serializable;

public class Pound extends TargetSkill implements Serializable
{
    private static final int cost = 15;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Pound";
    private static final int range = 1;

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
        source.attack(target);
        Strong strong = new Strong(source, 5, 10);
        source.addStatusEffect(strong);
        subtractCost(source);

        return source.getName() + " pounded " + target.getName() + ", powering itself up in the process.";
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
