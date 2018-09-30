package mapgeneration.data;

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

    public MonsterProvider(Messages messages, Map map)
    {
        Collection<Skill> skills = new ArrayList<Skill>();

        monsterFactories = new Vector<MonsterFactory>();

        MonsterFactory mouseFactory = new MonsterFactory(Faction.FOE, 'M',20,16,20,0,5,0,0,1,"mouse",map,messages,10,10,0,10,10, skills, 1,1);
        Bite bite = new Bite();
        skills.add(bite);
        MonsterFactory ratFactory = new MonsterFactory(Faction.FOE,'R',26,16,20,0,7,2,0,1,"rat",map,messages,10,10,0,14,14, skills,1,1);
        skills.clear();
        RequestAid requestAid = new RequestAid(ratFactory);
        mouseFactory.addSkill(requestAid);

        skills.add(new RollUp());
        monsterFactories.add(new MonsterFactory(Faction.FOE,'V',26,16,20,0,7,0,0,1,"vole",map,messages,10,10,0,13,13, skills,1,2));
        skills.clear();

        skills.add(new Flee());
        monsterFactories.add(new MonsterFactory(Faction.FOE,'A',22,16,20,0,5,0,0,1,"ant",map,messages,10,0,10,19,19, skills,1,3));
        skills.clear();

        skills.add(new PoisonFang());
        monsterFactories.add(new MonsterFactory(Faction.FOE,'S',24,16,20,10,5,2,0,1,"spider",map,messages,10,0,10,16,16, skills,1,4));
        skills.clear();

        monsterFactories.add(new MonsterFactory(Faction.FOE,'B',32,19,25,10,11,0,0,4,"bee",map,messages,15,20,0,19,18, skills,2,4));
        monsterFactories.add(new MonsterFactory(Faction.FOE,'C',35,17,23,5,15,3,0,1,"cat",map,messages,15,10,10,15,11, skills,2,2));
        monsterFactories.add(new MonsterFactory(Faction.FOE,'D',36,15,21,0,12,0,0,1,"deer",map,messages,15,0,15,12,15, skills,2,3));
        monsterFactories.add(new MonsterFactory(Faction.FOE,'Z',36,15,22,0,18,0,0,1,"zebra",map,messages,15,0,15,11,16, skills,2,3));
        monsterFactories.add(new MonsterFactory(Faction.FOE,'H',38,19,23,0,16,5,0,1,"hyena",map,messages,15,15,10,11,14, skills,2,4));
        monsterFactories.add(new MonsterFactory(Faction.FOE,'R',49,23,28,0,20,0,0,1,"rhino",map,messages,22,30,0,20,25, skills,3,4));
        monsterFactories.add(new MonsterFactory(Faction.FOE,'O',47,24,27,0,18,0,0,1,"ostrich",map,messages,22,20,10,23,24, skills,3,3));
        monsterFactories.add(new MonsterFactory(Faction.FOE,'H',40,25,30,10,15,0,0,4,"hawk",map,messages,22,15,15,25,23, skills,3,4));
        monsterFactories.add(new MonsterFactory(Faction.FOE,'B',45,22,28,0,17,0,0,1,"badger",map,messages,22,20,10,22,22, skills,3,4));
        monsterFactories.add(new MonsterFactory(Faction.FOE,'S',43,21,25,0,16,0,0,1,"stoat",map,messages,22,15,15,21,21, skills,3,3));
        monsterFactories.add(new MonsterFactory(Faction.FOE,'G',54,35,40,0,23,0,0,1,"grizzly",map,messages,35,20,20,28,28, skills,4,4));
        monsterFactories.add(new MonsterFactory(Faction.FOE,'W',50,31,37,5,20,0,0,1,"wolf",map,messages,35,30,10,26,24, skills,4,4));
        monsterFactories.add(new MonsterFactory(Faction.FOE,'L',52,34,38,5,22,0,0,1,"lion",map,messages,35,35,5,29,29, skills,4,4));
        monsterFactories.add(new MonsterFactory(Faction.FOE,'E',55,32,36,0,25,5,5,1,"elephant",map,messages,35,10,30,30,30, skills,4,4));
        monsterFactories.add(new MonsterFactory(Faction.FOE,'T',51,33,39,5,21,0,0,1,"tiger",map,messages,35,25,15,26,24, skills,4,4));

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
