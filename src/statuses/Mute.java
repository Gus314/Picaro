package statuses;

import entities.creatures.Creature;

import java.io.Serializable;

public class Mute extends TemporaryStatusEffect implements Serializable
{
    private static final String name = "Mute";
    private static final String description = "Lose all mp.";

    public Mute(Mute original)
    {
        super(original);
    }

    public Mute(Creature inTarget, int inDuration)
    {
        super(name, description, inTarget, inDuration);
    }

    public @Override String action()
    {
        getTarget().setMagicPoints(0);
        return "";
    }

    @Override
    public String onApplication()
    {
        getTarget().setMagicPoints(0);
        return getTarget().getName() + " has been rendered mute.";
    }

    @Override
    public String onRemoval()
    {
        return getTarget().getName() + " is no longer mute.";
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Mute cloned = (Mute) super.clone();
        return cloned;
    }
}
