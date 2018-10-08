package entities.ai.move;

import control.Map;
import entities.Monster;
import enums.Behaviour;

public class MoveFactory
{
    public static Move construct(Behaviour behaviour, Monster monster, Map map)
    {
        switch(behaviour)
        {
            case ATTACK:
            {
                return new MoveAttack(monster, map);
            }
            case DEFEND:
            {
                return new MoveDefend(monster, map);
            }
            case RETREAT:
            {
                return new MoveRetreat(monster, map);
            }
            case SUPPORT:
            {
                return new MoveSupport(monster, map);
            }
            default:
            {
                System.out.println("MoveFactory::construct() - unexpected behaviour.");
                return new MoveAttack(monster, map);
            }
        }
    }
}
