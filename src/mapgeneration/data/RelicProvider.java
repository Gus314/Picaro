package mapgeneration.data;

import control.Controller;
import entities.equipment.factories.RelicFactory;
import enums.RelicEffect;
import java.util.Vector;

public class RelicProvider
{
    private Vector<RelicFactory> relicFactories;

    public RelicProvider()
    {
        relicFactories = new Vector<RelicFactory>();
        relicFactories.add(new RelicFactory(RelicEffect.CritChance, 2, "pin", 1, 2));
        relicFactories.add(new RelicFactory(RelicEffect.Defense, 5, "cover", 1, 2));
        relicFactories.add(new RelicFactory(RelicEffect.BlockChance, 4, "shield", 1, 2));
        relicFactories.add(new RelicFactory(RelicEffect.AbsorbChance, 2, "vampire", 1, 2));
        relicFactories.add(new RelicFactory(RelicEffect.Damage, 5, "sharp blade", 2, 3));
        relicFactories.add(new RelicFactory(RelicEffect.CritChance, 5, "sharp pin", 2, 3));
        relicFactories.add(new RelicFactory(RelicEffect.Defense, 5, "sturdy cover", 2, 3));
        relicFactories.add(new RelicFactory(RelicEffect.BlockChance, 5, "powerful shield", 2, 3));
        relicFactories.add(new RelicFactory(RelicEffect.AbsorbChance, 5, "crafty vampire", 2, 3));
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
