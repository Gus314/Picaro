package entities.ai;

import entities.Entity;
import entities.Monster;
import ui.mainwindow.Messages;

import java.util.ArrayList;
import java.util.Collection;

public class ActDefend extends Act
{
    public ActDefend(Monster inMonster, Messages inMessages)
    {
        super(inMonster, inMessages);
    }

    @Override
    public Collection<Entity> act(Entity entity)
    {
        System.out.println("ActDefend::act() - todo");
        return new ArrayList<Entity>();
        //TODO: Implement.
    }
}
