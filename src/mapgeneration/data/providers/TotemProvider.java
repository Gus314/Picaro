package mapgeneration.data.providers;

import control.Controller;
import control.Map;
import entities.factories.TotemFactory;
import enums.Faction;
import statuses.Groggy;
import statuses.Naive;
import statuses.Poison;
import statuses.Recklessness;
import ui.mainwindow.Messages;

import java.util.Collection;
import java.util.Vector;

public class TotemProvider
{
    private Vector<TotemFactory> totemFactories;



    public TotemProvider()
    {
        totemFactories = new Vector<TotemFactory>();
    }

    public void addAll(Collection<TotemFactory> newTotemFactories)
    {
        totemFactories.addAll(newTotemFactories);
    }

    public TotemFactory choose(int level)
    {
        Vector<TotemFactory> filteredFactories = new Vector<TotemFactory>();
        for(TotemFactory factory: totemFactories)
        {
            if(level >= factory.getMinLevel() && level <= factory.getMaxLevel())
            {
                filteredFactories.add(factory);
            }
        }

        return filteredFactories.get(Controller.getGenerator().nextInt(filteredFactories.size()));
    }
}
