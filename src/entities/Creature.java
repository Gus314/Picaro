package entities;

import control.Map;
import ui.Messages;
import java.util.Random;

public abstract class Creature extends Entity
{
    private static Random generator = new Random();
    private Map map;
    private Messages messages;
    private int defense;
    private String name;
    private int maxLife;
    private int life;
    private int minDamage;
    private int maxDamage;
    private int critChance;
    private int blockChance;
    private int absorbChance;
    private int range;
    private int exp;
    private int level;

    public Creature(Character inCha, int inRow, int inColumn, Map inMap, Messages inMessages, int inDefense, String inName, int inMaxLife, int inLife, int inMinDamage, int inMaxDamage, int inCritChance, int inBlockChance, int inAbsorbChance, int inRange, int inExp, int inLevel)
    {
        super(inCha, inRow, inColumn);
        map = inMap;
        messages = inMessages;
        defense = inDefense;
        name = inName;
        maxLife = inMaxLife;
        life = inLife;
        minDamage = inMinDamage;
        maxDamage = inMaxDamage;
        critChance = inCritChance;
        blockChance = inBlockChance;
        absorbChance = inAbsorbChance;
        range = inRange;
        exp = inExp;
        level = inLevel;
    }

    protected Random getGenerator(){return generator;}

    public boolean blocksLineOfSight(){ return true;}

    protected boolean attack(Creature target)
    {
        if(getGenerator().nextInt(100 - target.getBlockChance()) == 0)
        {
            getMessages().addMessage("Attack was blocked!");
            return false;
        }

        int damage = getMinDamage() + generator.nextInt(getMaxDamage() - getMinDamage()) - target.getDefense();

        if(generator.nextInt(100 - target.getAbsorbChance()) == 0)
        {
            int newTargetLife = target.getLife() + damage;
            int maxTargetLife = target.getMaxLife();
            int amount = damage;
            if(newTargetLife > maxTargetLife)
            {
                amount = maxTargetLife - target.getLife();
            }
            target.setLife(newTargetLife);
            messages.addMessage("Attack was absorbed for " + amount + " hp!");
        }

        int targetLife = target.getLife();
        Boolean criticalHit = false;

        if(generator.nextInt(101 - getCritChance()) - 1 == 0)
        {
            damage = damage * 2;
            criticalHit = true;
        }
        String targetName = target.getName();

        if(damage <= 0) {
            messages.addMessage(targetName + " defense nullified attack!");
        }
        else {
            targetLife -= damage;
            target.setLife(targetLife);
            if (!criticalHit)
                messages.addMessage("Critical hit!");
            messages.addMessage(targetName + " took " + damage + " damage!");
             }

        return targetLife <= 0;
    }

    public void setLife(int inLife)
    {
        life = inLife;
    }

    public int getExp(){
        return exp;
    }

    public int getLevel(){
        return level;
    }

    public Map getMap() {
        return map;
    }

    public Messages getMessages() {
        return messages;
    }

    public int getDefense() {
        return defense;
    }

    public String getName() {
        return name;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public int getLife() {
        return life;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public int getCritChance() {
        return critChance;
    }

    public int getBlockChance() {
        return blockChance;
    }

    public int getAbsorbChance() {
        return absorbChance;
    }

    public int getRange() {
        return range;
    }

    public void setBlockChance(int inBlockChance)
    {
        blockChance = inBlockChance;
    }

    public void setAbsorbChance(int inAbsorbChance)
    {
        absorbChance = inAbsorbChance;
    }


    public void setLevel(int inLevel)
    {
        level = inLevel;
    }

    public void setMaxLife(int inMaxLife)
    {
        maxLife = inMaxLife;
    }

    public void setMinDamage(int inMinDamage)
    {
        minDamage = inMinDamage;
    }

    public void setMaxDamage(int inMaxDamage)
    {
        maxDamage = inMaxDamage;
    }

    public void setCritChance(int inCritChance)
    {
        critChance = inCritChance;
    }

    public void setDefense(int inDefense)
    {
        defense = inDefense;
    }

    public void setExp(int inExp)
    {
        exp = inExp;
    }
}
