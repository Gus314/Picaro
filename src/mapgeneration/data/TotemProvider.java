package mapgeneration.data;

import control.Controller;
import control.Map;
import entities.factories.TotemFactory;
import enums.Faction;
import statuses.Groggy;
import statuses.Poison;
import ui.mainwindow.Messages;

import java.util.Vector;

public class TotemProvider
{
    private Vector<TotemFactory> totemFactories;

    public TotemProvider(Messages messages, Map map)
    {
        totemFactories = new Vector<TotemFactory>();

        Groggy groggy = new Groggy(null, 3, 4);
        totemFactories.add(new TotemFactory(Faction.FOE, 50, 10, 0, 0, 8, "groggy obelisk", map, messages, 20, 10, 1, 4, groggy));
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
