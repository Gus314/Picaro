package skills.monster.act4;

import control.Coordinate;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.StatType;
import enums.Direction;
import skills.TargetSkill;
import enums.SkillType;
import skills.combat.CombatHelper;
import skills.combat.CombatInfo;

import java.io.Serializable;

public class Tsunami extends TargetSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    private static final int cost = 25;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Tsunami";
    private static final int range = 4;

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        CombatHelper combatHelper = new CombatHelper(true, true, false, true, 1.0, "called forth a tsunami");
        CombatInfo combatInfo = combatHelper.calculateCombat(source, target);
        target.changeStat(StatType.LIFE, combatInfo.getLifeChange());

        boolean pushed = false;

        Coordinate startPosition = target.getPosition();
        Direction pushDirection = source.getPosition().determineDirection(target.getPosition());

        for(int currentAttemptedPush = 4; (currentAttemptedPush > 0) && (!pushed); currentAttemptedPush--)
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
