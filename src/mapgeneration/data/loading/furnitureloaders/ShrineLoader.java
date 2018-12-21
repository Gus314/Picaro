package mapgeneration.data.loading.furnitureloaders;

import entities.furniture.factories.FurnitureFactory;
import entities.furniture.factories.ShrineFactory;
import enums.StatType;
import mapgeneration.data.loading.CsvLoader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class ShrineLoader
{
    private static final String filePath = "Shrines.csv";
    private static final String minLevelName = "minLevel";
    private static final String maxLevelName = "maxLevel";
    private static final String nameName = "name";
    private static final String statTypeName = "statType";
    private static final String intensityName = "intensity";

    private static Collection<FurnitureFactory> convert(Collection<Map<String, Object>> loaded)
    {
        Collection<FurnitureFactory> result = new ArrayList<FurnitureFactory>();
        for(Map<String, Object> entry: loaded)
        {
            int minLevel = (int) entry.get(minLevelName);
            int maxLevel = (int) entry.get(maxLevelName);
            String name = (String) entry.get(nameName);
            StatType statType = StatType.valueOf((String) entry.get(statTypeName));
            int intensity = (int) entry.get(intensityName);

            FurnitureFactory shrineFactory = new ShrineFactory(minLevel, maxLevel, name, statType, intensity);
            result.add(shrineFactory);
        }

        return result;
    }

    public static Collection<FurnitureFactory> load()
    {
        java.util.Map<String, Class> parameters = new LinkedHashMap<>();
        parameters.put(minLevelName, Integer.TYPE);
        parameters.put(maxLevelName, Integer.TYPE);
        parameters.put(nameName, String.class);
        parameters.put(statTypeName, String.class);
        parameters.put(intensityName, Integer.TYPE);

        Collection<Map<String, Object>> loaded = CsvLoader.load(filePath, parameters);
        Collection<FurnitureFactory> furnitureFactories = convert(loaded);
        return  furnitureFactories;
    }
}
