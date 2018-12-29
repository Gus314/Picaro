package skills.monster.act2;

import entities.creatures.Creature;
import enums.SkillBehaviour;
import skills.TargetSkill;
import enums.SkillType;
import java.io.Serializable;

public class Flurry extends TargetSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    private static final int cost = 7;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Flurry";
    private static final int range = 4;

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        source.attack(target);
        source.attack(target);
        subtractCost(source);
        return source.getName() + " finished unleashing a flurry against " + target.getName() + ".";
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
        return SkillBehaviour.ATTACK;
    }
}
