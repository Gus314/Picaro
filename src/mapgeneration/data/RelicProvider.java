package mapgeneration.data;

import control.Controller;
import entities.creatures.Player;
import entities.equipment.factories.RelicFactory;
import statuses.*;

import java.util.Vector;

public class RelicProvider
{
    private Vector<RelicFactory> relicFactories;

    public RelicProvider(Player player)
    {
        relicFactories = new Vector<RelicFactory>();

        Regen regen = new Regen(player, 2);
        relicFactories.add(new RelicFactory(regen, "regenerating pin", 1, 4));

        Recklessness recklessness = new Recklessness(player);
        relicFactories.add(new RelicFactory(recklessness, "reckless belt", 1, 4));

        Naive naive = new Naive(player);
        relicFactories.add(new RelicFactory(naive, "naive necklace", 1, 4));
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
