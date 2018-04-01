package mapgeneration.data;

import control.Controller;
import entities.Player;
import entities.equipment.factories.ConsumableFactory;
import enums.ConsumableType;
import ui.Messages;

import java.util.Vector;

public class ConsumableProvider
{
    private Vector<ConsumableFactory> consumableFactories;

    public ConsumableProvider(Messages messages, Player player)
    {
        consumableFactories = new Vector<ConsumableFactory>();
        consumableFactories.add(new ConsumableFactory('p', 50, ConsumableType.RestoreHP, messages, "hp potion", player,1));
        consumableFactories.add(new ConsumableFactory('p', 75, ConsumableType.RestoreHP, messages, "large hp potion", player,2));
    }

    public ConsumableFactory choose(int level)
    {
        Vector<ConsumableFactory> filteredFactories = new Vector<ConsumableFactory>();
        for(ConsumableFactory factory: consumableFactories)
        {
            if(factory.getLevel() == level)
            {
                filteredFactories.add(factory);
            }
        }

        return filteredFactories.get(Controller.getGenerator().nextInt(filteredFactories.size()));
    }
}