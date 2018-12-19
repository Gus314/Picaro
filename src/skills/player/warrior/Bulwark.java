package skills.player.warrior;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.SelfSkill;
import skills.TargetSkill;
import statuses.Shield;

public class Bulwark extends SelfSkill
{
    private static final int cost = 5;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Bulwark";

    @Override
    public String getDescription()
    {
        return "Monster skill";
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
        int intensity = (int) Math.ceil(source.getDefense()*0.2);
        Shield shield = new Shield(source, 5, intensity);
        source.addStatusEffect(shield);
        subtractCost(source);
        return source.getName() + " used Bulwark.";
    }
}
