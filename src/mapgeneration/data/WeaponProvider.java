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
        weaponFactories.add(new WeaponFactory(2,4,5,2,"sword1",1,1,1));
        weaponFactories.add(new WeaponFactory(2,4,10,1,"spear1",1,1,2));
        weaponFactories.add(new WeaponFactory(2,4,0,1,"bow1",4,1,2));
        weaponFactories.add(new WeaponFactory(1,4,10,1,"crossbow1",4,1,3));
        weaponFactories.add(new WeaponFactory(1,2,0,4,"staff1",1,1,3));
        weaponFactories.add(new WeaponFactory(1,4,20,1,"dagger1",1,1,4));
        weaponFactories.add(new WeaponFactory(3,4,0,1,"axe1",1,1,4));
        weaponFactories.add(new WeaponFactory(2,4,0,4,"sceptre1",4,1,1));
        weaponFactories.add(new WeaponFactory(4,7,5,5,"sword2",1,2,2));
        weaponFactories.add(new WeaponFactory(3,7,10,3,"spear2",1,2,3));
        weaponFactories.add(new WeaponFactory(4,7,0,3,"bow2",4,2,3));
        weaponFactories.add(new WeaponFactory(3,7,10,3,"crossbow2",4,2,4));
        weaponFactories.add(new WeaponFactory(2,4,0,7,"staff2",1,2,4));
        weaponFactories.add(new WeaponFactory(3,6,20,3,"dagger2",1,2,4));
        weaponFactories.add(new WeaponFactory(5,7,0,3,"axe2",1,2,4));
        weaponFactories.add(new WeaponFactory(3,5,0,7,"sceptre2",4,2,2));
        weaponFactories.add(new WeaponFactory(6,9,5,6,"sword3",1,3,3));
        weaponFactories.add(new WeaponFactory(6,9,10,4,"spear3",1,3,4));
        weaponFactories.add(new WeaponFactory(6,9,0,4,"bow3",4,3,4));
        weaponFactories.add(new WeaponFactory(5,9,10,4,"crossbow3",4,3,4));
        weaponFactories.add(new WeaponFactory(3,5,0,9,"staff3",1,3,4));
        weaponFactories.add(new WeaponFactory(5,7,20,4,"dagger3",1,3,4));
        weaponFactories.add(new WeaponFactory(7,9,0,4,"axe3",1,3,4));
        weaponFactories.add(new WeaponFactory(6,9,0,9,"sceptre3",4,3,3));
        weaponFactories.add(new WeaponFactory(8,11,5,5,"sword4",1,4,4));
        weaponFactories.add(new WeaponFactory(7,11,10,5,"spear4",1,4,4));
        weaponFactories.add(new WeaponFactory(8,11,0,5,"bow4",4,4,4));
        weaponFactories.add(new WeaponFactory(7,11,10,5,"crossbow4",4,4,4));
        weaponFactories.add(new WeaponFactory(4,6,0,11,"staff4",1,4,4));
        weaponFactories.add(new WeaponFactory(7,9,20,5,"dagger4",1,4,4));
        weaponFactories.add(new WeaponFactory(9,11,0,5,"axe4",4,1,4));
        weaponFactories.add(new WeaponFactory(7,9,0,11,"sceptre4",4,4,4));

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
