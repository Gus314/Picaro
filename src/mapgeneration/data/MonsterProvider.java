package mapgeneration.data;

import control.Controller;
import control.Map;
import entities.factories.MonsterFactory;
import ui.mainwindow.Messages;

import java.util.Vector;

public class MonsterProvider
{
    private Vector<MonsterFactory> monsterFactories;

    public MonsterProvider(Messages messages, Map map)
    {
        monsterFactories = new Vector<MonsterFactory>();
        monsterFactories.add(new MonsterFactory('M',10,1,5,0,5,0,0,1,"mouse",map,messages,10,10,0,10,10,1,1));
        monsterFactories.add(new MonsterFactory('R',16,1,5,0,7,2,0,1,"rat",map,messages,10,10,0,14,14,1,1));
        monsterFactories.add(new MonsterFactory('V',16,1,5,0,7,0,0,1,"vole",map,messages,10,10,0,13,13,1,2));
        monsterFactories.add(new MonsterFactory('A',12,1,5,0,5,0,0,1,"ant",map,messages,10,0,10,19,19,1,3));
        monsterFactories.add(new MonsterFactory('S',14,1,5,10,5,2,0,1,"spider",map,messages,10,0,10,16,16,1,4));
        monsterFactories.add(new MonsterFactory('B',22,9,15,10,11,0,0,4,"bee",map,messages,15,20,0,19,18,2,4));
        monsterFactories.add(new MonsterFactory('C',25,7,13,5,15,3,0,1,"cat",map,messages,15,10,10,15,11,2,2));
        monsterFactories.add(new MonsterFactory('D',26,5,11,0,12,0,0,1,"deer",map,messages,15,0,15,12,15,2,3));
        monsterFactories.add(new MonsterFactory('Z',26,5,12,0,18,0,0,1,"zebra",map,messages,15,0,15,11,16,2,3));
        monsterFactories.add(new MonsterFactory('H',28,9,13,0,16,5,0,1,"hyena",map,messages,15,15,10,11,14,2,4));
        monsterFactories.add(new MonsterFactory('R',39,13,18,0,20,0,0,1,"rhino",map,messages,22,30,0,20,25,3,4));
        monsterFactories.add(new MonsterFactory('O',37,14,17,0,18,0,0,1,"ostrich",map,messages,22,20,10,23,24,3,3));
        monsterFactories.add(new MonsterFactory('H',30,15,20,10,15,0,0,4,"hawk",map,messages,22,15,15,25,23,3,4));
        monsterFactories.add(new MonsterFactory('B',35,12,18,0,17,0,0,1,"badger",map,messages,22,20,10,22,22,3,4));
        monsterFactories.add(new MonsterFactory('S',33,11,15,0,16,0,0,1,"stoat",map,messages,22,15,15,21,21,3,3));
        monsterFactories.add(new MonsterFactory('G',44,25,30,0,23,0,0,1,"grizzly",map,messages,35,20,20,28,28,4,4));
        monsterFactories.add(new MonsterFactory('W',40,21,27,5,20,0,0,1,"wolf",map,messages,35,30,10,26,24,4,4));
        monsterFactories.add(new MonsterFactory('L',42,24,28,5,22,0,0,1,"lion",map,messages,35,35,5,29,29,4,4));
        monsterFactories.add(new MonsterFactory('E',45,22,26,0,25,5,5,1,"elephant",map,messages,35,10,30,30,30,4,4));
        monsterFactories.add(new MonsterFactory('T',41,23,29,5,21,0,0,1,"tiger",map,messages,35,25,15,26,24,4,4));

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
