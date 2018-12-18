package statuses;

import entities.creatures.Creature;

import java.io.Serializable;

public class Blind extends TemporaryStatusEffect implements Serializable
{
    private static final String name = "Blind";
    private static final String description = "Lose all pp.";

    public Blind(Blind original)
    {
        super(original);
    }

    public Blind(Creature inTarget, int inDuration)
    {
        super(name, description, inTarget, inDuration);
    }

    public @Override String action()
    {
        getTarget().setPhysicalPoints(0);
        return "";
    }

    @Override
    public String onApplication()
    {
        getTarget().setPhysicalPoints(0);
        return getTarget().getName() + " has gone blind.";
    }

    @Override
    public String onRemoval()
    {
        return getTarget().getName() + " is no longer blind.";
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Blind cloned = (Blind) super.clone();
        return cloned;
    }
}
