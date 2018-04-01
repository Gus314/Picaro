package mapgeneration.data;

import control.Controller;
import entities.equipment.factories.ArmourFactory;

import java.util.Vector;

public class ArmourProvider
{
    private Vector<ArmourFactory> armourFactories;

    public ArmourProvider()
    {
        armourFactories = new Vector<ArmourFactory>();
        armourFactories.add(new ArmourFactory(5, 2, 4, 1,  "Chainmail",1));
        armourFactories.add(new ArmourFactory(7, 3, 6, 2,  "Platemail",2));
    }

    public ArmourFactory choose(int level)
    {
        Vector<ArmourFactory> filteredFactories = new Vector<ArmourFactory>();
        for(ArmourFactory factory: armourFactories)
        {
            if(factory.getLevel() == level)
            {
                filteredFactories.add(factory);
            }
        }

        return filteredFactories.get(Controller.getGenerator().nextInt(filteredFactories.size()));
    }
}
