package skills.monster.act3;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import skills.TargetSkill;
import enums.SkillType;
import statuses.Weak;

import java.io.Serializable;

public class MoltenBite extends TargetSkill implements Serializable
{
    private static final int cost = 15;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Molten Bite";
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
        Weak weak = new Weak(target, 5);
        source.addStatusEffect(weak);

        subtractCost(source);
        return source.getName() + " bites " + target.getName() + " with molten fangs.";
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
