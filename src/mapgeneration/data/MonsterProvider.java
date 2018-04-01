package mapgeneration.data;

import control.Controller;
import control.Map;
import entities.factories.MonsterFactory;
import ui.Messages;

import java.util.Vector;

public class MonsterProvider
{
    private Vector<MonsterFactory> monsterFactories;

    public MonsterProvider(Messages messages, Map map)
    {
        monsterFactories = new Vector<MonsterFactory>();
        monsterFactories.add(new MonsterFactory('G', 20, 4, 6, 1, 5, 5, 0, 1, "Goblin", map, messages, 20, 10, 20, 4, 4, 1, 2));
        monsterFactories.add(new MonsterFactory('S', 5, 1, 2, 0, 2, 0, 0, 4, "Skirmisher", map, messages, 20, 15, 15, 1, 7, 1, 2));
        monsterFactories.add(new MonsterFactory('O', 40, 4, 6, 2, 10, 10, 0, 1, "Orc", map, messages, 40, 0, 20, 2, 1, 2, 3));
        monsterFactories.add(new MonsterFactory('E', 50, 6, 8, 5, 15, 15, 0, 1, "Elephant", map, messages, 40, 0, 10, 10, 1, 2, 3));
    }

    public MonsterFactory choose(int level)
    {
        Vector<MonsterFactory> filteredFactories = new Vector<MonsterFactory>();
        for(MonsterFactory factory: monsterFactories)
        {
            if(level >= factory.getMinLevel() && level <= factory.getMaxLevel())
            {
                filteredFactories.add(factory);
            }
        }

        return filteredFactories.get(Controller.getGenerator().nextInt(filteredFactories.size()));
    }
}