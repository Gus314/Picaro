package statuses;

import entities.Creature;

import java.io.Serializable;

public class Shield extends IntensityStatusEffect implements Serializable
{
    private static final String name = "Shield";
    private static final String description = "Increase target magic defense.";

    public Shield(Creature inTarget, int inRemainingTurns, int intensity)
    {
        super(name, description, inTarget, inRemainingTurns, intensity);
    }

    @Override
    public String action()
    {
        return "";
    }

    @Override
    public String onApplication()
    {
        int baseMagicDefense = getTarget().getMagicDefense();
        int change = getIntensity();
        getTarget().setMagicDefence(baseMagicDefense + change);
        return getTarget().getName() + " is protected by a shield.";
    }

    @Override
    public String onRemoval()
    {
        int baseMagicDefense = getTarget().getMagicDefense();
        int change = getIntensity();
        getTarget().setMagicDefence(baseMagicDefense - change);
        return getTarget().getName() + " is no longer protected by a shield.";
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
