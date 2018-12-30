package skills.monster.act3;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import skills.TargetSkill;
import statuses.Bleed;
import enums.SkillType;
import statuses.Poison;
import statuses.Strong;

import java.io.Serializable;

public class Spines extends TargetSkill implements Serializable
{
    private static final int cost = 15;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Spines";
    private static final int range = 4;

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
        Poison poison = new Poison(target, 3, 3);
        target.addStatusEffect(poison);
        Bleed bleed = new Bleed(target, 3, 3);
        target.addStatusEffect(bleed);
        subtractCost(source);

        return source.getName() + " launched spines at " + target.getName() + ", opening and poisoning wounds.";
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
