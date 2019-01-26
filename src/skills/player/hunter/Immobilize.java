package skills.player.hunter;

import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import enums.StatType;
import skills.TargetSkill;
import skills.combat.CombatHelper;
import skills.combat.CombatInfo;
import statuses.Stun;

public class Immobilize extends TargetSkill
{
    private static final int duration = 5;

    @Override
    public int getRange()
    {
        return 4;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        Stun stun = new Stun(target, duration);
        target.addStatusEffect(stun);

        CombatHelper combatHelper = new CombatHelper(true, true, true, true, 0.8, "immobilizes");
        CombatInfo combatInfo = combatHelper.calculateCombat(source, target);
        target.changeStat(StatType.LIFE, combatInfo.getLifeChange());

        subtractCost(source);
        return combatInfo.getMessage();
    }

    @Override
    public int getCost()
    {
        return 5;
    }

    @Override
    public SkillType getSkillType()
    {
        return SkillType.PHYSICAL;
    }

    @Override
    public String getName()
    {
        return "Immobilize";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.ATTACK;
    }

    @Override
    public String getDescription()
    {
        return "Immobilize target for " + duration + " turns.";
    }
}
