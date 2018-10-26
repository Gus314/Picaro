package statuses;

import entities.Creature;

import java.io.Serializable;

public abstract class StatusEffect implements Serializable, Cloneable
{
    private String name;
    private Creature target;
    private String description;

    public String getName(){return name;}

    public String getDescription(){return description;}

    public Creature getTarget(){return target;}

    public void setTarget(Creature inTarget){target = inTarget;}

    // Default to doing nothing.
    public abstract String action();

    public StatusEffect(String inName, String inDescription, Creature inTarget)
    {
        name = inName;
        description = inDescription;
        target = inTarget;
    }

    public StatusEffect(StatusEffect original)
    {
        name = original.getName();
        description = original.getDescription();
        target = original.getTarget();
    }

    // Default to doing nothing.
    public abstract String onApplication();

    // Default to doing nothing.
    public abstract String onRemoval();

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        StatusEffect cloned = (StatusEffect) super.clone();
        cloned.name = name;
        cloned.target = target;
        cloned.description = description;

        return cloned;
    }
}
