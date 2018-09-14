package statuses;

import entities.Creature;

public abstract class IntensityStatusEffect extends TemporaryStatusEffect
{
    private int intensity;

    public IntensityStatusEffect(String inName, String inDescription, Creature inTarget, int inDuration, int inIntensity)
    {
        super(inName, inDescription, inTarget, inDuration);
        intensity = inIntensity;
    }

    public int getIntensity(){return intensity;}
}
