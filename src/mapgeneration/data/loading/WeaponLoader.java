package mapgeneration.data.loading;

import entities.equipment.factories.WeaponFactory;
import mapgeneration.data.providers.WeaponProvider;

import java.lang.reflect.Constructor;
import java.util.*;

public class WeaponLoader
{
    private static final String filePath = "data\\Weapons.csv";
    private static final String minDamageName = "minDamage";
    private static final String maxDamageName = "maxDamage";
    private static final String critChanceName = "critChance";
    private static final String intelligenceName = "intelligence";
    private static final String nameName = "name";
    private static final String rangeName = "range";
    private static final String minLevelName = "minLevel";
    private static final String maxLevelName = "maxLevel";

    private static Collection<WeaponFactory> convert(Collection<Map<String, Object>> loaded)
    {
        Collection<WeaponFactory> result = new ArrayList<WeaponFactory>();
        for(Map<String, Object> entry: loaded)
        {
            int minDamage = (int) entry.get(minDamageName);
            int maxDamage = (int) entry.get(maxDamageName);
            int critChance = (int) entry.get(critChanceName);
            int intelligence = (int) entry.get(intelligenceName);
            String name = (String) entry.get(nameName);
            int range = (int) entry.get(rangeName);
            int minLevel = (int) entry.get(minLevelName);
            int maxLevel = (int) entry.get(maxLevelName);

            WeaponFactory weaponFactory = new WeaponFactory(minDamage, maxDamage, critChance, intelligence, name, range, minLevel, maxLevel);
            result.add(weaponFactory);
        }

        return result;
    }

    public static WeaponProvider load()
    {
        WeaponProvider result = new WeaponProvider();

        java.util.Map<String, Class> parameters = new LinkedHashMap<>();
        parameters.put(minDamageName, Integer.TYPE);
        parameters.put(maxDamageName, Integer.TYPE);
        parameters.put(critChanceName, Integer.TYPE);
        parameters.put(intelligenceName, Integer.TYPE);
        parameters.put(nameName, String.class);
        parameters.put(rangeName, Integer.TYPE);
        parameters.put(minLevelName, Integer.TYPE);
        parameters.put(maxLevelName, Integer.TYPE);

        Collection<Map<String, Object>> loaded = CsvLoader.load(filePath, parameters);
        Collection<WeaponFactory> weaponFactories = convert(loaded);
        result.addAll(weaponFactories);

        return result;
    }
}
