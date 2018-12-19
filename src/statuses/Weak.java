package statuses;

import entities.creatures.Creature;

import java.io.Serializable;

public class Weak extends TemporaryStatusEffect implements Serializable
{
    private static final String name = "Weak";
    private static final String description = "Lose all pp.";

    public Weak(Weak original)
    {
        super(original);
    }

    public Weak(Creature inTarget, int inDuration)
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
        return getTarget().getName() + " is feeling weak.";
    }

    @Override
    public String onRemoval()
    {
        return getTarget().getName() + " is no longer feeling weak.";
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Weak cloned = (Weak) super.clone();
        return cloned;
    }
}
