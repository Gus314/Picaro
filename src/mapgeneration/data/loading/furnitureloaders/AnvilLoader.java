package mapgeneration.data.loading.furnitureloaders;

import entities.furniture.factories.AnvilFactory;
import entities.furniture.factories.FurnitureFactory;
import enums.WeaponModificationType;
import mapgeneration.data.loading.CsvLoader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class AnvilLoader
{
    private static final String filePath = "data\\Anvils.csv";
    private static String minLevelName = "minLevel";
    private static String maxLevelName = "maxLevel";
    private static String nameName = "name";
    private static String weaponModificationTypeName = "weaponModificationType";
    private static String amountName = "amount";

     private static Collection<FurnitureFactory> convert(Collection<Map<String, Object>> loaded)
    {
        Collection<FurnitureFactory> result = new ArrayList<FurnitureFactory>();
        for(Map<String, Object> entry: loaded)
        {
            int minLevel = (int) entry.get(minLevelName);
            int maxLevel = (int) entry.get(maxLevelName);
            String name = (String) entry.get(nameName);
            WeaponModificationType weaponModificationType = WeaponModificationType.valueOf((String) entry.get(weaponModificationTypeName));
            int amount = (int) entry.get(amountName);

            FurnitureFactory anvilFactory = new AnvilFactory(minLevel, maxLevel, name, weaponModificationType, amount);
            result.add(anvilFactory);
        }

        return result;
    }

    public static Collection<FurnitureFactory> load()
    {
        java.util.Map<String, Class> parameters = new LinkedHashMap<>();
        parameters.put(minLevelName, Integer.TYPE);
        parameters.put(maxLevelName, Integer.TYPE);
        parameters.put(nameName, String.class);
        parameters.put(weaponModificationTypeName, String.class);
        parameters.put(amountName, Integer.TYPE);

        Collection<Map<String, Object>> loaded = CsvLoader.load(filePath, parameters);
        Collection<FurnitureFactory> furnitureFactories = convert(loaded);
        return  furnitureFactories;
    }
}
