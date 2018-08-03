package statuses;

import entities.Creature;

public abstract class IntensityStatusEffect extends TemporaryStatusEffect
{
    private int intensity;

    public IntensityStatusEffect(String name, Creature inTarget, int inDuration, int inIntensity)
    {
        super(name, inTarget, inDuration);
        intensity = inIntensity;
    }

    public int getIntensity(){return intensity;}
}
