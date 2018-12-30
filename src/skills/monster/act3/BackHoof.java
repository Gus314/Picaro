package skills.monster.act3;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.StatType;
import skills.SelfSkill;
import skills.TargetSkill;
import enums.SkillType;
import statuses.*;

import java.io.Serializable;

public class BackHoof extends SelfSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    private static final int cost = 25;
    private static final SkillType skillType = SkillType.MAGICAL;
    private static final String name = "Back Hoof";

    @Override
    public String action(Creature source)
    {
        CoolDown coolDown = new CoolDown(source, 5, 40);
        source.addStatusEffect(coolDown);

        subtractCost(source);
        return getName() + " stepped back onto the back hoiof, cooling down.";
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
