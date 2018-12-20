package mapgeneration.data.providers;

import control.Controller;
import entities.furniture.factories.*;

import java.util.Collection;
import java.util.Vector;

public class FurnitureProvider
{
    private Vector<FurnitureFactory> furnitureFactories;

    public FurnitureProvider()
    {
        furnitureFactories = new Vector<FurnitureFactory>();
    }

    public void addAll(Collection<FurnitureFactory> newFurnitureFactories)
    {
        furnitureFactories.addAll(newFurnitureFactories);
    }

    public FurnitureFactory choose(int level)
    {
        Vector<FurnitureFactory> filteredFactories = new Vector<FurnitureFactory>();
        for(FurnitureFactory factory: furnitureFactories)
        {
            if(level >= factory.getMinLevel() && level <= factory.getMaxLevel())
            {
                filteredFactories.add(factory);
            }
        }

        return filteredFactories.get(Controller.getGenerator().nextInt(filteredFactories.size()));
    }
}
