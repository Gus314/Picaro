package skills;

import control.Controller;
import entities.factories.AbstractEntityFactory;
import mapgeneration.data.providers.EntityProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public abstract class VariedSummonSkill extends FloorSkill
{
    private List<AbstractEntityFactory> summons;

    public AbstractEntityFactory getSummon()
    {
        return summons.get(Controller.getGenerator().nextInt(summons.size()));
    }

    public void populate(Collection<EntityProvider> entityProviders)
    {
        if(summons == null)
        {
            summons = new ArrayList<AbstractEntityFactory>();
        }

        for(String summonName: summonNames)
        {
            for(EntityProvider entityProvider: entityProviders)
            {
                Optional<AbstractEntityFactory> factory = entityProvider.get(summonName);
                if(factory.isPresent())
                {
                    summons.add(factory.get());
                    break;
                }
            }
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
