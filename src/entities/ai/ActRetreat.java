package entities.ai;

import entities.Entity;
import entities.Monster;
import ui.mainwindow.Messages;

import java.util.ArrayList;
import java.util.Collection;

public class ActRetreat extends Act
{
    public ActRetreat(Monster inMonster, Messages inMessages)
    {
        super(inMonster, inMessages);
    }

    @Override
    public Collection<Entity> act(Entity entity)
    {
        System.out.println("ActRetreat::act() - todo");
        //TODO: Implement.
        return new ArrayList<Entity>();
    }
}
