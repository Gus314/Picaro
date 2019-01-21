package skills.monster.act4;

import control.Controller;
import control.Coordinate;
import entities.Floor;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.StatType;
import skills.TargetSkill;
import enums.SkillType;
import skills.combat.CombatHelper;
import skills.combat.CombatInfo;
import statuses.Bleed;
import statuses.Recklessness;

import java.io.Serializable;
import java.util.Collection;

public class Thunderbolt extends TargetSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    private static final int cost = 40;
    private static final SkillType skillType = SkillType.MAGICAL;
    private static final String name = "Thunderbolt";
    private static final int range = 7;

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        CombatHelper combatHelper = new CombatHelper(true, false, false, false, 1.4, "hurled a thunderbolt at");
        CombatInfo combatInfo  = combatHelper.calculateCombat(source, target);
        target.changeStat(StatType.LIFE, combatInfo.getLifeChange());
        subtractCost(source);
        return combatInfo.getMessage();
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
