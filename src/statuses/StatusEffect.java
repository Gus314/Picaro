package statuses;

import entities.Creature;

public abstract class StatusEffect
{
    private String name;
    private Creature target;
    private String description;

    public String getName(){return name;}

    public String getDescription(){return description;}

    public Creature getTarget(){return target;}

    // Default to doing nothing.
    public abstract String action();

    public StatusEffect(String inName, String inDescription, Creature inTarget)
    {
        name = inName;
        description = inDescription;
        target = inTarget;
    }

    // Default to doing nothing.
    public abstract String onApplication();

    // Default to doing nothing.
    public abstract String onRemoval();
}
