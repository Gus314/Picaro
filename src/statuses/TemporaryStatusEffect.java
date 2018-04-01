package statuses;

import entities.Creature;

public abstract class TemporaryStatusEffect extends StatusEffect
{
    private int remainingTurns;

    public int getRemainingTurns(){return remainingTurns;}

    public void setRemainingTurns(int inRemainingTurns){remainingTurns = inRemainingTurns;}

    protected void decrementRemainingTurns(){remainingTurns--;}

    public TemporaryStatusEffect(String inName, Creature inTarget, int inRemainingTurns)
    {
        super(inName, inTarget);
        remainingTurns = inRemainingTurns;
    }
}
