package skills.monster.act2;

import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.SelfSkill;
import statuses.Shell;
import statuses.Shield;

import java.io.Serializable;

public class Hide extends SelfSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }


    @Override
    public String action(Creature source)
    {
        int duration = 3;
        int intensity = 7;
        Shell shell = new Shell(source, duration, intensity);
        source.addStatusEffect(shell);
        Shield shield = new Shield(source, duration, intensity);
        source.addStatusEffect(shield);
        subtractCost(source);
        return source.getName() + " hides, increasing its defence and magic defense.";
    }

    @Override
    public int getCost()
    {
        return 5;
    }

    @Override
    public SkillType getSkillType()
    {
        return SkillType.MAGICAL;
    }

    @Override
    public String getName()
    {
        return "Hides";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.DEFEND;
    }
}
