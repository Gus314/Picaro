package entities.ai;

import entities.Monster;
import entities.Player;
import ui.mainwindow.Messages;

public class ActSupport extends Act
{
    public ActSupport(Monster inMonster, Messages inMessages)
    {
        super(inMonster, inMessages);
    }

    @Override
    public void act(Player player)
    {
        System.out.println("ActSupport::act() - todo");
        //TODO: Implement.
    }
}
