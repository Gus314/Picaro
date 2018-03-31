package entities.statuses;

import entities.Entity;

public abstract class StatusEffect
{
    private String name;
    private Entity target;

    public String getName(){return name;}

    public abstract void action();

    public StatusEffect(String inName, Entity inTarget)
    {
        name = inName;
        target = inTarget;
    }
}
