package entities.ai;

import entities.Monster;
import enums.Behaviour;
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

        return Behaviour.ATTACK;
    }

    public TurnType determineTurnType(boolean inRange)
    {
        Behaviour behaviour = determineBehaviour();

        switch(behaviour)
        {
            case RETREAT:
            {
                return TurnType.MOVE;
            }
            default:
            {
                return inRange ? TurnType.ACT : TurnType.MOVE;
            }
        }
    }
}
