package mapgeneration.data.loading.furnitureloaders;

import control.Controller;
import entities.factories.AbstractEntityFactory;
import entities.furniture.factories.BoxFactory;
import entities.furniture.factories.FurnitureFactory;
import mapgeneration.data.loading.CsvLoader;
import mapgeneration.data.providers.MonsterProvider;
import mapgeneration.data.providers.WeaponProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class BoxLoader
{
    private static final String filePath = "Boxes.csv";
    private static final String minLevelName = "minLevel";
    private static final String maxLevelname = "maxLevel";
    private static final String nameName = "name";


    private static Collection<FurnitureFactory> convert(Collection<Map<String, Object>> loaded, WeaponProvider weaponProvider, MonsterProvider monsterProvider)
    {
        Collection<FurnitureFactory> result = new ArrayList<FurnitureFactory>();
        for(Map<String, Object> entry: loaded)
        {
            int minLevel = (int) entry.get(minLevelName);
            int maxLevel = (int) entry.get(maxLevelname);
            String name = (String) entry.get(nameName);

            boolean isWeapon = (Controller.getGenerator().nextInt(2) == 1);
            int entityLevel = minLevel+1;
            AbstractEntityFactory entityFactory = isWeapon ? weaponProvider.choose(entityLevel) : monsterProvider.choose(entityLevel);
            FurnitureFactory boxFactory = new BoxFactory(minLevel, maxLevel, name, entityFactory);
            result.add(boxFactory);
        }

        return result;
    }

    public static Collection<FurnitureFactory> load(WeaponProvider weaponProvider, MonsterProvider monsterProvider)
    {
        java.util.Map<String, Class> parameters = new LinkedHashMap<>();
        parameters.put(minLevelName, Integer.TYPE);
        parameters.put(maxLevelname, Integer.TYPE);
        parameters.put(nameName, String.class);

        Collection<Map<String, Object>> loaded = CsvLoader.load(filePath, parameters);
        Collection<FurnitureFactory> furnitureFactories = convert(loaded, weaponProvider, monsterProvider);
        return  furnitureFactories;
    }
}
