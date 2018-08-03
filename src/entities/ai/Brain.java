package entities.ai;

import control.Controller;
import control.Map;
import entities.Monster;
import enums.Behaviour;
import enums.SkillBehaviour;
import enums.TurnType;

public class Brain
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

        return Behaviour.ATTACK;
    }

    public TurnType determineTurnType(boolean inRange, Behaviour behaviour)
    {
        switch(behaviour)
        {
            case RETREAT:
            {
                return TurnType.MOVE;
            }
            case SUPPORT:
            {
                return TurnType.ACT;
            }
            default:
            {
                return inRange ? TurnType.ACT : TurnType.MOVE;
            }
        }
    }
}
