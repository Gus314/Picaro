package mapgeneration.data;

import control.Controller;
import entities.equipment.factories.ArmourFactory;
import enums.ArmourType;

import java.util.Vector;

public class ArmourProvider
{
    private Vector<ArmourFactory> armourFactories;

    public ArmourProvider()
    {
        armourFactories = new Vector<ArmourFactory>();
        armourFactories.add(new ArmourFactory(ArmourType.CHEST, 5, 2, 4, 1,  "Chainmail",1, 2));
        armourFactories.add(new ArmourFactory(ArmourType.HANDS, 5, 2, 4, 1,  "Mittens",1, 2));
        armourFactories.add(new ArmourFactory(ArmourType.HEAD, 5, 2, 4, 1,  "Helmet",1, 2));
        armourFactories.add(new ArmourFactory(ArmourType.LEGS, 5, 2, 4, 1,  "Trousers",1, 2));
        armourFactories.add(new ArmourFactory(ArmourType.FEET, 5, 2, 4, 1,  "Shoes",1, 2));

        armourFactories.add(new ArmourFactory(ArmourType.CHEST, 7, 3, 6, 2,  "Platemail",2, 3));
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
