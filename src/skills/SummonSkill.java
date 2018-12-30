package skills;

import entities.factories.AbstractEntityFactory;
import mapgeneration.data.providers.EntityProvider;

import java.util.Collection;
import java.util.Optional;

public abstract class SummonSkill extends FloorSkill
{
    private AbstractEntityFactory summon;

    public AbstractEntityFactory getSummon()
    {
        return summon;
    }

    public void populate(Collection<EntityProvider> entityProviders)
    {
        for(EntityProvider entityProvider: entityProviders)
        {
            Optional<AbstractEntityFactory> factory = entityProvider.get(summonName);
            if(factory.isPresent())
            {
                summon = factory.get();
                break;
            }
        }
    }

    private String summonName;
    protected void setSummonName(String inSummonName){summonName = inSummonName;}
}
