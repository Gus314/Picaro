package skills.monster.act2;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.StatType;
import skills.TargetSkill;
import statuses.Bleed;
import enums.SkillType;

import java.io.Serializable;

public class Swipe extends TargetSkill implements Serializable
{
    private static final int cost = 8;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Swipe";
    private static final int range = 1;

    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        int critChange = (source.getCritChance() <= 50) ? 50 : (100 - source.getCritChance());
        source.changeStat(StatType.CRITCHANCE, critChange);
        source.attack(target);
        source.changeStat(StatType.CRITCHANCE, critChange * -1);

        return source.getName() + " took a swipe at " + target.getName() + ".";
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
