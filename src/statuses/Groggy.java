package statuses;

import entities.Creature;
import enums.StatType;

import java.io.Serializable;

public class Groggy extends IntensityStatusEffect implements Serializable
{
    private static final String name = "Groggy";
    private static final String description = "Reduced defense.";

    public Groggy(Creature inTarget, int inDuration, int inIntensity)
    {
        super(name, description, inTarget, inDuration, inIntensity);
    }

    public @Override String action()
    {
        return "";
    }

    @Override
    public String onApplication()
    {
        int change = getIntensity();
        getTarget().changeStat(StatType.DEFENSE, -change);
        return getTarget().getName() + " is feeling groggy.";
    }

    @Override
    public String onRemoval()
    {
        int baseDefense = getTarget().getDefense();
        int change = getIntensity();
        getTarget().changeStat(StatType.DEFENSE, change);
        return getTarget().getName() + " is no longer feeling groggy.";
    }
}
