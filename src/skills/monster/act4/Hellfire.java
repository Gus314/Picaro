package skills.monster.act4;

import control.Controller;
import control.Coordinate;
import entities.Floor;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.StatType;
import skills.TargetSkill;
import enums.SkillType;
import skills.combat.CombatHelper;
import statuses.Bleed;
import statuses.Burn;
import statuses.Recklessness;
import statuses.Stun;

import java.io.Serializable;
import java.util.Collection;

public class Hellfire extends TargetSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    private static final int cost = 40;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Hellfire";
    private static final int range = 5;

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        Burn burn = new Burn(target, 5, 10);
        target.addStatusEffect(burn);

        Stun stun = new Stun(target, 5);
        target.addStatusEffect(burn);

        return source.getName() + " is wreathed " + target.getName() + " in hellfire.";
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
