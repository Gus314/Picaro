package skills.player;

import entities.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.SelfSkill;
import statuses.Shell;

import java.io.Serializable;

public class Toughen extends SelfSkill implements Serializable
{
    @Override
    public String action(Creature source)
    {
        int duration = 4;
        int intensity = 5;
        Shell shell = new Shell(source, duration, intensity);
        source.addStatusEffect(shell);
        subtractCost(source);
        return source.getName() + " readies to receive attacks, increasing defence.";
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
        return "Toughen";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.DEFEND;
    }
}
