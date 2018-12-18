package statuses;

import entities.creatures.Creature;
import enums.StatType;

import java.io.Serializable;

public class CoolDown extends TemporaryStatusEffect implements Serializable
{
    private int intensity;
    private static final String name = "CoolDown";
    private static final String description = "Gain defense over time at a cost of attack.";
    private int totalCount;

    public CoolDown(CoolDown original)
    {
        super(original);
        intensity = original.intensity;
        totalCount = original.totalCount;
    }

    public CoolDown(Creature inTarget, int inDuration, int inIntensity)
    {
        super(name, description, inTarget, inDuration);
        intensity = inIntensity;
        totalCount = 0;
    }

    public @Override String action()
    {
        getTarget().changeStat(StatType.DEFENSE, intensity);
        getTarget().changeStat(StatType.MAGICDEFENSE, intensity);
        getTarget().changeStat(StatType.MINDAMAGE, intensity * -1);
        getTarget().changeStat(StatType.MAXDAMAGE, intensity * -1);
        getTarget().changeStat(StatType.INTELLIGENCE, intensity * -1);
        totalCount++;
        return "";
    }

    @Override
    public String onApplication()
    {
        return getTarget().getName() + " is cooling down.";
    }

    @Override
    public String onRemoval()
    {
        getTarget().changeStat(StatType.DEFENSE, intensity*totalCount*-1);
        getTarget().changeStat(StatType.MAGICDEFENSE, intensity*totalCount*-1);
        getTarget().changeStat(StatType.MINDAMAGE, intensity *totalCount);
        getTarget().changeStat(StatType.MAXDAMAGE, intensity * totalCount);
        getTarget().changeStat(StatType.INTELLIGENCE, intensity * totalCount);

        return getTarget().getName() + " is no longer cooling down.";
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        CoolDown cloned = (CoolDown) super.clone();
        cloned.intensity = intensity;
        cloned.totalCount = totalCount;

        return cloned;
    }
}
