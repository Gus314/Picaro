package statuses;

import entities.Creature;
import enums.StatType;

import java.io.Serializable;

public class Recklessness extends StatusEffect implements Serializable
{
    private static final int intensity = 50;
    private static final String name = "Recklessness";
    private static final String description = "Greatly increase critical chance at the cost of defense.";
    private int critGained;
    private int defenseLost;

    public Recklessness(Recklessness original)
    {
        super(original);
    }

    public Recklessness(Creature inTarget)
    {
        super(name, description, inTarget);
        critGained = 0;
        defenseLost = 0;
    }

    public @Override String action()
    {
        // Could temporary defense changes cause problems?
        if(getTarget().getDefense() > 0)
        {
            defenseLost += getTarget().getDefense();
            getTarget().setDefense(0);
        }

        return "";
    }

    @Override
    public String onApplication()
    {
        int oldCrit = getTarget().getCritChance();
        getTarget().changeStat(StatType.CRITCHANCE, intensity);
        critGained = getTarget().getCritChance() - oldCrit;

        defenseLost = getTarget().getDefense();
        getTarget().setDefense(0);

        return getTarget().getName() + " is feeling reckless.";
    }

    @Override
    public String onRemoval()
    {
        getTarget().changeStat(StatType.CRITCHANCE, critGained * -1);
        getTarget().setDefense(getTarget().getDefense() + defenseLost);
        return getTarget().getName() + " is no longer feeling reckless.";
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
