package entities;

import entities.*;
import enums.RelicEffect;
import ui.Messages;

import java.util.Vector;

public class Player extends Entity
{	
	private int maxLife;
	private int life;
	private int minDamage;
	private int maxDamage;
	private int critChance;
	private int defense;
	private int blockChance;
	private int absorbChance;
	private Messages messages;
	private Weapon weapon;
	private Armour armour;
	private Relic relic;
	private Vector<Item> items;
	
	private int exp;
	private int level;
	
	public Player(Messages inMessages)
	{
		super('@', 0, 0);
		items = new Vector<Item>();
		maxLife = 100;
		life = maxLife;
		messages = inMessages;
		weapon = null;
		armour = null;
		relic = null;
		exp = 0;
		minDamage = 4;
		maxDamage = 8;
		critChance = 10;
		defense = 3;
		blockChance = 0;
		absorbChance = 0;
		level = 1;
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
	
	public int getLevel()
	{
		return level;
	}
	
	public void setLevel(int inLevel)
	{
		level = inLevel;
	}
	
	public void setMaxLife(int inMaxLife)
	{
		maxLife = inMaxLife;
	}
	
	public int getMaxLife()
	{
		return maxLife;
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
	
	public int getExp()
	{
		return exp;
	}
	
	public void setExp(int inExp)
	{
		exp = inExp;
	}
	
	public int getMinDamage()
	{
		int result = minDamage;
		
		if(weapon!=null)
			result+= weapon.getMinDamage();
		
		if(relic!=null && relic.getEffect()== RelicEffect.Damage)
			result+= relic.getAmount();
		
		return result;
	}
	
	public int getMaxDamage()
	{
		int result = maxDamage;
		
		if(weapon!=null)
			result+= weapon.getMaxDamage();
		
		if(relic!=null && relic.getEffect()== RelicEffect.Damage)
			result+= relic.getAmount();
		
		return result;
	}
	
	public int getCritChance()
	{
		int result = critChance;
		
		if(weapon!=null)
			result+= weapon.getCritChance();
		
		if(relic!=null && relic.getEffect()== RelicEffect.CritChance)
			result+= relic.getAmount();
		
		return result;
	}
	
	public int getDefense()
	{
		int result = defense;
		
		if(armour!=null)
			result+= armour.getDefense();
		
		if(relic!=null && relic.getEffect()== RelicEffect.Defense)
			result+= relic.getAmount();
		
		return result;
	}
	
	public int getLife()
	{
		return life;
	}
	
	public void setLife(int inLife)
	{
		life = inLife;
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
	
	public int getBlockChance()
	{
		int result = blockChance;
		
		if(armour!=null)
			result+= armour.getBlockChance();
		
		if(relic!=null && relic.getEffect()== RelicEffect.BlockChance)
			result+= relic.getAmount();
		
		return result;
	}
	
	public int getAbsorbChance()
	{
		int result = absorbChance;
		
		if(armour!=null)
			result+= armour.getAbsorbChance();
		
		if(relic!=null && relic.getEffect()== RelicEffect.AbsorbChance)
			result+= relic.getAmount();
		
		return result;
	}
	
	public void setBlockChance(int inBlockChance)
	{
		blockChance = inBlockChance;
	}
	
	public void setAbsorbChance(int inAbsorbChance)
	{
		absorbChance = inAbsorbChance;
	}
}