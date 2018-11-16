package statuses;

import entities.creatures.Creature;

import java.io.Serializable;

public abstract class TemporaryStatusEffect extends StatusEffect implements Serializable, Cloneable
{
    private int remainingTurns;

    public int getRemainingTurns(){return remainingTurns;}

    public void decrementRemainingTurns(){remainingTurns--;}

    public TemporaryStatusEffect(TemporaryStatusEffect original)
    {
        super(original);
        remainingTurns = original.remainingTurns;
    }

    public TemporaryStatusEffect(String inName, String inDescription, Creature inTarget, int inRemainingTurns)
    {
        super(inName, inDescription, inTarget);
        remainingTurns = inRemainingTurns;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        TemporaryStatusEffect cloned = (TemporaryStatusEffect) super.clone();
        cloned.remainingTurns = remainingTurns;
        return cloned;
    }
}
