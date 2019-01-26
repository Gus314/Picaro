package skills.player.hunter;

import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import enums.StatType;
import skills.AreaSkill;
import skills.combat.CombatHelper;
import skills.combat.CombatInfo;
import statuses.Bleed;

import java.util.List;

public class HundredCuts extends AreaSkill
{
    @Override
    public int getRange()
    {
        return 4;
    }

    @Override
    public int getRadius()
    {
        return 3;
    }

    @Override
    public String action(Creature source, List<Creature> targets)
    {
        String message = source.getName() + " let loose with a hundred cuts.";

        CombatHelper combatHelper = new CombatHelper(true, true, true, true, 0.5, "cut");

        for(Creature target: targets)
        {
            CombatInfo combatInfo = combatHelper.calculateCombat(source, target);
            target.changeStat(StatType.LIFE, combatInfo.getLifeChange());
            message += "\n" + combatInfo.getMessage();

            Bleed bleed = new Bleed(target, 4, 4);
            target.addStatusEffect(bleed);
        }

        subtractCost(source);
        return message;
    }

    @Override
    public int getCost() {
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
        return "Hundred cuts";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.ATTACK;
    }

    @Override
    public String getDescription()
    {
        return "cut multiple targets, causing them to bleed.";
    }
}
