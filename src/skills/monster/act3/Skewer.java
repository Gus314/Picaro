package skills.monster.act3;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.StatType;
import skills.TargetSkill;
import enums.SkillType;
import statuses.Recklessness;

import java.io.Serializable;

public class Skewer extends TargetSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    private static final int cost = 25;
    private static final SkillType skillType = SkillType.MAGICAL;
    private static final String name = "Skewer";
    private static final int range = 1;

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        int halfIntelligence = source.getIntelligence()/2;
        int damage = halfIntelligence + Controller.getGenerator().nextInt(halfIntelligence);

        target.changeStat(StatType.LIFE, damage * -1);
        subtractCost(source);
        return source.getName() + " skewered " + target.getName() + " for " + damage + " damage.";
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
