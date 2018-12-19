package statuses;

import entities.creatures.Creature;
import enums.StatType;

import java.io.Serializable;

// Note that the effect of stun is dealt with using movement methods.
public class Stun extends StatusEffect implements Serializable
{
    private static final String name = "Stun";
    private static final String description = "Become stunned and unable to move.";

    public Stun(Blind original)
    {
        super(original);
    }

    public Stun(Creature inTarget)
    {
        super(name, description, inTarget);
    }

    public @Override String action()
    {
        return getTarget().getName() + " is stunned.";
    }

    @Override
    public String onApplication()
    {
        return getTarget().getName() + " has become stunned.";
    }

    @Override
    public String onRemoval()
    {
        return getTarget().getName() + " is no longer stunned.";
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Stun cloned = (Stun) super.clone();
        return cloned;
    }
}
