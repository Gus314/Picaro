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
        weaponFactories.add(new WeaponFactory(12,	14	,5,	2	,"sword" 	, 1 	, 1, 	  1));
        weaponFactories.add(new WeaponFactory(12,	14	,10,	1	,"spear" 	, 1 	, 1, 	  2));
        weaponFactories.add(new WeaponFactory(12,	14	,0	,1	,"bow" 	, 4 	, 1, 	  2));
        weaponFactories.add(new WeaponFactory(11,	14	,10	,1	,"crossbow" 	, 4 	, 1, 	  3));
        weaponFactories.add(new WeaponFactory(11,	12	,0	,4	,"staff" 	, 1 	, 1, 	  3));
        weaponFactories.add(new WeaponFactory(11,	14	,20	,1	,"dagger" 	, 1 	, 1, 	  4));
        weaponFactories.add(new WeaponFactory(13,	14	,0	,1	,"axe" 	, 1, 	 1, 	  4));
        weaponFactories.add(new WeaponFactory(12,	14	,0	,4	,"sceptre" 	, 4 	, 1, 	  1));
        weaponFactories.add(new WeaponFactory(19,	22	,5	,5	,"sword" 	, 1 	, 2, 	  2));
        weaponFactories.add(new WeaponFactory(18,	22	,10	,3	,"spear" 	, 1 	, 2, 	  3));
        weaponFactories.add(new WeaponFactory(19,	22	,0	,3	,"bow" 	, 4, 	 2, 	  3));
        weaponFactories.add(new WeaponFactory(18,	22	,10	,3	,"crossbow" 	, 4 	, 2, 	  4));
        weaponFactories.add(new WeaponFactory(17,	19	,0	,7	,"staff" 	, 1 	, 2, 	  4));
        weaponFactories.add(new WeaponFactory(18,	21	,20	,3	,"dagger" 	, 1 	, 2, 	  4));
        weaponFactories.add(new WeaponFactory(20,	22	,0	,3	,"axe" 	, 1, 	 2, 	  4));
        weaponFactories.add(new WeaponFactory(18,	20	,0	,7	,"sceptre" 	, 4 	, 2, 	  2));
        weaponFactories.add(new WeaponFactory(26,	29	,5	,6	,"sword" 	, 1 	, 3, 	  3));
        weaponFactories.add(new WeaponFactory(26,	29	,10	,4	,"spear" 	, 1 	, 3, 	  4));
        weaponFactories.add(new WeaponFactory(26,	29	,0	,4	,"bow" 	, 4, 	 3, 	  4));
        weaponFactories.add(new WeaponFactory(25,	29	,10	,4	,"crossbow" 	, 4 	, 3 	,  4));
        weaponFactories.add(new WeaponFactory(23,	25	,0	,9	,"staff" 	, 1 	, 3, 	  4));
        weaponFactories.add(new WeaponFactory(25,	27	,20	,4	,"dagger" 	, 1 	, 3, 	  4));
        weaponFactories.add(new WeaponFactory(27,	29	,0	,4	,"axe" 	, 1, 	 3, 	  4));
        weaponFactories.add(new WeaponFactory(26,	29	,0	,9	,"sceptre" 	, 4 	, 3, 	  3));
        weaponFactories.add(new WeaponFactory(33,	36	,5	,5	,"sword" 	, 1 	, 4, 	  4));
        weaponFactories.add(new WeaponFactory(32,	36	,10	,5	,"spear" 	, 1 	, 4,   4));
        weaponFactories.add(new WeaponFactory(33,	36	,0	,5	,"bow" 	, 4, 	 4, 	  4));
        weaponFactories.add(new WeaponFactory(32,	36	,10	,5	,"crossbow" 	, 4 	, 4, 	  4));
        weaponFactories.add(new WeaponFactory(29,	31	,0	,11	,"staff" 	, 1 	, 4, 	  4));
        weaponFactories.add(new WeaponFactory(32,	34	,20	,5	,"dagger" 	, 1 	, 4, 	  4));
        weaponFactories.add(new WeaponFactory(34,	36	,0	,5	,"axe", 	 1, 4, 	  4));
        weaponFactories.add(new WeaponFactory(32,	34	,0	,11,	"sceptre" 	, 4 	, 4, 	  4));
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
