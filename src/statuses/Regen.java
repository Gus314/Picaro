package statuses;

import entities.Creature;

import java.io.Serializable;

public class Regen extends StatusEffect implements Serializable
{
    private int intensity;
    private static final String name = "Regen";
    private static final String description = "Gain hp over time at a cost of mp.";

    public Regen(Regen original)
    {
        super(original);
        intensity = original.intensity;
    }

    public Regen(Creature inTarget, int inIntensity)
    {
        super(name, description, inTarget);
        intensity = inIntensity;
    }

    public @Override String action()
    {
        if(getTarget().getLife() == getTarget().getMaxLife())
        {
            return "";
        }

        int mp = getTarget().getMagicPoints();
        if(mp <= 0)
        {
            return getTarget().getName() + " had insufficient mp for regen.";
        }

        getTarget().setMagicPoints(mp-1);

        int healing = intensity;
        int maxLife = getTarget().getMaxLife();
        int currentLife = getTarget().getLife();

        if(healing + currentLife > maxLife)
        {
            healing = maxLife - currentLife;
        }

        getTarget().setLife(currentLife + healing);

        return (healing > 0) ? getTarget().getName() + " healed " + healing + " damage from regen." : "";
    }

    @Override
    public String onApplication()
    {
        return getTarget().getName() + " is regenerating.";
    }

    @Override
    public String onRemoval()
    {
        return getTarget().getName() + " is no longer regenerating.";
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Regen cloned = (Regen) super.clone();
        cloned.intensity = intensity;

        return cloned;
    }
}
