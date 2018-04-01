package statuses;

import entities.Creature;

public abstract class StatusEffect
{
    private String name;
    private Creature target;

    public String getName(){return name;}

    public Creature getTarget(){return target;}

    public abstract String action();

    public StatusEffect(String inName, Creature inTarget)
    {
        name = inName;
        target = inTarget;
    }
}
