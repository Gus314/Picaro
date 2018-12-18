package statuses;

import entities.creatures.Creature;

import java.io.Serializable;

public class Cynical extends TemporaryStatusEffect implements Serializable
{
    private static final String name = "Cynical";
    private static final String description = "Lose all magic defense.";
    private int removedMagicDefense;

    public Cynical(Cynical original)
    {
        super(original);
        removedMagicDefense = original.removedMagicDefense;
    }

    public Cynical(Creature inTarget, int inDuration)
    {
        super(name, description, inTarget, inDuration);
        removedMagicDefense = 0;
    }

    public @Override String action()
    {
        return "";
    }

    @Override
    public String onApplication()
    {
        removedMagicDefense = getTarget().getMagicDefense();
        getTarget().setMagicDefence(0);
        return getTarget().getName() + " is feeling cynical.";
    }

    @Override
    public String onRemoval()
    {
        getTarget().setMagicDefence(getTarget().getMagicDefense() + removedMagicDefense);
        return getTarget().getName() + " is no longer feeling cynical.";
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Cynical cloned = (Cynical) super.clone();
        cloned.removedMagicDefense = removedMagicDefense;

        return cloned;
    }
}
