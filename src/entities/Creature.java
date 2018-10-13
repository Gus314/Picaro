package entities;

import control.Controller;
import control.Map;
import enums.*;
import skills.Skill;
import skills.SummonSkill;
import statuses.StatusEffect;
import statuses.TemporaryStatusEffect;
import ui.mainwindow.Messages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Creature extends Entity implements Serializable
{
    private Map map;
    private Messages messages;
    private Faction faction;
    private int defense;
    private int maxLife;
    private int life;
    private int minDamage;
    private int maxDamage;
    private int critChance;
    private int blockChance;
    private int absorbChance;
    private int range;
    private int exp;
    private int physicalPoints;
    private int maxPhysicalPoints;
    private int magicPoints;
    private int maxMagicPoints;
    private int intelligence;
    private int magicDefense;
    private List<StatusEffect> statusEffects;
    private List<Skill> skills;

    public Creature(Character inCha, int inRow, int inColumn, Map inMap, Messages inMessages, Faction inFaction, int inDefense, String inName, int inMaxLife, int inLife, int inMinDamage, int inMaxDamage, int inCritChance, int inBlockChance, int inAbsorbChance, int inRange, int inExp,
                    int inPhysicalPoints, int inMaxPhysicalPoints, int inMagicPoints, int inMaxMagicPoints, int inIntelligence, int inMagicDefense)
    {
        super(inCha, inRow, inColumn, inName);
        map = inMap;
        messages = inMessages;
        faction = inFaction;
        defense = inDefense;
        maxLife = inMaxLife;
        life = inLife;
        minDamage = inMinDamage;
        maxDamage = inMaxDamage;
        critChance = inCritChance;
        blockChance = inBlockChance;
        absorbChance = inAbsorbChance;
        range = inRange;
        exp = inExp;
        physicalPoints = inPhysicalPoints;
        maxPhysicalPoints = inMaxPhysicalPoints;
        magicPoints = inMagicPoints;
        maxMagicPoints = inMaxMagicPoints;
        intelligence = inIntelligence;
        magicDefense = inMagicDefense;

        statusEffects = new ArrayList<StatusEffect>();
        skills = new ArrayList<Skill>();
    }

    public boolean matchingFaction(Creature other)
    {
        return faction == other.faction;
    }

    public Collection<StatusEffect> getStatusEffects(){return statusEffects;}

    public Faction getFaction()
    {
        return faction;
    }

    public void setMap(Map inMap)
    {
        map = inMap;
    }

    public int getIntelligence() { return intelligence; }

    public void setIntelligence(int inIntelligence){intelligence = inIntelligence;}

    public int getMagicDefense() { return magicDefense;}

    public void setMagicDefence(int inMagicDefence){magicDefense = inMagicDefence;}

    public int getSpellsCount()
    {
        int result = 0;
        for(Skill skill: skills)
        {
            if(skill.getSkillType() == SkillType.MAGICAL)
            {
                result++;
            }
        }
        return result;
    }

    public int getTechniquesCount()
    {
        int result = 0;
        for(Skill skill: skills)
        {
            if(skill.getSkillType() == SkillType.PHYSICAL)
            {
                result++;
            }
        }
        return result;
    }

    public List<Skill> getSkills()
    {
        return skills;
    }

    public void addSkill(Skill skill)
    {
        skills.add(skill);
    }

    public void addSkills(Collection<Skill> inSkills){skills.addAll(inSkills);}

    public boolean blocksLineOfSight(){ return true;}

    public void progressStatusEffects()
    {
        List<TemporaryStatusEffect> removableEffects = new ArrayList<TemporaryStatusEffect>();

        for(StatusEffect effect: statusEffects)
        {
            String actionMessage = effect.action();
            Player player = map.getPlayer();
            if(actionMessage.length() > 0 && map.isInLineOfSight(player, this, player.getSightRadius()))
            {
                messages.addMessage(actionMessage);
            }

            if(effect instanceof TemporaryStatusEffect)
            {
                TemporaryStatusEffect temporaryEffect = (TemporaryStatusEffect) effect;
                temporaryEffect.decrementRemainingTurns();
                if(temporaryEffect.getRemainingTurns() == 0)
                {
                   removableEffects.add(temporaryEffect);
                   messages.addMessage(effect.getName() + " expired for " + effect.getTarget().getName() + ".");
                }
            }
        }

        statusEffects.removeAll(removableEffects);
    }

    public void addStatusEffect(StatusEffect effect)
    {
        statusEffects.removeIf(e -> e.getClass() == effect.getClass());
        statusEffects.add(effect);
        messages.addMessage(effect.onApplication());
    }

    public void removeStatusEffect(StatusEffect effect)
    {
        messages.addMessage(effect.onRemoval());
        statusEffects.remove(effect);
    }

    public boolean attack(Creature target)
    {
        if(Controller.getGenerator().nextInt(100 - target.getBlockChance()) == 0)
        {
            getMessages().addMessage("Attack was blocked!");
            return false;
        }

        int damage = getMinDamage() + Controller.getGenerator().nextInt(getMaxDamage() - getMinDamage()) - target.getDefense();

        if(Controller.getGenerator().nextInt(100 - target.getAbsorbChance()) == 0)
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

        if(Controller.getGenerator().nextInt(101 - getCritChance()) - 1 == 0)
        {
            damage = damage * 4;
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

    protected Collection<Floor> findNearbyEmptyFloors(int range)
    {
        Collection<Floor> result = new ArrayList<Floor>();

        for(int i = -range; i < range; i++)
        {
            for(int j = -range; j < range; j++)
            {
                List<Entity> here = getMap().atPosition(getRow() + i, getColumn() + j, true);
                if(here.size() == 1 && here.get(0) instanceof Floor)
                {
                    result.add((Floor) here.get(0));
                }
            }
        }

        return result;
    }

    public boolean canUse(Skill skill)
    {
        if(skill instanceof SummonSkill && findNearbyEmptyFloors(((SummonSkill) skill).getRange()).size() == 0)
        {
            return false;
        }

        switch(skill.getSkillType())
        {
            case PHYSICAL:
            {
                return getPhysicalPoints() >= skill.getCost();
            }
            case MAGICAL:
            {
                return getMagicPoints() >= skill.getCost();
            }
            default:
            {
                System.out.println("Creature::canUse() - unexpected skill type.");
                return false;
            }
        }
    }

    public int getSightRadius()
    {
        return 8;
    }

    public String changeStat(StatType stat, int intensity)
    {
        String result = getName();
        int change = 0; // Intensity may be modified to ensure stat stays within limits, e.g. greater than or equal to 0.
        switch(stat)
        {
            case MP:
            {
                change = (intensity < 0) && ((intensity * -1) > maxMagicPoints) ? maxMagicPoints : intensity;
                maxMagicPoints = maxMagicPoints + change;
                result = result + " max mp ";
                break;
            }
            case PP:
            {
                change = (intensity < 0) && ((intensity * -1) > maxPhysicalPoints) ? maxPhysicalPoints : intensity;
                maxPhysicalPoints = maxPhysicalPoints + change;
                result = result + " max pp";
                break;
            }
            case ABSORBCHANCE:
            {
                change = (intensity < 0) && ((intensity * -1) > absorbChance) ? absorbChance : intensity;
                change = (change > 0) && (change + absorbChance <= 100) ? (100 - absorbChance) : change;
                absorbChance += change;
                result = result + " absorb chance";
                break;
            }
            case EXP:
            {
                change = (intensity < 0) && ((intensity * -1) > exp) ? exp : intensity;
                exp = exp + change;
                result = result + " exp ";
                break;
            }
            case LIFE:
            {
                change = (intensity < 0) && ((intensity * -1) > life) ? life : intensity;
                life = life + change;
                result = result + " life ";
            }
            case BLOCKCHANCE:
            {
                change = (intensity < 0) && ((intensity * -1) > blockChance) ? blockChance : intensity;
                change = (change > 0) && (change + blockChance <= 100) ? (100 - blockChance) : change;
                blockChance += change;
                result = result + " block chance";
            }
            case RANGE:
            {
                change = (intensity < 0) && ((intensity * -1) > range) ? range : intensity;
                range = range + change;
                result = result + " range";
                break;
            }
            case CRITCHANCE:
            {
                change = (intensity < 0) && ((intensity * -1) > critChance) ? critChance : intensity;
                change = (change > 0) && (change + critChance <= 100) ? (100 - critChance) : change;
                critChance += change;
                result = result + " crit chance";
            }
            case DEFENSE:
            {
                change = (intensity < 0) && ((intensity * -1) > defense) ? defense : intensity;
                defense = defense + change;
                result = result + " defense ";
                break;
            }
            case INTELLIGENCE:
            {
                change = (intensity < 0) && ((intensity * -1) > intelligence) ? intelligence : intensity;
                intelligence = intelligence + change;
                result = result + " intelligence ";
                break;
            }
            case MAXDAMAGE:
            {
                change = (intensity < 0) && ((intensity * -1) > maxDamage) ? maxDamage : intensity;
                maxDamage = maxDamage + change;
                result = result + " maxDamage ";
                break;
            }
            case MINDAMAGE:
            {
                change = (intensity < 0) && ((intensity * -1) > minDamage) ? minDamage : intensity;
                minDamage = minDamage + change;
                result = result + " minDamage ";
                break;
            }
        }

        return result + " changed by " + intensity + ".";
    }


    public boolean canMove()
    {
        int row = getRow();
        int column = getColumn();

        for(Direction direction: Direction.values())
        {
            List<Entity> here = getMap().atPosition(row + direction.rowShift(), column + direction.columnShift());
            if(Entity.passable(here))
            {
                return true;
            }
        }

        return false;
    }

    public void setLife(int inLife)
    {
        life = inLife;
    }

    public int getExp(){
        return exp;
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

    public int getPhysicalPoints() {
        return physicalPoints;
    }

    public void setPhysicalPoints(int physicalPoints) {
        this.physicalPoints = physicalPoints;
    }

    public int getMaxPhysicalPoints() {
        return maxPhysicalPoints;
    }

    public void setMaxPhysicalPoints(int maxPhysicalPoints) {
        this.maxPhysicalPoints = maxPhysicalPoints;
    }

    public int getMagicPoints() {
        return magicPoints;
    }

    public void setMagicPoints(int magicPoints) {
        this.magicPoints = magicPoints;
    }

    public int getMaxMagicPoints() {
        return maxMagicPoints;
    }

    public void setMaxMagicPoints(int maxMagicPoints) {
        this.maxMagicPoints = maxMagicPoints;
    }
}
