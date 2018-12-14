package mapgeneration.data.providers;

import control.Controller;
import entities.creatures.Player;
import entities.equipment.factories.RelicFactory;
import statuses.*;

import java.util.Collection;
import java.util.Vector;

public class RelicProvider
{
    private Vector<RelicFactory> relicFactories;

    public RelicProvider()
    {
        relicFactories = new Vector<RelicFactory>();
    }

    public void addAll(Collection<RelicFactory> newRelicFactories)
    {
        relicFactories.addAll(newRelicFactories);
    }

    public RelicFactory choose(int level)
    {
        Vector<RelicFactory> filteredFactories = new Vector<RelicFactory>();
        for(RelicFactory factory: relicFactories)
        {
            if(level >= factory.getMinLevel() && level <= factory.getMaxLevel())
            {
                filteredFactories.add(factory);
            }
        }

        return filteredFactories.get(Controller.getGenerator().nextInt(filteredFactories.size()));
    }
}
