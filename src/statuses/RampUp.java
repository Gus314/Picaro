package statuses;

import entities.creatures.Creature;
import enums.StatType;

import java.io.Serializable;

public class RampUp extends TemporaryStatusEffect implements Serializable
{
    private int intensity;
    private static final String name = "RampUp";
    private static final String description = "Gain attack over time at a cost of defense.";
    private int totalCount;

    public RampUp(RampUp original)
    {
        super(original);
        intensity = original.intensity;
        totalCount = original.totalCount;
    }

    public RampUp(Creature inTarget, int inDuration, int inIntensity)
    {
        super(name, description, inTarget, inDuration);
        intensity = inIntensity;
        totalCount = 0;
    }

    public @Override String action()
    {
        getTarget().changeStat(StatType.DEFENSE, intensity*-1);
        getTarget().changeStat(StatType.MAGICDEFENSE, intensity*-1);
        getTarget().changeStat(StatType.MINDAMAGE, intensity);
        getTarget().changeStat(StatType.MAXDAMAGE, intensity);
        getTarget().changeStat(StatType.INTELLIGENCE, intensity);
        totalCount++;
        return "";
    }

    @Override
    public String onApplication()
    {
        return getTarget().getName() + " is ramping up.";
    }

    @Override
    public String onRemoval()
    {
        getTarget().changeStat(StatType.DEFENSE, intensity*totalCount);
        getTarget().changeStat(StatType.MAGICDEFENSE, intensity*totalCount);
        getTarget().changeStat(StatType.MINDAMAGE, intensity *totalCount*-1);
        getTarget().changeStat(StatType.MAXDAMAGE, intensity * totalCount*-1);
        getTarget().changeStat(StatType.INTELLIGENCE, intensity * totalCount*-1);

        return getTarget().getName() + " is no longer ramping up.";
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        RampUp cloned = (RampUp) super.clone();
        cloned.intensity = intensity;
        cloned.totalCount = totalCount;

        return cloned;
    }
}
