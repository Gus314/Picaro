package statuses;

import entities.creatures.Creature;

import java.io.Serializable;

public class Burn extends IntensityStatusEffect implements Serializable
{
    private static final String name = "Burn";
    private static final String description = "Take damage every turn.";

    public Burn(Creature inTarget, int inDuration, int inIntensity)
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
        String message = getTarget().getName() + " took " + damage + " damage from burning.";
        return message;
    }

    @Override
    public String onApplication()
    {
        return getTarget().getName() + " is burning.";
    }

    @Override
    public String onRemoval()
    {
        return getTarget().getName() + " is no longer burning.";
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
