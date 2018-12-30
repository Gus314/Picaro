package skills.monster.act3;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.StatType;
import skills.SelfSkill;
import skills.TargetSkill;
import enums.SkillType;
import statuses.RampUp;
import statuses.Recklessness;
import statuses.Studied;

import java.io.Serializable;

public class Regrow extends SelfSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    private static final int cost = 25;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Regrow";

    @Override
    public String action(Creature source)
    {
        int intensity = 100;
        int difference = (source.getMaxLife() - source.getLife());
        int change = (difference < intensity) ? difference : intensity;

        source.changeStat(StatType.LIFE, change);

        Studied studied = new Studied(source, 5);
        source.addStatusEffect(studied);

        subtractCost(source);
        return getName() + " regrew, becoming studied and healing for " + change + " life.";
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
}
