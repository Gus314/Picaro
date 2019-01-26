package skills.player.mage;

import control.Coordinate;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.StatType;
import enums.Direction;
import skills.SelfSkill;
import skills.TargetSkill;
import enums.SkillType;
import skills.combat.CombatHelper;
import skills.combat.CombatInfo;

import java.io.Serializable;

public class Tornado extends SelfSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Surround self with a tornado, pushing away any nearby creatures.";
    }

    private static final int cost = 8;
    private static final SkillType skillType = SkillType.MAGICAL;
    private static final String name = "Tornado";

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

    @Override
    public String action(Creature source)
    {
        CombatHelper combatHelper = new CombatHelper(true, true, false, true, 1.0, "hurled a tornado against");
        String message = source.getName() + " summoned a tornado around themselves.";

        for(Creature target: source.getSurroundingCreatures())
        {
            CombatInfo combatInfo = combatHelper.calculateCombat(source, target);
            target.changeStat(StatType.LIFE, combatInfo.getLifeChange());

            boolean pushed = false;

            Coordinate startPosition = target.getPosition();
            Direction pushDirection = source.getPosition().determineDirection(target.getPosition());

            for(int currentAttemptedPush = 2; (currentAttemptedPush > 0) && (!pushed); currentAttemptedPush--)
            {
                Coordinate newPosition = startPosition;
                for(int i = 0; i < currentAttemptedPush; i++)
                {
                    newPosition = newPosition.move(pushDirection);
                }

                if(target.getMap().isEmpty(newPosition))
                {
                    target.setRow(newPosition.getRow());
                    target.setColumn(newPosition.getColumn());
                    pushed = true;
                }

            }
            message += "\n" + combatInfo.getMessage();
        }

        subtractCost(source);
        return message;
    }
}
