package statuses;

import entities.Creature;

import java.io.Serializable;

public abstract class TemporaryStatusEffect extends StatusEffect implements Serializable
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
