package statuses;

import entities.creatures.Creature;

import java.io.Serializable;

public class Shell extends IntensityStatusEffect implements Serializable
{
    private static final String name = "Shell";
    private static final String description = "Increase target defense.";

    public Shell(Creature inTarget, int inRemainingTurns, int intensity)
    {
        super(name, description, inTarget, inRemainingTurns, intensity);
    }

    @Override
    public String action()
    {
        return "";
    }

    @Override
    public String onApplication()
    {
        int baseDefense = getTarget().getDefense();
        int change = getIntensity();
        getTarget().setDefense(baseDefense + change);
        return getTarget().getName() + " is protected by a shell.";
    }

    @Override
    public String onRemoval()
    {
        int baseDefense = getTarget().getDefense();
        int change = getIntensity();
        getTarget().setDefense(baseDefense - change);
        return getTarget().getName() + " is no longer protected by a shell.";
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
