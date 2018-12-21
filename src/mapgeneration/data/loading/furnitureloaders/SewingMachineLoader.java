package mapgeneration.data.loading.furnitureloaders;

import entities.furniture.factories.FurnitureFactory;
import entities.furniture.factories.SewingMachineFactory;
import enums.ArmourModificationType;
import mapgeneration.data.loading.CsvLoader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class SewingMachineLoader
{
    private static final String filePath = "SewingMachines.csv";
    private static final String minLevelName = "minLevel";
    private static final String maxLevelName = "maxLevel";
    private static final String nameName = "name";
    private static final String armourModificationTypeName = "armourModificationType";
    private static final String intensityName = "intensity";

    private static Collection<FurnitureFactory> convert(Collection<Map<String, Object>> loaded)
    {
        Collection<FurnitureFactory> result = new ArrayList<FurnitureFactory>();
        for(Map<String, Object> entry: loaded)
        {
            int minLevel = (int) entry.get(minLevelName);
            int maxLevel = (int) entry.get(maxLevelName);
            String name = (String) entry.get(nameName);
            ArmourModificationType armourModificationType = ArmourModificationType.valueOf((String) entry.get(armourModificationTypeName));
            int intensity = (int) entry.get(intensityName);

            FurnitureFactory sewingMachineFactory = new SewingMachineFactory(minLevel, maxLevel, name, armourModificationType, intensity);
            result.add(sewingMachineFactory);
        }

        return result;
    }

    public static Collection<FurnitureFactory> load()
    {
        java.util.Map<String, Class> parameters = new LinkedHashMap<>();
        parameters.put(minLevelName, Integer.TYPE);
        parameters.put(maxLevelName, Integer.TYPE);
        parameters.put(nameName, String.class);
        parameters.put(armourModificationTypeName, String.class);
        parameters.put(intensityName, Integer.TYPE);

        Collection<Map<String, Object>> loaded = CsvLoader.load(filePath, parameters);
        Collection<FurnitureFactory> furnitureFactories = convert(loaded);
        return  furnitureFactories;
    }
}
