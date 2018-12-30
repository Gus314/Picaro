package skills.monster.act3;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import skills.SelfSkill;
import skills.TargetSkill;
import enums.SkillType;
import statuses.RampUp;
import statuses.Recklessness;

import java.io.Serializable;

public class WindUp extends SelfSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    private static final int cost = 15;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Wind-Up";

    @Override
    public String action(Creature source)
    {
        RampUp rampUp = new RampUp(source, 5, 5);
        source.addStatusEffect(rampUp);

        subtractCost(source);
        return "";
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
}
