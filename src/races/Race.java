package races;

import entities.creatures.Player;

import java.io.Serializable;

public class Race implements Serializable
{
    private String name;

    public Race(String name, int initialLife, int initialDefense, int initialMinDamage, int initialMaxDamage,int initialCritChance, int initialBlockChance, int initialAbsorbChance,
                int initialMaxPhysicalPoints, int initialMaxMagicPoints, int initialIntelligence, int initialMagicDefence, int changeLife, int changeDefense, int changeMinDamage, int changeMaxDamage,
                int changeCritChance, int changeBlockChance, int changeAbsorbChance, int changeMaxPhysicalPoints, int changeMaxMagicPoints, int changeIntelligence, int changeMagicDefence)
    {
        this.name = name;
        this.initialLife = initialLife;
        this.initialDefense = initialDefense;
        this.initialMinDamage = initialMinDamage;
        this.initialMaxDamage = initialMaxDamage;
        this.initialCritChance = initialCritChance;
        this.initialBlockChance = initialBlockChance;
        this.initialAbsorbChance = initialAbsorbChance;
        this.initialMaxPhysicalPoints = initialMaxPhysicalPoints;
        this.initialMaxMagicPoints = initialMaxMagicPoints;
        this.initialIntelligence = initialIntelligence;
        this.initialMagicDefence = initialMagicDefence;
        this.changeLife = changeLife;
        this.changeDefense = changeDefense;
        this.changeMinDamage = changeMinDamage;
        this.changeMaxDamage = changeMaxDamage;
        this.changeCritChance = changeCritChance;
        this.changeBlockChance = changeBlockChance;
        this.changeAbsorbChance = changeAbsorbChance;
        this.changeMaxPhysicalPoints = changeMaxPhysicalPoints;
        this.changeMaxMagicPoints = changeMaxMagicPoints;
        this.changeIntelligence = changeIntelligence;
        this.changeMagicDefence = changeMagicDefence;
    }

    public @Override String toString(){return name;}

    private int initialLife = 100;
    private int initialDefense = 3;
    private int initialMinDamage = 4;
    private int initialMaxDamage = 8;
    private int initialCritChance = 10;
    private int initialBlockChance = 0;
    private int initialAbsorbChance = 0;
    private int initialMaxPhysicalPoints = 40;
    private int initialMaxMagicPoints = 50;
    private int initialIntelligence = 8;
    private int initialMagicDefence = 6;
    private int changeLife = 100;
    private int changeDefense = 3;
    private int changeMinDamage = 4;
    private int changeMaxDamage = 8;
    private int changeCritChance = 10;
    private int changeBlockChance = 0;
    private int changeAbsorbChance = 0;
    private int changeMaxPhysicalPoints = 40;
    private int changeMaxMagicPoints = 50;
    private int changeIntelligence = 8;
    private int changeMagicDefence = 6;

    public void levelUp(Player player)
    {
        // Note the order dependance on max vs current, e.g. with life.
        player.setMaxLife(player.getMaxLife() + changeLife);
        player.setLife(player.getMaxLife());
        player.setDefense(player.getDefense() + changeDefense);
        player.setMinDamage(player.getMinDamage() + changeMinDamage);
        player.setMaxDamage(player.getMaxDamage() + changeMaxDamage);
        player.setCritChance(player.getCritChance() + changeCritChance);
        player.setBlockChance(player.getBlockChance() + changeBlockChance);
        player.setAbsorbChance(player.getAbsorbChance() + changeAbsorbChance);
        player.setMaxPhysicalPoints(player.getMaxPhysicalPoints() + changeMaxPhysicalPoints);
        player.setPhysicalPoints(player.getMaxPhysicalPoints());
        player.setMaxMagicPoints(player.getMaxMagicPoints() + changeMaxMagicPoints);
        player.setMagicPoints(player.getMaxMagicPoints());
        player.setIntelligence(player.getIntelligence() + changeIntelligence);
        player.setMagicDefence(player.getMagicDefense() + changeMagicDefence);
    }

    public void initialise(Player player)
    {
        // Note the order dependance on max vs current, e.g. with life.
        player.setLife(initialLife);
        player.setMaxLife(initialLife);
        player.setDefense(initialDefense);
        player.setMinDamage(initialMinDamage);
        player.setMaxDamage(initialMaxDamage);
        player.setCritChance(initialCritChance);
        player.setBlockChance(initialBlockChance);
        player.setAbsorbChance(initialAbsorbChance);
        player.setPhysicalPoints(initialMaxPhysicalPoints);
        player.setMaxPhysicalPoints(initialMaxPhysicalPoints);
        player.setMagicPoints(initialMaxMagicPoints);
        player.setMaxMagicPoints(initialMaxMagicPoints);
        player.setIntelligence(initialIntelligence);
        player.setMagicDefence(initialMagicDefence);
    }

    public String getName(){return name;}
}
