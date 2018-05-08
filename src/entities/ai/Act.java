package entities.ai;

import entities.Monster;
import entities.Player;
import ui.mainwindow.Messages;

public abstract class Act
{
    private Monster monster;
    private Messages messages;

    protected Monster getMonster(){return monster;}

    protected Messages getMessages(){return messages;}

    public Act(Monster inMonster, Messages inMessages)
    {
        monster = inMonster;
        messages = inMessages;
    }

    public abstract void act(Player player);
}
