package skills.monster.act3;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.StatType;
import skills.TargetSkill;
import enums.SkillType;
import statuses.Naive;
import statuses.Recklessness;

import java.io.Serializable;

public class Riddle extends TargetSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    private static final int cost = 15;
    private static final SkillType skillType = SkillType.MAGICAL;
    private static final String name = "Riddle";
    private static final int range = 4;

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        int mp = target.getMagicPoints();
        int pp = target.getPhysicalPoints();
        int difference = mp - pp;
        boolean mpGreater = (mp > pp);

        target.changeStat(StatType.MP, mpGreater ? (difference * -1) : difference);
        target.changeStat(StatType.PP, mpGreater ? difference : (difference * -1));
        subtractCost(source);
        return source.getName() + " told " + target.getName() + "a riddle, swapping mp and pp.";
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
