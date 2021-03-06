package skills.monster.act2;

import control.Map;
import entities.creatures.Creature;
import entities.Entity;
import entities.Floor;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.VariedSummonSkill;

import java.io.Serializable;
import java.util.Collection;

public class Raise extends VariedSummonSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }


    public Raise(Collection<String> inSummonNames)
    {
        addSummonNames(inSummonNames);
    }

    @Override
    public int getRange()
    {
        return 7;
    }

    @Override
    public String action(Creature source, Floor floor, Collection<Entity> additions)
    {
        Map map = source.getMap();
        if(map.atPosition(floor.getRow(), floor.getColumn()).size() > 1)
        {
            System.out.println("Raise::action - tile contains too many entities.");
        }
        else
        {
            additions.add(getSummon().construct(floor.getRow(), floor.getColumn()));
        }
        subtractCost(source);
        return source.getName() + " raises a creature from the dead.";
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
        return "Raise";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.SUPPORT;
    }
}
