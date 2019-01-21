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

public class Cleave extends TargetSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    private static final int cost = 40;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Cleave";
    private static final int range = 1;

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        String result = "";
        CombatHelper combatHelper = new CombatHelper(true, true, true, true, 0.9, "cleaved");
        CombatInfo combatInfo = combatHelper.calculateCombat(source, target);
        target.changeStat(StatType.LIFE, combatInfo.getLifeChange());
        result += combatInfo.getMessage();

        for(Creature creature: target.getSurroundingCreatures())
        {
            combatInfo = combatHelper.calculateCombat(source, creature);
            creature.changeStat(StatType.LIFE, combatInfo.getLifeChange());
            result += "\n" +  combatInfo.getMessage();
        }

        result += "\n" + getName() + " cleaves through " + target.getName();
        return result;
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
