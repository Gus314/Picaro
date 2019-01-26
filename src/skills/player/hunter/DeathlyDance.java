package skills.player.hunter;

import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import enums.StatType;
import skills.AreaSkill;
import skills.combat.CombatHelper;
import skills.combat.CombatInfo;
import statuses.Bleed;
import statuses.Poison;

import java.util.List;

public class DeathlyDance extends AreaSkill
{
    private static final int duration = 5;
    private static final int intensity = 5;

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
        String message = source.getName() + " performs a deathly dance.";

        CombatHelper combatHelper = new CombatHelper(true, true, true, true, 0.5, "dances with");
        for(Creature target: targets)
        {
            Poison poison = new Poison(target, duration, intensity);
            target.addStatusEffect(poison);

            Bleed bleed = new Bleed(target, duration, intensity);
            target.addStatusEffect(bleed);

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
        return "Deathy Dance";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.ATTACK;
    }

    @Override
    public String getDescription()
    {
        return "Perform a deathly dance, poisoning and cutting a group of enemies.";
    }
}
