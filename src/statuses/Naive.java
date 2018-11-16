package statuses;

import entities.Creature;

import java.io.Serializable;

public class Naive extends StatusEffect implements Serializable
{
    private static final String name = "Naive";
    private static final String description = "Double defense at the cost of magic defense.";
    private int lostMagicDefense = 0;
    private int gainedDefense = 0;

    public Naive(Creature inTarget)
    {
        super(name, description, inTarget);
    }

    public Naive(){super(name, description, null);}

    public @Override String action()
    {
        return "";
    }

    @Override
    public String onApplication()
    {
        lostMagicDefense = getTarget().getMagicDefense();
        gainedDefense = getTarget().getDefense();
        getTarget().setMagicDefence(0);
        getTarget().setDefense(gainedDefense*2);

        return getTarget().getName() + " is feeling naive.";
    }

    @Override
    public String onRemoval()
    {
        getTarget().setMagicDefence(lostMagicDefense);
        getTarget().setDefense(gainedDefense);

        return getTarget().getName() + " is no longer feeling naive.";
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
