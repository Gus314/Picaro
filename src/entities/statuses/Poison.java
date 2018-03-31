package entities.statuses;

import entities.Entity;

public class Poison extends TemporaryStatusEffect
{
    private int intensity;
    private static final String name = "Poison";

    public Poison(Entity inTarget, int inDuration, int inIntensity)
    {
        super(name, inTarget, inDuration);
        intensity = inIntensity;
    }

    public @Override void action()
    {
    }
}
