package skills.player.warrior;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.SelfSkill;
import skills.TargetSkill;
import statuses.CoolDown;
import statuses.Shield;

public class Relax extends SelfSkill
{
    private static final int cost = 5;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Relax";
    @Override
    public String getDescription()
    {
        return "Cool down, gradually increasing defence at a cost of attack.";
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

    @Override
    public String action(Creature source)
    {
        int duration = 5;
        int intensity = (int) Math.ceil(source.getDefense()*0.1);
        CoolDown coolDown = new CoolDown(source, 5, intensity);
        source.addStatusEffect(coolDown);
        subtractCost(source);
        return source.getName() + " used Relax.";
    }
}
