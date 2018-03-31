package entities.skills;

import entities.Creature;
import enums.SkillType;

public class PoisonDart extends TargetSkill
{
    private static final int cost = 3;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Poison Dart";
    private static final int range = 4;

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        return source.getName() + "cast poison dart";
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
}
