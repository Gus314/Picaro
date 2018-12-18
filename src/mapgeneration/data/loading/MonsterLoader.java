package mapgeneration.data.loading;

import entities.factories.MonsterFactory;
import enums.Faction;
import enums.LoadableSkill;
import mapgeneration.data.providers.MonsterProvider;
import mapgeneration.data.providers.SkillProvider;
import skills.Skill;
import ui.mainwindow.Messages;

import java.util.*;

public class MonsterLoader
{
    private static final String filePath = "data\\Monsters.csv";
    private static final String factionName = "faction";
    private static final String charaName = "chara";
    private static final String lifeName = "life";
    private static final String minDamageName = "minDamage";
    private static final String maxDamageName = "maxDamage";
    private static final String critDamageName = "critDamage";
    private static final String defenseName = "defense";
    private static final String blockChanceName = "blockChance";
    private static final String absorbChanceName = "absorbChance";
    private static final String rangeName = "range";
    private static final String nameName = "name";
    private static final String expName = "exp";
    private static final String ppName = "pp";
    private static final String mpName = "mp";
    private static final String intelligenceName = "intelligence";
    private static final String magicDefenseName = "magicDefense";
    private static final String minLevelName = "minLevel";
    private static final String maxLevelName = "maxLevel";
    private static final String skill1Name = "skill1";
    private static final String skill2Name = "skill2";
    private static final String skill3Name = "skill3";
    private static final String skill4Name = "skill4";
    private Messages messages;
    private control.Map map;
    private SkillProvider skillProvider;

    public MonsterLoader(Messages inMessages, control.Map inMap, SkillProvider inSkillProvider)
    {
        messages = inMessages;
        map = inMap;
        skillProvider = inSkillProvider;
    }

    private Collection<MonsterFactory> convert(Collection<Map<String, Object>> loaded)
    {
        Collection<MonsterFactory> result = new ArrayList<MonsterFactory>();
        for(Map<String, Object> entry: loaded)
        {
            Faction faction = Faction.valueOf((String) entry.get(factionName));
            Character chara = ((String) entry.get(charaName)).charAt(0);
            int life = (int) entry.get(lifeName);
            int minDamage = (int) entry.get(minDamageName);
            int maxDamage = (int) entry.get(maxDamageName);
            int critDamage = (int) entry.get(critDamageName);
            int defense = (int) entry.get(defenseName);
            int blockChance = (int) entry.get(blockChanceName);
            int absorbChance = (int) entry.get(absorbChanceName);
            int range = (int) entry.get(rangeName);
            String name = (String) entry.get(nameName);
            int exp = (int) entry.get(expName);
            int pp = (int) entry.get(ppName);
            int mp = (int) entry.get(mpName);
            int intelligence = (int) entry.get(intelligenceName);
            int magicDefense = (int) entry.get(magicDefenseName);
            int minLevel = (int) entry.get(minLevelName);
            int maxLevel = (int) entry.get(maxLevelName);

            Collection<Skill> skills = convertSkills(entry);

            MonsterFactory monsterFactory = new MonsterFactory(faction, chara, life, minDamage, maxDamage, critDamage, defense, blockChance, absorbChance, range,
                    name, map, messages, exp, pp, mp, intelligence, magicDefense, skills, minLevel, maxLevel);
            result.add(monsterFactory);
        }

        return result;
    }

    private Collection<Skill> convertSkills(Map<String, Object> entry)
    {
        Collection<Skill> skills = new ArrayList<Skill>();

        Collection<String> skillNames = new ArrayList<String>();
        skillNames.add(skill1Name);
        skillNames.add(skill2Name);
        skillNames.add(skill3Name);
        skillNames.add(skill4Name);

        for(String skillName: skillNames)
        {
            String skillId = (String) entry.get(skillName);
            if(skillId.length() > 1)
            {
                Skill skill = skillProvider.get(LoadableSkill.valueOf(skillId));
                skills.add(skill);
            }
        }

        return skills;
    }

    public MonsterProvider load()
    {
        MonsterProvider result = new MonsterProvider();

        java.util.Map<String, Class> parameters = new LinkedHashMap<>();
        parameters.put(factionName, String.class);
        parameters.put(charaName, String.class);
        parameters.put(lifeName, Integer.TYPE);
        parameters.put(minDamageName, Integer.TYPE);
        parameters.put(maxDamageName, Integer.TYPE);
        parameters.put(critDamageName, Integer.TYPE);
        parameters.put(defenseName, Integer.TYPE);
        parameters.put(blockChanceName, Integer.TYPE);
        parameters.put(absorbChanceName, Integer.TYPE);
        parameters.put(rangeName, Integer.TYPE);
        parameters.put(nameName, String.class);
        parameters.put(expName, Integer.TYPE);
        parameters.put(ppName, Integer.TYPE);
        parameters.put(mpName, Integer.TYPE);
        parameters.put(intelligenceName, Integer.TYPE);
        parameters.put(magicDefenseName, Integer.TYPE);
        parameters.put(minLevelName, Integer.TYPE);
        parameters.put(maxLevelName, Integer.TYPE);
        parameters.put(skill1Name, String.class);
        parameters.put(skill2Name, String.class);
        parameters.put(skill3Name, String.class);
        parameters.put(skill4Name, String.class);
        Collection<Map<String, Object>> loaded = CsvLoader.load(filePath, parameters);
        Collection<MonsterFactory> monsterFactories = convert(loaded);
        result.addAll(monsterFactories);

        return result;
    }
}
