package skills.monster.act2;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.StatType;
import skills.TargetSkill;
import statuses.Bleed;
import enums.SkillType;

import java.io.Serializable;

public class Savage extends TargetSkill implements Serializable
{
    private static final int cost = 8;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Savage";
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
        int critChange = (source.getCritChance() <= 33) ? 33 : (100 - source.getCritChance());
        source.changeStat(StatType.CRITCHANCE, critChange);
        source.attack(target);
        source.changeStat(StatType.CRITCHANCE, critChange * -1);

        Bleed bleed = new Bleed(target, 7, 1);
        target.addStatusEffect(bleed);

        return source.getName() + " savaged " + target.getName() + ".";
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
