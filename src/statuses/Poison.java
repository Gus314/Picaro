package statuses;

import entities.Creature;

public class Poison extends IntensityStatusEffect
{
    private static final String name = "Poison";

    public Poison(Creature inTarget, int inDuration, int inIntensity)
    {
        super(name, inTarget, inDuration, inIntensity);
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
}
