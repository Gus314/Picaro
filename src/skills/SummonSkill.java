package skills;

import entities.Entity;
import entities.factories.AbstractEntityFactory;
import mapgeneration.data.providers.MonsterProvider;

import java.util.Collection;

public abstract class SummonSkill extends FloorSkill
{
    private AbstractEntityFactory summon;

    public AbstractEntityFactory getSummon()
    {
        return summon;
    }

    public void populate(MonsterProvider monsterProvider)
    {
        summon = monsterProvider.get(getSummonName());
    }

    private String summonName;
    private String getSummonName(){return summonName;}
    protected void setSummonName(String inSummonName){summonName = inSummonName;}
}
