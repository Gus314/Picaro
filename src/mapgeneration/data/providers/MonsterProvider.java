package mapgeneration.data.providers;

import control.Controller;
import control.Map;
import entities.factories.MonsterFactory;
import enums.Faction;
import skills.Skill;
import skills.monster.*;
import ui.mainwindow.Messages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

public class MonsterProvider
{
    private Vector<MonsterFactory> monsterFactories;

    public MonsterProvider()
    {
        monsterFactories = new Vector<MonsterFactory>();
    }

    public void addAll(Collection<MonsterFactory> newMonsterFactories)
    {
        monsterFactories.addAll(newMonsterFactories);
    }

    public MonsterFactory get(String name)
    {
        for(MonsterFactory monsterFactory: monsterFactories)
        {
            if(monsterFactory.getName().equals(name))
            {
                return monsterFactory;
            }
        }

        System.out.println("MonsterProvider::get() - unable to find monster.");
        return null;
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
