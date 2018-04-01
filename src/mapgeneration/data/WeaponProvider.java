package mapgeneration.data;

import control.Controller;
import entities.equipment.factories.WeaponFactory;

import java.util.Vector;

public class WeaponProvider
{
    private Vector<WeaponFactory> weaponFactories;

    public WeaponProvider()
    {
        weaponFactories = new Vector<WeaponFactory>();
        weaponFactories.add(new WeaponFactory(3, 5, 10, 0, "Sword",1, 1, 1));
        weaponFactories.add(new WeaponFactory(6, 10, 10, 0, "LongSword",2, 1, 1));
        weaponFactories.add(new WeaponFactory(4, 7, 20,0,  "BastardSword",2, 1, 1));
        weaponFactories.add(new WeaponFactory(2, 4, 30,0,  "Bow",2, 8, 1));
        weaponFactories.add(new WeaponFactory(4, 7, 20,0,  "Rocket Launcher",2, 6, 2));
    }

    public WeaponFactory choose(int level)
    {
        Vector<WeaponFactory> filteredFactories = new Vector<WeaponFactory>();
        for(WeaponFactory factory: weaponFactories)
        {
            if(factory.getLevel() == level)
            {
                filteredFactories.add(factory);
            }
        }

        return filteredFactories.get(Controller.getGenerator().nextInt(filteredFactories.size()));
    }
}
