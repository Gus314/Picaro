package entities.statuses;

import entities.Entity;

public abstract class TemporaryStatusEffect extends StatusEffect
{
    private int remainingTurns;

    public int getRemainingTurns(){return remainingTurns;}

    public TemporaryStatusEffect(String inName, Entity inTarget, int inRemainingTurns)
    {
        super(inName, inTarget);
        remainingTurns = inRemainingTurns;
    }
}
