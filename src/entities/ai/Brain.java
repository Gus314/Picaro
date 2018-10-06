package entities.ai;

import control.Controller;
import control.Map;
import entities.Monster;
import enums.Behaviour;
import enums.SkillBehaviour;
import enums.TurnType;

import java.io.Serializable;

public class Brain implements Serializable
{
    private Monster monster;

    public Brain(Monster inMonster)
    {
        monster = inMonster;
    }

    public Behaviour determineBehaviour()
    {
        double dLife = (double) monster.getLife();
        double dMaxLife = (double) monster.getMaxLife();
        if((dLife / dMaxLife) * 100.0 < 50.0)
        {
            return Behaviour.RETREAT;
        }

        if(monster.hasSkills(SkillBehaviour.SUPPORT) && Controller.getGenerator().nextInt(10) == 1)
        {
            return Behaviour.SUPPORT;
        }

        if(monster.hasSkills(SkillBehaviour.DEFEND) && Controller.getGenerator().nextInt(10) == 1)
        {
            return Behaviour.DEFEND;
        }

        if(monster.hasSkills(SkillBehaviour.RETREAT) && Controller.getGenerator().nextInt(10) == 1)
        {
            return Behaviour.RETREAT;
        }

        return Behaviour.ATTACK;
    }

    public TurnType determineTurnType(boolean inRange, Behaviour behaviour)
    {
        switch(behaviour)
        {
            case DEFEND:
            {
                boolean canActDefend = monster.hasSkills(SkillBehaviour.DEFEND);
                // TODO: Tidy this method.
                boolean shouldAct = canActDefend && (Controller.getGenerator().nextInt(2) == 1);
                return shouldAct ? TurnType.ACT : TurnType.MOVE;
            }
            case ATTACK:
            {
                return inRange ? TurnType.ACT : TurnType.MOVE;
            }
            case RETREAT:
            {
                boolean canActRetreat = monster.hasSkills(SkillBehaviour.RETREAT);
                // TODO: Tidy this method.
                boolean shouldAct = canActRetreat && (Controller.getGenerator().nextInt(2) == 1);
                return shouldAct ? TurnType.ACT : TurnType.MOVE;
            }
            case SUPPORT:
            {
                return TurnType.ACT;
            }
            default:
            {
                System.out.println("Brain::determineTurnType - unexpected behaviour.");
                return TurnType.MOVE;
            }
        }
    }
}
