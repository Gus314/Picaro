package skills.monster.act4;

import control.Map;
import entities.creatures.Creature;
import entities.Entity;
import entities.Floor;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.VariedSummonSkill;

import java.io.Serializable;
import java.util.Collection;

public class Resurrect extends VariedSummonSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }


    public Resurrect(Collection<String> inSummonNames)
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
        return source.getName() + " resurrects a creature from the dead.";
    }

    @Override
    public int getCost()
    {
        return 40;
    }

    @Override
    public SkillType getSkillType()
    {
        return SkillType.PHYSICAL;
    }

    @Override
    public String getName()
    {
        return "Resurrect";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.SUPPORT;
    }
}
