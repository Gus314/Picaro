package skills.monster.act1;

import control.Map;
import entities.creatures.Creature;
import entities.Entity;
import entities.Floor;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.SummonSkill;

import java.io.Serializable;
import java.util.Collection;

public class RequestAid extends SummonSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }


    public RequestAid(String inSummonName)
    {
        setSummonName(inSummonName);
    }

    @Override
    public int getRange()
    {
        return 3;
    }

    @Override
    public String action(Creature source, Floor floor, Collection<Entity> additions)
    {
        Map map = source.getMap();
        if(map.atPosition(floor.getRow(), floor.getColumn()).size() > 1)
        {
            System.out.println("RequestAid::action - tile contains too many entities.");
        }
        else
        {
            additions.add(getSummon().construct(floor.getRow(), floor.getColumn()));
        }
        subtractCost(source);
        return source.getName() + " calls for a friend.";
    }

    @Override
    public int getCost()
    {
        return 10;
    }

    @Override
    public SkillType getSkillType()
    {
        return SkillType.PHYSICAL;
    }

    @Override
    public String getName()
    {
        return "RequestAid";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.SUPPORT;
    }
}
