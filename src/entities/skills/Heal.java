package entities.skills;

import entities.Creature;
import enums.SkillType;

public class Heal extends SelfSkill
{
    private static final int cost = 2;
    private static final SkillType skillType = SkillType.MAGICAL;
    private static final String name = "Heal";

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
    public String action(Creature source)
    {
       return source.getName() + " cast heal.";
    }
}
