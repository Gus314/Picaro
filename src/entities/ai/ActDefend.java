package entities.ai;

import entities.Monster;
import entities.Player;
import ui.mainwindow.Messages;

public class ActDefend extends Act
{
    public ActDefend(Monster inMonster, Messages inMessages)
    {
        super(inMonster, inMessages);
    }

    @Override
    public void act(Player player)
    {
        System.out.println("ActDefend::act() - todo");
        //TODO: Implement.
    }
}
