package statuses;

import entities.creatures.Creature;
import enums.StatType;

import java.io.Serializable;

public class Wise extends TemporaryStatusEffect implements Serializable
{
    private int intensity;
    private static final String name = "Wise";
    private static final String description = "Gain intelligence.";
    private int totalCount;

    public Wise(Wise original)
    {
        super(original);
        intensity = original.intensity;
        totalCount = original.totalCount;
    }

    public Wise(Creature inTarget, int inDuration, int inIntensity)
    {
        super(name, description, inTarget, inDuration);
        intensity = inIntensity;
        totalCount = 0;
    }

    public @Override String action()
    {
        getTarget().changeStat(StatType.INTELLIGENCE, intensity);

        totalCount++;
        return "";
    }

    @Override
    public String onApplication()
    {
        return getTarget().getName() + " is feeling wise.";
    }

    @Override
    public String onRemoval()
    {
        getTarget().changeStat(StatType.INTELLIGENCE, intensity*totalCount*-1);

        return getTarget().getName() + " is no longer feeling wise.";
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Wise cloned = (Wise) super.clone();
        cloned.intensity = intensity;
        cloned.totalCount = totalCount;

        return cloned;
    }
}
