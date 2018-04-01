package statuses;

import entities.Creature;

public class Poison extends TemporaryStatusEffect
{
    private int intensity;
    private static final String name = "Poison";

    public Poison(Creature inTarget, int inDuration, int inIntensity)
    {
        super(name, inTarget, inDuration);
        intensity = inIntensity;
    }

    public @Override String action()
    {
       int damage = intensity;
       int currentLife = getTarget().getLife();
       if(damage > currentLife)
       {
           damage = currentLife;
       }

       getTarget().setLife(currentLife - damage);
       decrementRemainingTurns();
       String message = getTarget().getName() + " took " + damage + " damage from poison.";
       return message;
    }
}
