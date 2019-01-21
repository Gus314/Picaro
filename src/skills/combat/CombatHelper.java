package skills.combat;

import control.Controller;
import entities.creatures.Creature;

public class CombatHelper
{
    private boolean allowAbsorb;
    private boolean allowBlock;
    private boolean allowCritical;
    private boolean isPhysical;
    private double multiplier;
    private String verb;

    public CombatHelper(boolean inAllowAbsorb, boolean inAllowBlock, boolean inAllowCritical, boolean inIsPhysical, double inMultiplier, String inVerb)
    {
        allowAbsorb = inAllowAbsorb;
        allowBlock = inAllowBlock;
        allowCritical = inAllowCritical;
        isPhysical = inIsPhysical;
        multiplier = inMultiplier;
        verb = inVerb;
    }

    private int magicDamage(Creature source, Creature target)
    {
        int halfIntelligence = source.getIntelligence() / 2;
        int adjustedIntelligence = (int) Math.floor(halfIntelligence * multiplier);
        int damage = adjustedIntelligence + Controller.getGenerator().nextInt(adjustedIntelligence) - target.getMagicDefense();
        return (damage < 0) ? 0 : damage;
    }

    private int physicalDamage(Creature source, Creature target)
    {
        int minDamage = (int) Math.floor(source.getMinDamage() * multiplier);
        int maxDamage = (int) Math.floor(source.getMaxDamage() * multiplier);
        int damage = minDamage + Controller.getGenerator().nextInt(maxDamage - minDamage) - target.getDefense();
        return (damage < 0) ? 0 : damage;
    }

    private int magicDamage(Creature target, int baseDamage)
    {
        int adjustedbaseDamage = (int) Math.floor(baseDamage * multiplier);
        int damage = adjustedbaseDamage + Controller.getGenerator().nextInt(adjustedbaseDamage) - target.getMagicDefense();
        return (damage < 0) ? 0 : damage;
    }

    private int physicalDamage(Creature target, int baseDamage)
    {
        int adjustedbaseDamage = (int) Math.floor(baseDamage * multiplier);
        int damage = Controller.getGenerator().nextInt(adjustedbaseDamage) - target.getDefense();
        return (damage < 0) ? 0 : damage;
    }


    private boolean isBlocked(Creature target)
    {
        return (target.getBlockChance() == 100) ? true :
                (Controller.getGenerator().nextInt(100 - target.getBlockChance()) == 0);
    }

    private boolean isAbsorbed(Creature target)
    {
        return (target.getAbsorbChance() == 100) ? true :
                (Controller.getGenerator().nextInt(100 - target.getAbsorbChance()) == 0);
    }

    private boolean isCritical(Creature source)
    {
        return (source.getCritChance() == 100) ? true :
                (Controller.getGenerator().nextInt(101 - source.getCritChance()) - 1 == 0);
    }

    public CombatInfo calculateEnvironmentalDamage(String sourceName, Creature target, int baseDamage)
    {
        boolean absorbed = isAbsorbed(target);
        boolean blocked = isBlocked(target);
        int damage = isPhysical ? physicalDamage(target, baseDamage) : magicDamage(target, baseDamage);

        return new CombatInfo(absorbed, blocked,  false, damage, sourceName, target.getName(), verb);
    }

    public CombatInfo calculateCombat(Creature source, Creature target)
    {
        boolean absorbed = isAbsorbed(target);
        boolean critical = isCritical(source);
        boolean blocked = isBlocked(target);
        int damage = isPhysical ? physicalDamage(source, target) : magicDamage(source, target);

        return new CombatInfo(absorbed, blocked,  critical, damage, source.getName(), target.getName(), verb);
    }
}