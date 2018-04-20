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
        armourFactories.add(new ArmourFactory(ArmourType.CHEST, 1, 0, 3, 3, "shirt",1, 2 ));
        armourFactories.add(new ArmourFactory(ArmourType.HANDS, 1, 0, 3, 3, "mittens",1, 2));
        armourFactories.add(new ArmourFactory(ArmourType.HEAD, 1, 0, 3, 3, "hat",1, 2 ));
        armourFactories.add(new ArmourFactory(ArmourType.LEGS, 1, 0, 3, 3, "trousers",1, 2));
        armourFactories.add(new ArmourFactory(ArmourType.FEET, 1, 0, 3, 3, "shoes",1, 2 ));
        armourFactories.add(new ArmourFactory(ArmourType.CHEST, 2, 0, 6, 6, "leather chest",2, 3 ));
        armourFactories.add(new ArmourFactory(ArmourType.HANDS, 2, 0, 6, 6, "leather gloves",2, 3 ));
        armourFactories.add(new ArmourFactory(ArmourType.HEAD, 2, 0, 6, 6, "leather helmet",2, 3 ));
        armourFactories.add(new ArmourFactory(ArmourType.LEGS, 2, 0, 6, 6, "leather legs",2, 3 ));
        armourFactories.add(new ArmourFactory(ArmourType.FEET, 2, 0, 6, 6, "leather greaves",2, 3 ));
        armourFactories.add(new ArmourFactory(ArmourType.CHEST, 3, 0, 9, 9, "chain chest",3, 4 ));
        armourFactories.add(new ArmourFactory(ArmourType.HANDS, 3, 0, 9, 9, "chain gloves",3, 4 ));
        armourFactories.add(new ArmourFactory(ArmourType.HEAD, 3, 0, 9, 9, "chain helmet",3, 4 ));
        armourFactories.add(new ArmourFactory(ArmourType.LEGS, 3, 0, 9, 9, "chain legs",3, 4 ));
        armourFactories.add(new ArmourFactory(ArmourType.FEET, 3, 0, 9, 9, "chain greaves",3, 4 ));
        armourFactories.add(new ArmourFactory(ArmourType.CHEST, 4, 0, 12, 12, "plate chest",4, 4 ));
        armourFactories.add(new ArmourFactory(ArmourType.HANDS, 4, 0, 12, 12, "plate gloves",4, 4 ));
        armourFactories.add(new ArmourFactory(ArmourType.HEAD, 4, 0, 12, 12, "plate helmet",4, 4 ));
        armourFactories.add(new ArmourFactory(ArmourType.LEGS, 4, 0, 12, 12, "plate legs",4, 4 ));
        armourFactories.add(new ArmourFactory(ArmourType.FEET, 4, 0, 12, 12, "plate greaves",4, 4 ));
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


