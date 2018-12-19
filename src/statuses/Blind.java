package statuses;

import entities.creatures.Creature;
import enums.StatType;

import java.io.Serializable;

// Note that the effect of blindness is dealt with using line of sight checks.
public class Blind extends TemporaryStatusEffect implements Serializable
{
    private static final String name = "Blind";
    private static final String description = "Lose sight.";

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
        return "";
    }

    @Override
    public String onApplication()
    {
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
