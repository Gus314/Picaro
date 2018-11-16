package skills.monster;

import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.SelfSkill;
import statuses.Shell;

import java.io.Serializable;

public class RollUp extends SelfSkill implements Serializable
{
    @Override
    public String action(Creature source)
    {
        int duration = 3;
        int intensity = 5;
        Shell shell = new Shell(source, duration, intensity);
        source.addStatusEffect(shell);
        subtractCost(source);
        return source.getName() + " rolls up, increasing its defence.";
    }

    @Override
    public int getCost()
    {
        return 5;
    }

    @Override
    public SkillType getSkillType()
    {
        return SkillType.PHYSICAL;
    }

    @Override
    public String getName()
    {
        return "RollUp";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.DEFEND;
    }
}
