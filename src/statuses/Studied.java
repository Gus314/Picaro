package statuses;

import entities.creatures.Creature;
import enums.StatType;

import java.io.Serializable;

public class Studied extends TemporaryStatusEffect implements Serializable
{
    private static final String name = "Studied";
    private static final String description = "Swap magic and physical defense.";
    private int defenseChange;
    private int magicDefenseChange;
    int lastDefense;
    int lastMagicDefense;

    public Studied(Studied original)
    {
        super(original);
        defenseChange = original.defenseChange;
        magicDefenseChange = original.magicDefenseChange;
    }

    public Studied(Creature inTarget, int inDuration)
    {
        super(name, description, inTarget, inDuration);
        defenseChange = 0;
        magicDefenseChange = 0;
    }

    public @Override String action()
    {
        if((lastDefense != getTarget().getDefense()) || (lastMagicDefense != getTarget().getMagicDefense()))
        {
            int furtherDefenseChange = getTarget().getDefense() - lastDefense;
            int furtherMagicDefenseChange = getTarget().getMagicDefense() - lastMagicDefense;

            defenseChange += furtherDefenseChange;
            magicDefenseChange += furtherMagicDefenseChange;

            getTarget().changeStat(StatType.DEFENSE, furtherDefenseChange);
            getTarget().changeStat(StatType.MAGICDEFENSE, furtherMagicDefenseChange);

            lastDefense = getTarget().getDefense();
            lastMagicDefense = getTarget().getMagicDefense();
        }

        return "";

    }

    @Override
    public String onApplication()
    {
        defenseChange = getTarget().getMagicDefense() - getTarget().getDefense();
        magicDefenseChange = getTarget().getDefense() - getTarget().getMagicDefense();
        getTarget().changeStat(StatType.DEFENSE, defenseChange);
        getTarget().changeStat(StatType.MAGICDEFENSE, magicDefenseChange);

        lastDefense = getTarget().getDefense();
        lastMagicDefense = getTarget().getMagicDefense();

        return getTarget().getName() + " is studying.";
    }

    @Override
    public String onRemoval()
    {
        getTarget().changeStat(StatType.DEFENSE, -defenseChange);
        getTarget().changeStat(StatType.MAGICDEFENSE, -magicDefenseChange);

        return getTarget().getName() + " is no longer studying.";
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Studied cloned = (Studied) super.clone();
        cloned.defenseChange = defenseChange;
        cloned.magicDefenseChange = magicDefenseChange;

        return cloned;
    }
}
