package mapgeneration.data;

import control.Controller;
import control.Map;
import entities.factories.TotemFactory;
import enums.Faction;
import statuses.Groggy;
import statuses.Naive;
import statuses.Poison;
import statuses.Recklessness;
import ui.mainwindow.Messages;

import java.util.Vector;

public class TotemProvider
{
    private Vector<TotemFactory> totemFactories;

    public TotemProvider(Messages messages, Map map)
    {
        totemFactories = new Vector<TotemFactory>();

        Groggy groggy = new Groggy(null, 3, 4);
        totemFactories.add(new TotemFactory(Faction.FOE, 50, 10, 0, 0, 8, "groggy obelisk", map, messages, 0, 10, 1, 4, groggy));

        Recklessness recklessness = new Recklessness();
        totemFactories.add(new TotemFactory(Faction.FOE, 60, 12, 0, 0, 8, "recklessness obelisk", map, messages, 0, 12, 2, 4, recklessness));

        Naive naive = new Naive();
        totemFactories.add(new TotemFactory(Faction.FOE, 74, 16, 0, 0, 8, "naive obelisk", map, messages, 0, 18, 3, 4, naive));

        Poison poison = new Poison(null, 3, 1);
        totemFactories.add(new TotemFactory(Faction.FOE, 92, 15, 0, 0, 8, "poison obelisk", map, messages, 0, 15, 4, 4, poison));
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
