package skills;

import control.Controller;
import entities.Entity;
import entities.factories.AbstractEntityFactory;
import entities.factories.MonsterFactory;
import mapgeneration.data.providers.MonsterProvider;
import mapgeneration.data.providers.TotemProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class VariedSummonSkill extends FloorSkill
{
    private List<AbstractEntityFactory> summons;

    public AbstractEntityFactory getSummon()
    {
        return summons.get(Controller.getGenerator().nextInt(summons.size()));
    }

    public void populate(MonsterProvider monsterProvider, TotemProvider totemProvider)
    {
        if(summons == null)
        {
            summons = new ArrayList<AbstractEntityFactory>();
        }

        for(String summonName: summonNames)
        {
            AbstractEntityFactory factory = (monsterProvider.get(summonName) != null) ? monsterProvider.get(summonName) : totemProvider.get(summonName);
            summons.add(factory);
        }
    }

    private Collection<String> summonNames;
    protected void addSummonNames(Collection<String> inSummonNames)
    {
        if(summonNames == null)
        {
            summonNames = new ArrayList<String>();
        }

        summonNames.addAll(inSummonNames);
    }
}
