package skills.player.hunter;

import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import enums.StatType;
import skills.AreaSkill;
import skills.combat.CombatHelper;
import skills.combat.CombatInfo;

import java.util.List;

public class TrickShot extends AreaSkill
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
        String message = source.getName() + " lets loose with a trick shot.";

        CombatHelper combatHelper = new CombatHelper(true, true, true, true, 1.0, "struck");

        for(Creature target: targets)
        {
            CombatInfo combatInfo = combatHelper.calculateCombat(source, target);
            target.changeStat(StatType.LIFE, combatInfo.getLifeChange());
            message += "\n" + combatInfo.getMessage();
        }

        subtractCost(source);
        return message;
    }

    @Override
    public int getCost()
    {
        return 8;
    }

    @Override
    public SkillType getSkillType()
    {
        return SkillType.PHYSICAL;
    }

    @Override
    public String getName()
    {
        return "Trick Shot";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.ATTACK;
    }

    @Override
    public String getDescription()
    {
        return "Strike at a group of targets all at once.";
    }
}
