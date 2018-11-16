package entities.furniture;

import entities.Entity;
import entities.creatures.Player;
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
        if(!getUsed())
        {
            Entity entity = factory.construct(player.getRow(), player.getColumn());
            player.getMap().addEntry(entity);
            setUsed(true);
            player.getMessages().addMessage("The " + getName() + " opened to reveal a " + entity.getName()) ;
        }
        else
        {
            player.getMessages().addMessage("The " + getName() + " is already open.");
        }
    }
}
