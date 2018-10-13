package entities.furniture;

import entities.Entity;
import entities.Player;
import entities.factories.AbstractEntityFactory;

public class Box extends Furniture
{
    public AbstractEntityFactory factory;

    public Box(int row, int column, String name, AbstractEntityFactory inFactory)
    {
        super('?', row, column, name);
        factory = inFactory;
    }

    @Override
    public void use(Player player)
    {
        if(!used)
        {
            Entity entity = factory.construct(player.getRow(), player.getColumn());
            player.getMap().addEntry(entity);
            used = true;
            player.getMessages().addMessage("The " + getName() + " opened to reveal a " + entity.getName()) ;
        }
        else
        {
            player.getMessages().addMessage("The " + getName() + " is already open.");
        }
    }
}
