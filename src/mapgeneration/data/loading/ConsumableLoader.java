package mapgeneration.data.loading;

import entities.creatures.Player;
import entities.equipment.factories.ConsumableFactory;
import enums.ConsumableType;
import mapgeneration.data.providers.ConsumableProvider;
import ui.mainwindow.Messages;

import java.util.*;

public class ConsumableLoader
{
    private static final String filePath = "Consumables.csv";
    private static final String charaName = "chara";
    private static final String amountName = "amount";
    private static final String typeName = "char";
    private static final String nameName = "name";
    private static final String minLevelName = "minLevel";
    private static final String maxLevelName = "maxLevel";
    private Messages messages;
    private Player player;

    public ConsumableLoader(Messages inMessages, Player inPlayer)
    {
        messages = inMessages;
        player = inPlayer;
    }

    private Collection<ConsumableFactory> convert(Collection<Map<String, Object>> loaded)
    {
        Collection<ConsumableFactory> result = new ArrayList<ConsumableFactory>();
        for(Map<String, Object> entry: loaded)
        {
            char chara = ((String) entry.get(charaName)).charAt(0);
            int amount = (int) entry.get(amountName);
            ConsumableType type = ConsumableType.valueOf((String) entry.get(typeName));
            String name = (String) entry.get(nameName);
            int minLevel = (int) entry.get(minLevelName);
            int maxLevel = (int) entry.get(maxLevelName);

            ConsumableFactory consumableFactory = new ConsumableFactory(chara, amount, type, messages, name, player, minLevel, maxLevel);
            result.add(consumableFactory);
        }

        return result;
    }

    public ConsumableProvider load()
    {
        ConsumableProvider result = new ConsumableProvider();

        java.util.Map<String, Class> parameters = new LinkedHashMap<>();
        parameters.put(charaName, String.class);
        parameters.put(amountName, Integer.TYPE);
        parameters.put(typeName, String.class);
        parameters.put(nameName, String.class);
        parameters.put(minLevelName, Integer.TYPE);
        parameters.put(maxLevelName, Integer.TYPE);

        Collection<Map<String, Object>> loaded = CsvLoader.load(filePath, parameters);
        Collection<ConsumableFactory> consumableFactories = convert(loaded);
        result.addAll(consumableFactories);

        return result;
    }
}
