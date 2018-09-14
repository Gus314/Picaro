package statuses;

import entities.Creature;

public class Regen extends StatusEffect
{
    private int intensity;
    private static final String name = "Regen";
    private static final String description = "Increase target defense.";

    public Regen(Creature inTarget, int inIntensity)
    {
        super(name, description, inTarget);
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
}
