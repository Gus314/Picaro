package skills.monster.act1;

import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.SelfSkill;
import statuses.Shell;
import statuses.Strong;

import java.io.Serializable;

public class Roar extends SelfSkill implements Serializable
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
        int intensity = 5;
        Strong strong = new Strong(source, duration, intensity);
        source.addStatusEffect(strong);
        subtractCost(source);
        return source.getName() + " roars, increasing its attack.";
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
        return "Roar";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.SUPPORT;
    }
}
