package entities.ai;

import entities.Monster;
import entities.Player;
import ui.mainwindow.Messages;

public class ActRetreat extends Act
{
    public ActRetreat(Monster inMonster, Messages inMessages)
    {
        super(inMonster, inMessages);
    }

    @Override
    public void act(Player player)
    {
        System.out.println("ActRetreat::act() - todo");
        //TODO: Implement.
    }
}
