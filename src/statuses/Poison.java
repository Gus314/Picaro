package statuses;

import entities.Creature;

import java.io.Serializable;

public class Poison extends IntensityStatusEffect implements Serializable
{
    private static final String name = "Poison";
    private static final String description = "Take damage every turn.";

    public Poison(Creature inTarget, int inDuration, int inIntensity)
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
       String message = getTarget().getName() + " took " + damage + " damage from poison.";
       return message;
    }

    @Override
    public String onApplication()
    {
        return getTarget().getName() + " is poisoned.";
    }

    @Override
    public String onRemoval()
    {
        return getTarget().getName() + " is no longer poisoned.";
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
