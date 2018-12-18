package statuses;

import entities.creatures.Creature;
import enums.StatType;

import java.io.Serializable;

public class Strong extends TemporaryStatusEffect implements Serializable
{
    private int intensity;
    private static final String name = "Strong";
    private static final String description = "Gain attack.";
    private int totalCount;

    public Strong(Strong original)
    {
        super(original);
        intensity = original.intensity;
        totalCount = original.totalCount;
    }

    public Strong(Creature inTarget, int inDuration, int inIntensity)
    {
        super(name, description, inTarget, inDuration);
        intensity = inIntensity;
        totalCount = 0;
    }

    public @Override String action()
    {
        getTarget().changeStat(StatType.MINDAMAGE, intensity);
        getTarget().changeStat(StatType.MAXDAMAGE, intensity);

        totalCount++;
        return "";
    }

    @Override
    public String onApplication()
    {
        return getTarget().getName() + " is feeling strong.";
    }

    @Override
    public String onRemoval()
    {
        getTarget().changeStat(StatType.MINDAMAGE, intensity*totalCount*-1);
        getTarget().changeStat(StatType.MAXDAMAGE, intensity*totalCount*-1);

        return getTarget().getName() + " is no longer feeling strong.";
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Strong cloned = (Strong) super.clone();
        cloned.intensity = intensity;
        cloned.totalCount = totalCount;

        return cloned;
    }
}
