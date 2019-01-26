package skills.player.hunter;

import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import enums.StatType;
import skills.TargetSkill;
import skills.combat.CombatHelper;
import skills.combat.CombatInfo;
import statuses.Blind;

public class EyeThrust extends TargetSkill
{
    @Override
    public int getRange()
    {
        return 1;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        Blind blind = new Blind(target, 5);
        target.addStatusEffect(blind);

        CombatHelper combatHelper = new CombatHelper(true, true, true, true, 1.0, " thrust at the eye of ");
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
        return "Eye Thrust";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.ATTACK;
    }

    @Override
    public String getDescription()
    {
        return "Thrust at an enemy's eye or other visual organ.";
    }
}
