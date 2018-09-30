package skills.player;

import control.Map;
import entities.Creature;
import entities.Entity;
import entities.Floor;
import entities.factories.MonsterFactory;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.SummonSkill;

import java.util.Collection;

public class CallEnemy extends SummonSkill
{
    public CallEnemy(MonsterFactory inSummon)
    {
        setSummon(inSummon);
    }

    @Override
    public int getRange()
    {
        return 3;
    }

    @Override
    public String action(Creature source, Floor floor, Collection<Entity> additions)
    {
        // TODO: FR - Remove this code smell.
        Map map = source.getMap();
        MonsterFactory monsterFactory = (MonsterFactory) getSummon();
        monsterFactory.setMap(map);
        monsterFactory.setMessages(source.getMessages());

        if(map.atPosition(floor.getRow(), floor.getColumn()).size() > 1)
        {
            System.out.println("RequestAid::action - tile contains too many entities.");
        }
        else
        {
            additions.add(getSummon().construct(floor.getRow(), floor.getColumn()));
        }
        subtractCost(source);
        return source.getName() + " calls a monster.";
    }

    @Override
    public int getCost()
    {
        return 10;
    }

    @Override
    public SkillType getSkillType()
    {
        return SkillType.MAGICAL;
    }

    @Override
    public String getName()
    {
        return "Call Enemy";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.SUPPORT;
    }
}
