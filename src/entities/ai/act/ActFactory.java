package entities.ai.act;

import entities.creatures.Monster;
import enums.Behaviour;
import ui.mainwindow.Messages;

public class ActFactory
{
    public static Act construct(Behaviour behaviour, Monster monster, Messages messages)
    {
        switch(behaviour)
        {
            case ATTACK:
            {
                return new ActAttack(monster, messages);
            }
            case DEFEND:
            {
                return new ActDefend(monster, messages);
            }
            case RETREAT:
            {
                return new ActRetreat(monster, messages);
            }
            case SUPPORT:
            {
                return new ActSupport(monster, messages);
            }
            default:
            {
                System.out.println("ActFactory::construct - unexpected behaviour.");
                return new ActAttack(monster, messages);
            }
        }
    }
}
