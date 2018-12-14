package mapgeneration.data.loading.furnitureloaders;

import entities.furniture.factories.FurnitureFactory;
import entities.furniture.factories.TeleporterFactory;
import mapgeneration.data.loading.CsvLoader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class TeleporterLoader
{
    private static final String filePath = "data\\Teleporters.csv";
    private static final String minLevelName = "minLevel";
    private static final String maxLevelName = "maxLevel";
    private static final String nameName = "name";

    private static Collection<FurnitureFactory> convert(Collection<Map<String, Object>> loaded)
    {
        Collection<FurnitureFactory> result = new ArrayList<FurnitureFactory>();
        for(Map<String, Object> entry: loaded)
        {
            int minLevel = (int) entry.get(minLevelName);
            int maxLevel = (int) entry.get(maxLevelName);
            String name = (String) entry.get(nameName);

            FurnitureFactory teleporterFactory = new TeleporterFactory(minLevel, maxLevel, name);
            result.add(teleporterFactory);
        }

        return result;
    }

    public static Collection<FurnitureFactory> load()
    {
        java.util.Map<String, Class> parameters = new LinkedHashMap<>();
        parameters.put(minLevelName, Integer.TYPE);
        parameters.put(maxLevelName, Integer.TYPE);
        parameters.put(nameName, String.class);

        Collection<Map<String, Object>> loaded = CsvLoader.load(filePath, parameters);
        Collection<FurnitureFactory> furnitureFactories = convert(loaded);
        return  furnitureFactories;
    }
}
