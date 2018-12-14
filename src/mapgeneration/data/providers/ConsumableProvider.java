package mapgeneration.data.providers;

import control.Controller;
import entities.equipment.factories.ConsumableFactory;

import java.util.Collection;
import java.util.Vector;

public class ConsumableProvider
{
    private Vector<ConsumableFactory> consumableFactories;

    public ConsumableProvider()
    {
        consumableFactories = new Vector<ConsumableFactory>();
    }

    public void addAll(Collection<ConsumableFactory> newConsumables)
    {
        consumableFactories.addAll(newConsumables);
    }

    public ConsumableFactory choose(int level)
    {
        Vector<ConsumableFactory> filteredFactories = new Vector<ConsumableFactory>();
        for(ConsumableFactory factory: consumableFactories)
        {
            if(level >= factory.getMinLevel() && level <= factory.getMaxLevel())
            {
                filteredFactories.add(factory);
            }
        }

        return filteredFactories.get(Controller.getGenerator().nextInt(filteredFactories.size()));
    }
}