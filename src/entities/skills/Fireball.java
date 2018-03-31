package entities.skills;

import entities.Creature;
import enums.SkillType;

import java.util.List;

public class Fireball extends AreaSkill
{
    private static final int range = 5;
    private static final int radius = 3;
    private static final int cost = 6;
    private static final SkillType skillType = SkillType.MAGICAL;
    private static final String name = "Fireball";

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public int getRadius() {
        return radius;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public SkillType getSkillType() {
        return skillType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String action(Creature source, List<Creature> targets)
    {
        return source.getName() + " cast fireball";
    }
}
