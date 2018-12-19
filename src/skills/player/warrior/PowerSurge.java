package skills.player.warrior;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.SelfSkill;
import skills.TargetSkill;
import statuses.CoolDown;
import statuses.RampUp;
import statuses.Shield;
import statuses.Strong;

public class PowerSurge extends SelfSkill
{
    private static final int cost = 5;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Power Surge";
    @Override
    public String getDescription()
    {
        return "Increase strength.";
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
        return SkillBehaviour.SUPPORT;
    }

    @Override
    public String action(Creature source)
    {
        int duration = 5;
        int intensity = (int) Math.ceil(source.getMinDamage()*0.2);
        Strong strong = new Strong(source, 5, intensity);
        source.addStatusEffect(strong);
        subtractCost(source);
        return source.getName() + " used Power Surge.";
    }
}
