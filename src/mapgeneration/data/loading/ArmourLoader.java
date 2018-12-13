package mapgeneration.data.loading;

import entities.equipment.factories.ArmourFactory;
import enums.ArmourType;
import mapgeneration.data.providers.ArmourProvider;
import java.util.*;

public class ArmourLoader
{
    private static final String filePath = "data\\Armours.csv";
    private static final String armourTypeName = "armourType";
    private static final String blockChanceName = "blockChance";
    private static final String absorbChanceName = "absorbChance";
    private static final String defenseName = "defense";
    private static final String magicDefenseName = "magicDefense";
    private static final String nameName = "name";
    private static final String minLevelName = "minLevel";
    private static final String maxLevelName = "maxLevel";

    private static Collection<ArmourFactory> convert(Collection<Map<String, Object>> loaded)
    {
        Collection<ArmourFactory> result = new ArrayList<ArmourFactory>();
        for(Map<String, Object> entry: loaded)
        {
            ArmourType armourType = ArmourType.valueOf((String) entry.get(armourTypeName));
            int blockChance = (int) entry.get(blockChanceName);
            int absorbChance = (int) entry.get(absorbChanceName);
            int defense = (int) entry.get(defenseName);
            int magicDefense = (int) entry.get(magicDefenseName);
            String name = (String) entry.get(nameName);
            int minLevel = (int) entry.get(minLevelName);
            int maxLevel = (int) entry.get(maxLevelName);

            ArmourFactory armourFactory = new ArmourFactory(armourType, blockChance, absorbChance, defense, magicDefense, name, minLevel, maxLevel);
            result.add(armourFactory);
        }

        return result;
    }

    public static ArmourProvider load()
    {
        ArmourProvider result = new ArmourProvider();

        java.util.Map<String, Class> parameters = new LinkedHashMap<>();
        parameters.put(armourTypeName, String.class);
        parameters.put(blockChanceName, Integer.TYPE);
        parameters.put(absorbChanceName, Integer.TYPE);
        parameters.put(defenseName, Integer.TYPE);
        parameters.put(magicDefenseName, Integer.TYPE);
        parameters.put(nameName, String.class);
        parameters.put(minLevelName, Integer.TYPE);
        parameters.put(maxLevelName, Integer.TYPE);

        Collection<Map<String, Object>> loaded = CsvLoader.load(filePath, parameters);
        Collection<ArmourFactory> armourFactories = convert(loaded);
        result.addAll(armourFactories);

        return result;
    }
}
