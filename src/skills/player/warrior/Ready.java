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

public class Ready extends SelfSkill
{
    private static final int cost = 5;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Ready";
    @Override
    public String getDescription()
    {
        return "Increase strength over time at a cost of defence.";
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
        int intensity = (int) Math.ceil(source.getMinDamage()*0.1);
        RampUp rampUp = new RampUp(source, 5, intensity);
        source.addStatusEffect(rampUp);
        subtractCost(source);
        return source.getName() + " used Ready.";
    }
}
