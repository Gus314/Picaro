package statuses;

import entities.creatures.Creature;

import java.io.Serializable;

public class Exposed extends TemporaryStatusEffect implements Serializable
{
    private static final String name = "Exposed";
    private static final String description = "Lose all defense.";
    private int removedDefense;

    public Exposed(Exposed original)
    {
        super(original);
        removedDefense = original.removedDefense;
    }

    public Exposed(Creature inTarget, int inDuration)
    {
        super(name, description, inTarget, inDuration);
        removedDefense = 0;
    }

    public @Override String action()
    {
        return "";
    }

    @Override
    public String onApplication()
    {
        removedDefense = getTarget().getDefense();
        getTarget().setDefense(0);
        return getTarget().getName() + " is feeling exposed.";
    }

    @Override
    public String onRemoval()
    {
        getTarget().setDefense(getTarget().getDefense() + removedDefense);
        return getTarget().getName() + " is no longer feeling exposed.";
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Exposed cloned = (Exposed) super.clone();
        cloned.removedDefense = removedDefense;

        return cloned;
    }
}
