package skills.monster.act3;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.StatType;
import skills.SelfSkill;
import skills.TargetSkill;
import enums.SkillType;
import statuses.RampUp;
import statuses.Recklessness;
import statuses.Studied;
import statuses.Wise;

import java.io.Serializable;

public class Sprout extends SelfSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    private static final int cost = 25;
    private static final SkillType skillType = SkillType.MAGICAL;
    private static final String name = "Sprout";

    @Override
    public String action(Creature source)
    {
        Wise wise = new Wise(source, 5, 40);
        source.addStatusEffect(wise);

        subtractCost(source);
        return getName() + " sprouted.";
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
        return SkillBehaviour.DEFEND;
    }
}
