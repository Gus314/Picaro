package entities;

import control.Map;
import entities.skills.Fireball;
import entities.skills.Heal;
import entities.skills.PoisonDart;
import enums.RelicEffect;
import ui.Messages;

import java.util.Random;
import java.util.Vector;

public class Player extends Creature
{	
	private Weapon weapon;
	private Armour armour;
	private Relic relic;
	private Vector<Item> items;
	private int range;
	private static Random generator = new Random();
	private static final int initialLife = 100;
	private static final int initialDefense = 3;
	private static final int initialMinDamage = 4;
	private static final int initialMaxDamage = 8;
    private static final int initialExp = 0;
    private static final int initialCritChance = 10;
    private static final int initialBlockChance = 0;
    private static final int initialAbsorbChance = 0;
    private static final int initialLevel = 1;
    private static final int initialRange = 1;
    private static final int initialMaxPhysicalPoints = 40;
    private static final int initialMaxMagicPoints = 50;


	public Player(Map inMap, Messages inMessages, String inName)
	{
		super('@', 0, 0, inMap, inMessages, initialDefense, inName, initialLife, initialLife, initialMinDamage, initialMaxDamage, initialCritChance, initialBlockChance, initialAbsorbChance, initialRange, initialExp,
				initialLevel, initialMaxPhysicalPoints, initialMaxPhysicalPoints, initialMaxMagicPoints, initialMaxMagicPoints);
		items = new Vector<Item>();
		weapon = null;
		armour = null;
		relic = null;
		addSkill(new PoisonDart());
		addSkill(new Fireball());
		addSkill(new Heal());
	}

	public boolean blocksLineOfSight(){ return true;}

	public void addItem(Item inItem)
	{
		items.add(inItem);
	}
	
	public Vector<Item> getItems()
	{
		return items;
	}

	public @Override int getRange()
	{
		int result = range;

		if(weapon != null)
		{
			result = weapon.getRange();
		}

		return result;
	}

	public @Override int getMinDamage()
	{
		int result = super.getMinDamage();
		
		if(weapon!=null)
			result+= weapon.getMinDamage();
		
		if(relic!=null && relic.getEffect()== RelicEffect.Damage)
			result+= relic.getAmount();
		
		return result;
	}

	public @Override int getMaxDamage()
	{
		int result = super.getMaxDamage();
		
		if(weapon!=null)
			result+= weapon.getMaxDamage();
		
		if(relic!=null && relic.getEffect()== RelicEffect.Damage)
			result+= relic.getAmount();
		
		return result;
	}
	
	public @Override int getCritChance()
	{
		int result = super.getCritChance();
		
		if(weapon!=null)
			result+= weapon.getCritChance();
		
		if(relic!=null && relic.getEffect()== RelicEffect.CritChance)
			result+= relic.getAmount();
		
		return result;
	}
	
	public @Override int getDefense()
	{
		int result = super.getDefense();
		
		if(armour!=null)
			result+= armour.getDefense();
		
		if(relic!=null && relic.getEffect()== RelicEffect.Defense)
			result+= relic.getAmount();
		
		return result;
	}
	
	public void setWeapon(Weapon inWeapon)
	{
		weapon = inWeapon;
	}
	
	public Weapon getWeapon()
	{
		return weapon;
	}
	
	public void setArmour(Armour inArmour)
	{
		armour = inArmour;
	}
	
	public Armour getArmour()
	{
		return armour;
	}
	
	public Relic getRelic()
	{
		return relic;
	}

	
	public void setRelic(Relic inRelic)
	{
		relic = inRelic;
	}
	
	public @Override int getBlockChance()
	{
		int result = super.getBlockChance();
		
		if(armour!=null)
			result+= armour.getBlockChance();
		
		if(relic!=null && relic.getEffect()== RelicEffect.BlockChance)
			result+= relic.getAmount();
		
		return result;
	}
	
	public @Override int getAbsorbChance()
	{
		int result = super.getAbsorbChance();
		
		if(armour!=null)
			result+= armour.getAbsorbChance();
		
		if(relic!=null && relic.getEffect()== RelicEffect.AbsorbChance)
			result+= relic.getAmount();
		
		return result;
	}

	public boolean attack(Monster monster)
	{
		boolean killed = super.attack(monster);

		if(killed)
		{
			getMessages().addMessage(monster.getName() + " died, giving " + monster.getExp() + " exp!");
			int newExp = getExp()+monster.getExp();
			if(newExp >= 100)
			{
				newExp = newExp - 100;
				setMaxLife(getMaxLife()+10);
				setLife(getMaxLife());
				setMinDamage(getMinDamage()+2);
				setMaxDamage(getMaxDamage()+2);
				setDefense(getDefense()+2);
				setMaxMagicPoints(getMaxMagicPoints() + 5);
				setMagicPoints(getMaxMagicPoints());
				setMaxPhysicalPoints(getMaxPhysicalPoints() + 5);
				setPhysicalPoints(getMaxPhysicalPoints());
				setLevel(getLevel()+1);
				getMessages().addMessage("Level up!");
			}
			setExp(newExp);
		}
		return killed;
	}
}