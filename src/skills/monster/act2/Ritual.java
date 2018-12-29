package skills.monster.act2;

import control.Map;
import entities.creatures.Creature;
import entities.Entity;
import entities.Floor;
import entities.factories.MonsterFactory;
import enums.SkillBehaviour;
import enums.SkillType;
import mapgeneration.data.providers.MonsterProvider;
import skills.SummonSkill;
import skills.VariedSummonSkill;

import java.io.Serializable;
import java.util.Collection;

public class Ritual extends VariedSummonSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }


    public Ritual(Collection<String> inSummonNames)
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
        return source.getName() + " performs a dark ritual.";
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
        return "Ritual";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.SUPPORT;
    }
}
