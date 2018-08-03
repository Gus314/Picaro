package statuses;

import entities.Creature;

public class Shell extends IntensityStatusEffect
{
    public Shell(Creature inTarget, int inRemainingTurns, int intensity)
    {
        super("Shell", inTarget, inRemainingTurns, intensity);
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
}
