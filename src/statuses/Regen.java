package statuses;

import entities.Creature;

public class Regen extends StatusEffect
{
    private int intensity;
    private static final String name = "Regen";

    public Regen(Creature inTarget, int inIntensity)
    {
        super(name, inTarget);
        intensity = inIntensity;
    }

    public @Override String action()
    {
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
}
