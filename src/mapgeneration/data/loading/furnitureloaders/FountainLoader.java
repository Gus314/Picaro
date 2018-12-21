package mapgeneration.data.loading.furnitureloaders;

import entities.furniture.factories.FountainFactory;
import entities.furniture.factories.FurnitureFactory;
import enums.LoadableStatus;
import mapgeneration.data.loading.CsvLoader;
import mapgeneration.data.providers.StatusProvider;
import statuses.StatusEffect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class FountainLoader
{
    private static final String filePath = "Fountains.csv";
    private static final String minLevelName = "minLevel";
    private static final String maxLevelName = "maxLevel";
    private static final String nameName = "name";
    private static final String statusName = "status";

    private static Collection<FurnitureFactory> convert(Collection<Map<String, Object>> loaded, StatusProvider statusProvider)
    {
        Collection<FurnitureFactory> result = new ArrayList<FurnitureFactory>();
        for(Map<String, Object> entry: loaded)
        {
            int minLevel = (int) entry.get(minLevelName);
            int maxLevel = (int) entry.get(maxLevelName);
            String name = (String) entry.get(nameName);
            LoadableStatus statusId = LoadableStatus.valueOf((String) entry.get(statusName));
            StatusEffect status = statusProvider.get(statusId);

            FurnitureFactory fountainFactory = new FountainFactory(minLevel, maxLevel, name, status);
            result.add(fountainFactory);
        }

        return result;
    }

    public static Collection<FurnitureFactory> load(StatusProvider statusProvider)
    {
        java.util.Map<String, Class> parameters = new LinkedHashMap<>();
        parameters.put(minLevelName, Integer.TYPE);
        parameters.put(maxLevelName, Integer.TYPE);
        parameters.put(nameName, String.class);
        parameters.put(statusName, String.class);

        Collection<Map<String, Object>> loaded = CsvLoader.load(filePath, parameters);
        Collection<FurnitureFactory> furnitureFactories = convert(loaded, statusProvider);
        return  furnitureFactories;
    }
}
