package statuses;

import entities.Creature;

public abstract class TemporaryStatusEffect extends StatusEffect
{
    private int remainingTurns;

    public int getRemainingTurns(){return remainingTurns;}

    public void decrementRemainingTurns(){remainingTurns--;}

    public TemporaryStatusEffect(String inName, String inDescription, Creature inTarget, int inRemainingTurns)
    {
        super(inName, inDescription, inTarget);
        remainingTurns = inRemainingTurns;
    }
}
