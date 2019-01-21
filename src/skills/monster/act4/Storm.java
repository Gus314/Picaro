package skills.monster.act4;

import control.Controller;
import control.Coordinate;
import entities.Floor;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.StatType;
import skills.AreaSkill;
import skills.TargetSkill;
import enums.SkillType;
import skills.combat.CombatHelper;
import skills.combat.CombatInfo;
import statuses.Bleed;
import statuses.Recklessness;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class Storm extends AreaSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    private static final int cost = 40;
    private static final SkillType skillType = SkillType.MAGICAL;
    private static final String name = "Storm";
    private static final int range = 7;

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public int getRadius()
    {
        return 5;
    }

    @Override
    public String action(Creature source, List<Creature> targets)
    {
        String message = "";
        CombatHelper combatHelper = new CombatHelper(true, false, false, false, 1.4, "arced lightning at");

        for(Creature target: targets)
        {
            CombatInfo combatInfo  = combatHelper.calculateCombat(source, target);
            target.changeStat(StatType.LIFE, combatInfo.getLifeChange());
            message += combatInfo.getMessage() + "\n";
        }

        subtractCost(source);
        message += source.getName() + " called down a storm.";
        return message;
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
