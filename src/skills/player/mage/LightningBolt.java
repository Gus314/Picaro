package skills.player.mage;

import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import enums.StatType;
import skills.TargetSkill;
import skills.combat.CombatHelper;
import skills.combat.CombatInfo;

public class LightningBolt extends TargetSkill
{
    @Override
    public int getRange()
    {
        return 4;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        CombatHelper combatHelper = new CombatHelper(true, false, false, false, 1.2, "hurled a bolt of lightning at");
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
        return SkillType.MAGICAL;
    }

    @Override
    public String getName()
    {
        return "Lightning Bolt";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.ATTACK;
    }

    @Override
    public String getDescription()
    {
        return "Strike an enemy with a bolt of lightning.";
    }
}
