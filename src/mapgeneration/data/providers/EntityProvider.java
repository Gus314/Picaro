package mapgeneration.data.providers;

import control.Controller;
import entities.factories.AbstractEntityFactory;
import entities.factories.LeveledFactory;

import java.util.Collection;
import java.util.Optional;
import java.util.Vector;

public class EntityProvider<T extends AbstractEntityFactory>
{
    private Vector<T> entityFactories;

    public EntityProvider()
    {
        entityFactories = new Vector<T>();
    }

    public void addAll(Collection<T> newEntityFactories)
    {
        entityFactories.addAll(newEntityFactories);
    }

    public Optional<T> get(String name)
    {
        for(T entityFactory: entityFactories)
        {
            if(entityFactory.getName().equals(name))
            {
                return Optional.of(entityFactory);
            }
        }

        return Optional.empty();
    }

    public T choose(int level)
    {
        Vector<T> filteredFactories = new Vector<T>();
        for(T factory: entityFactories)
        {
            boolean valid = true;
            if(factory instanceof LeveledFactory)
            {
                LeveledFactory leveledFactory = (LeveledFactory) factory;
                valid = (level >= leveledFactory.getMinLevel() && level <= leveledFactory.getMaxLevel());
            }
            if(valid)
            {
                filteredFactories.add(factory);
            }
        }

        return filteredFactories.get(Controller.getGenerator().nextInt(filteredFactories.size()));
    }
}
