package entities.ai;

import entities.Entity;
import entities.Monster;
import entities.Player;
import ui.mainwindow.Messages;

import java.util.Collection;

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

    public abstract Collection<Entity> act(Entity entity);
}
