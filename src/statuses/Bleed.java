package statuses;

import entities.creatures.Creature;

import java.io.Serializable;

public class Bleed extends IntensityStatusEffect implements Serializable
{
    private static final String name = "Bleed";
    private static final String description = "Take damage every turn.";

    public Bleed(Creature inTarget, int inDuration, int inIntensity)
    {
        super(name, description, inTarget, inDuration, inIntensity);
    }

    public @Override String action()
    {
        int damage = getIntensity();
        int currentLife = getTarget().getLife();
        if(damage > currentLife)
        {
            damage = currentLife;
        }

        getTarget().setLife(currentLife - damage);
        String message = getTarget().getName() + " took " + damage + " damage from bleeding.";
        return message;
    }

    @Override
    public String onApplication()
    {
        return getTarget().getName() + " is bleeding.";
    }

    @Override
    public String onRemoval()
    {
        return getTarget().getName() + " is no longer bleeding.";
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
