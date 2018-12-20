package skills.monster.act1;

import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.SelfSkill;
import statuses.CoolDown;
import statuses.Shell;
import statuses.Strong;

import java.io.Serializable;

public class BuryHead extends SelfSkill implements Serializable
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
        CoolDown coolDown = new CoolDown(source, duration, intensity);
        source.addStatusEffect(coolDown);
        subtractCost(source);
        return source.getName() + " buries its head.";
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
        return "BuryHead";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.DEFEND;
    }
}
