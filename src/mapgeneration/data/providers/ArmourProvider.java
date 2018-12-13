package mapgeneration.data.providers;

import control.Controller;
import entities.equipment.factories.ArmourFactory;
import enums.ArmourType;

import java.util.Collection;
import java.util.Vector;

public class ArmourProvider
{
    private Vector<ArmourFactory> armourFactories;

    public ArmourProvider()
    {
        armourFactories = new Vector<ArmourFactory>();
    }

    public void addAll(Collection<ArmourFactory> inArmourFactories)
    {
        armourFactories.addAll(inArmourFactories);
    }

    public ArmourFactory choose(int level)
    {
        Vector<ArmourFactory> filteredFactories = new Vector<ArmourFactory>();
        for(ArmourFactory factory: armourFactories)
        {
            if(level >= factory.getMinLevel() && level <= factory.getMaxLevel())
            {
                filteredFactories.add(factory);
            }
        }

        return filteredFactories.get(Controller.getGenerator().nextInt(filteredFactories.size()));
    }
}


