package mapgeneration.data.loading;

import entities.factories.TotemFactory;
import enums.Faction;
import enums.LoadableStatus;
import mapgeneration.data.providers.EntityProvider;
import mapgeneration.data.providers.StatusProvider;
import statuses.StatusEffect;
import ui.mainwindow.Messages;

import java.util.*;

public class TotemLoader
{
    private static final String filePath = "Totems.csv";
    private static final String factionName = "faction";
    private static final String lifeName = "life";
    private static final String defenseName = "defense";
    private static final String blockChanceName = "blockChance";
    private static final String absorbChanceName = "absorbChance";
    private static final String rangeName = "range";
    private static final String nameName = "name";
    private static final String expName = "exp";
    private static final String magicDefenseName = "magicDefense";
    private static final String minLevelName = "minLevel";
    private static final String maxLevelName = "maxLevel";
    private static final String statusName = "status";
    private Messages messages;
    private control.Map map;
    private StatusProvider statusProvider;

    public TotemLoader(Messages inMessages, control.Map inMap, StatusProvider inStatusProvider)
    {
        messages = inMessages;
        map = inMap;
        statusProvider = inStatusProvider;
    }

    private Collection<TotemFactory> convert(Collection<Map<String, Object>> loaded)
    {
        Collection<TotemFactory> result = new ArrayList<TotemFactory>();
        for(Map<String, Object> entry: loaded)
        {
            Faction faction = Faction.valueOf((String) entry.get(factionName));
            int life = (int) entry.get(lifeName);
            int defense = (int) entry.get(defenseName);
            int blockChance = (int) entry.get(blockChanceName);
            int absorbChance = (int) entry.get(absorbChanceName);
            int range = (int) entry.get(rangeName);
            String name = (String) entry.get(nameName);
            int exp = (int) entry.get(expName);
            int magicDefense = (int) entry.get(magicDefenseName);
            int minLevel = (int) entry.get(minLevelName);
            int maxLevel = (int) entry.get(maxLevelName);
            String statusId = (String) entry.get(statusName);
            StatusEffect status = statusProvider.get(LoadableStatus.valueOf(statusId));
            TotemFactory totemFactory = new TotemFactory(faction, life, defense, blockChance, absorbChance, range, name, map, messages, exp, magicDefense, minLevel, maxLevel, status);
            result.add(totemFactory);
        }

        return result;
    }

    public EntityProvider<TotemFactory> load()
    {
        EntityProvider<TotemFactory> result = new EntityProvider<TotemFactory>();

        java.util.Map<String, Class> parameters = new LinkedHashMap<>();
        parameters.put(factionName, String.class);
        parameters.put(lifeName, Integer.TYPE);
        parameters.put(defenseName, Integer.TYPE);
        parameters.put(blockChanceName, Integer.TYPE);
        parameters.put(absorbChanceName, Integer.TYPE);
        parameters.put(rangeName, Integer.TYPE);
        parameters.put(nameName, String.class);
        parameters.put(expName, Integer.TYPE);
        parameters.put(magicDefenseName, Integer.TYPE);
        parameters.put(minLevelName, Integer.TYPE);
        parameters.put(maxLevelName, Integer.TYPE);
        parameters.put(statusName, String.class);
        Collection<Map<String, Object>> loaded = CsvLoader.load(filePath, parameters);
        Collection<TotemFactory> totemFactories = convert(loaded);
        result.addAll(totemFactories);

        return result;
    }
}
