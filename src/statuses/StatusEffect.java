package statuses;

import entities.Creature;

public abstract class StatusEffect
{
    private String name;
    private Creature target;

    public String getName(){return name;}

    public Creature getTarget(){return target;}

    // Default to doing nothing.
    public abstract String action();

    public StatusEffect(String inName, Creature inTarget)
    {
        name = inName;
        target = inTarget;
    }

    // Default to doing nothing.
    public abstract String onApplication();

    // Default to doing nothing.
    public abstract String onRemoval();
}
