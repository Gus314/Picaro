package mapgeneration.data.providers;

import control.Controller;
import entities.equipment.factories.WeaponFactory;

import java.util.Collection;
import java.util.Vector;

public class WeaponProvider
{
    private Vector<WeaponFactory> weaponFactories;

    public WeaponProvider()
    {
        weaponFactories = new Vector<WeaponFactory>();
    }

    public void addAll(Collection<WeaponFactory> inWeaponFactories)
    {
        weaponFactories.addAll(inWeaponFactories);
    }

    public WeaponFactory choose(int level)
    {
        Vector<WeaponFactory> filteredFactories = new Vector<WeaponFactory>();
        for(WeaponFactory factory: weaponFactories)
        {
            if(level >= factory.getMinLevel() && level <= factory.getMaxLevel())
            {
                filteredFactories.add(factory);
            }
        }

        return filteredFactories.get(Controller.getGenerator().nextInt(filteredFactories.size()));
    }
}
