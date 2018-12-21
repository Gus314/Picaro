package mapgeneration.data.loading;

import entities.equipment.factories.RelicFactory;
import enums.LoadableSkill;
import enums.LoadableStatus;
import mapgeneration.data.providers.RelicProvider;
import mapgeneration.data.providers.StatusProvider;
import statuses.StatusEffect;

import java.util.*;

public class RelicLoader
{
    private static final String filePath = "Relics.csv";
    private static final String statusName = "status";
    private static final String nameName = "name";
    private static final String minLevelName = "minLevel";
    private static final String maxLevelName = "maxLevel";
    private StatusProvider statusProvider;

    public RelicLoader(StatusProvider inStatusProvider)
    {
        statusProvider = inStatusProvider;
    }

    private Collection<RelicFactory> convert(Collection<Map<String, Object>> loaded)
    {
        Collection<RelicFactory> result = new ArrayList<RelicFactory>();
        for(Map<String, Object> entry: loaded)
        {
            String statusId = (String) entry.get(statusName);
            StatusEffect status = statusProvider.get(LoadableStatus.valueOf(statusId));
            String name = (String) entry.get(nameName);
            int minLevel = (int) entry.get(minLevelName);
            int maxLevel = (int) entry.get(maxLevelName);

            RelicFactory relicFactory = new RelicFactory(status, name, minLevel, maxLevel);
            result.add(relicFactory);
        }

        return result;
    }

    public RelicProvider load()
    {
        RelicProvider result = new RelicProvider();

        java.util.Map<String, Class> parameters = new LinkedHashMap<>();
        parameters.put(statusName, String.class);
        parameters.put(nameName, String.class);
        parameters.put(minLevelName, Integer.TYPE);
        parameters.put(maxLevelName, Integer.TYPE);

        Collection<Map<String, Object>> loaded = CsvLoader.load(filePath, parameters);
        Collection<RelicFactory> relicFactories = convert(loaded);
        result.addAll(relicFactories);

        return result;
    }
}