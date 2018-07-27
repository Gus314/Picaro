package mapgeneration.data;

import control.Controller;
import entities.Player;
import entities.equipment.factories.RelicFactory;
import enums.RelicEffect;
import statuses.Regen;

import java.util.Vector;

public class RelicProvider
{
    private Vector<RelicFactory> relicFactories;

    public RelicProvider(Player player)
    {
        Regen regen = new Regen(player, 10);

        relicFactories = new Vector<RelicFactory>();
        relicFactories.add(new RelicFactory(regen, "poisoned pin", 1, 2));
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
