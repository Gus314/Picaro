package skills.player.warrior;

import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.SelfSkill;
import statuses.Shell;

import java.io.Serializable;

public class Toughen extends SelfSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Increase magic defence";
    }

    @Override
    public String action(Creature source)
    {
        int duration = 5;
        int intensity = (int) Math.ceil(source.getMagicDefense()*0.2);
        Shell shell = new Shell(source, duration, intensity);
        source.addStatusEffect(shell);
        subtractCost(source);
        return source.getName() + " readies to receive attacks, increasing magic defence.";
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
