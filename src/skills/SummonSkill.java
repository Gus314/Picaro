package skills;

import entities.Entity;
import entities.factories.AbstractEntityFactory;
import entities.factories.MonsterFactory;
import mapgeneration.data.providers.MonsterProvider;
import mapgeneration.data.providers.TotemProvider;

import java.util.Collection;

public abstract class SummonSkill extends FloorSkill
{
    private AbstractEntityFactory summon;

    public AbstractEntityFactory getSummon()
    {
        return summon;
    }

    public void populate(MonsterProvider monsterProvider, TotemProvider totemProvider)
    {
        summon = (monsterProvider.get(summonName) != null) ? monsterProvider.get(summonName) : totemProvider.get(summonName);
    }

    private String summonName;
    protected void setSummonName(String inSummonName){summonName = inSummonName;}
}
