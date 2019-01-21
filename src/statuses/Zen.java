package statuses;

import entities.creatures.Creature;

import java.io.Serializable;

public class Zen extends TemporaryStatusEffect implements Serializable
{
    private static final String name = "Zen";
    private static final String description = "Reduce defenses to 0 but gain great amount of absorb and block.";
    private int removedDefense;
    private int removedMagicDefense;
    private int gainedAbsorb;
    private int gainedBlock;

    public Zen(Zen original)
    {
        super(original);
        removedDefense = original.removedDefense;
        removedMagicDefense = original.removedMagicDefense;
        gainedAbsorb = original.gainedAbsorb;
        gainedBlock = original.gainedBlock;
    }

    public Zen(Creature inTarget, int inDuration)
    {
        super(name, description, inTarget, inDuration);
        removedDefense = 0;
        removedMagicDefense = 0;
        gainedAbsorb = 0;
        gainedBlock = 0;
    }

    public @Override String action()
    {
        return "";
    }

    @Override
    public String onApplication()
    {
        int attemptedAbsorbGain = 25;
        int attemptedBlockGain = 25;
        gainedAbsorb = getTarget().getAbsorbChance() < (100 - attemptedAbsorbGain) ? attemptedAbsorbGain : (100 - getTarget().getAbsorbChance());
        gainedBlock = getTarget().getBlockChance() < (100 - attemptedBlockGain) ? attemptedBlockGain : (100 - getTarget().getBlockChance());
        removedMagicDefense = getTarget().getMagicDefense();
        removedDefense = getTarget().getDefense();

        getTarget().setAbsorbChance(getTarget().getAbsorbChance() + gainedAbsorb);
        getTarget().setBlockChance(getTarget().getBlockChance() + gainedBlock);
        getTarget().setDefense(0);
        getTarget().setMagicDefence(0);

        return getTarget().getName() + " is meditating.";
    }

    @Override
    public String onRemoval()
    {
        getTarget().setAbsorbChance(getTarget().getAbsorbChance() - gainedAbsorb > 0 ? getTarget().getAbsorbChance() - gainedAbsorb : 0);
        getTarget().setBlockChance(getTarget().getBlockChance() - gainedBlock > 0 ? getTarget().getBlockChance() - gainedBlock : 0);
        getTarget().setDefense(getTarget().getDefense() + removedDefense);
        getTarget().setMagicDefence(getTarget().getMagicDefense() + removedMagicDefense);

        return getTarget().getName() + " is no longer meditating.";
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Zen cloned = (Zen) super.clone();
        cloned.gainedBlock = gainedBlock;
        cloned.gainedAbsorb = gainedAbsorb;
        cloned.removedDefense = removedDefense;
        cloned.removedMagicDefense = removedMagicDefense;

        return cloned;
    }
}
