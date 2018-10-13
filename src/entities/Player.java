package entities;

import control.Grave;
import control.Map;
import control.PlayerInitialData;
import entities.equipment.Armour;
import entities.equipment.Item;
import entities.equipment.Relic;
import entities.equipment.Weapon;
import enums.ArmourType;
import enums.Faction;
import pclasses.Pclass;
import races.Race;
import ui.mainwindow.Messages;

import javax.swing.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

public class Player extends Creature implements Serializable
{	
	private Weapon weapon;
	private java.util.Map<ArmourType, Armour> armours;
	private Collection<Relic> relics;
	private Collection<Item> items;
	private int level;
	private Race race;
	private Pclass pclass;
	private static final int initialLevel = 1;
	private static final int initialLife = 100;
	private static final int initialDefense = 3;
	private static final int initialMinDamage = 4;
	private static final int initialMaxDamage = 8;
    private static final int initialExp = 0;
    private static final int initialCritChance = 10;
    private static final int initialBlockChance = 0;
    private static final int initialAbsorbChance = 0;
    private static final int initialRange = 1;
    private static final int initialMaxPhysicalPoints = 40;
    private static final int initialMaxMagicPoints = 50;
    private static final int initialIntelligence = 8;
    private static final int initialMagicDefence = 6;
	private static final int maxRelics = 3;
	private String causeOfDeath;

	public Player(Map inMap, Messages inMessages, PlayerInitialData playerInitialData)
	{
		super('@', 0, 0, inMap, inMessages, Faction.PLAYER, initialDefense, playerInitialData.getName(), initialLife, initialLife, initialMinDamage, initialMaxDamage, initialCritChance, initialBlockChance, initialAbsorbChance, initialRange, initialExp,
				initialMaxPhysicalPoints, initialMaxPhysicalPoints, initialMaxMagicPoints, initialMaxMagicPoints, initialIntelligence, initialMagicDefence);

		race = playerInitialData.getRace();
		race.initialise(this);

		pclass = playerInitialData.getPclass();
		pclass.initialise(this);

		items = new Vector<Item>();
		weapon = playerInitialData.getWeapon();
		armours = new HashMap<>();
		for(Armour armour: playerInitialData.getArmours().values())
		{
			armours.put(armour.getArmourType(), armour);
		}
		relics = new Vector<Relic>();
		level = initialLevel;
		causeOfDeath = "";
	}

	public static int getMaxRelics(){return maxRelics;}

	public boolean passable(){return false;}

	public boolean blocksLineOfSight(){ return true;}

	public void addItem(Item inItem)
	{
		items.add(inItem);
	}
	
	public Collection<Item> getItems()
	{
		return items;
	}

	public int getLevel(){
		return level;
	}

	public void setLevel(int inLevel){level = inLevel;}

	public Race getRace(){return race;}

	public Pclass getPclass(){return pclass;}

	public @Override int getRange()
	{
		int result = super.getRange();

		if(weapon != null)
		{
			result = weapon.getRange();
		}

		return result;
	}

	public @Override int getSightRadius()
	{
		return 8;
	}

	public @Override int getMinDamage()
	{
		int result = super.getMinDamage();
		
		if(weapon!=null)
			result+= weapon.getMinDamage();

		return result;
	}

	public @Override int getMaxDamage()
	{
		int result = super.getMaxDamage();
		
		if(weapon!=null)
			result+= weapon.getMaxDamage();

		return result;
	}
	
	public @Override int getCritChance()
	{
		int result = super.getCritChance();
		
		if(weapon!=null)
			result+= weapon.getCritChance();

		return result;
	}

	public @Override int getIntelligence()
	{
		int result = super.getIntelligence();

		if(weapon!=null)
			result+= weapon.getIntelligence();

		return result;
	}

	private int getArmoursDefense()
	{
		int result = 0;
		for(Armour armour: armours.values())
		{
			result += armour.getDefense();
		}
		return result;
	}

	public @Override int getDefense()
	{
		int result = super.getDefense();

		result+= getArmoursDefense();

		return result;
	}

	private int getArmoursMagicDefense()
	{
		int result = 0;
		for(Armour armour: armours.values())
		{
			result += armour.getMagicDefense();
		}
		return result;
	}

	public @Override int getMagicDefense()
	{
		int result = super.getMagicDefense();

		result+= getArmoursMagicDefense();

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
	
	public void setArmour(Armour armour)
	{
		armours.put(armour.getArmourType(), armour);
	}
	
	public Armour getArmour(ArmourType armourType)
	{
		return armours.get(armourType);
	}

	public java.util.Map<ArmourType, Armour> getArmours(){return armours;}

	public Collection<Relic> getRelics()
	{
		return relics;
	}

	public void removeRelic(Relic relic)
	{
		removeStatusEffect(relic.getStatusEffect());
		relics.remove(relic);
	}

	public boolean canAddRelic()
	{
		return relics.size() < maxRelics;
	}

	public void addRelic(Relic relic)
	{
		if(relics.size() < maxRelics)
		{
			relics.add(relic);
			addStatusEffect(relic.getStatusEffect());
		}
		else
		{
			System.out.println("Player::add(Relic) - too many relics.");
		}
	}

	private int getArmoursBlockChance()
	{
		int result = 0;
		for(Armour armour: armours.values())
		{
			result += armour.getBlockChance();
		}
		return result;
	}

	public @Override int getBlockChance()
	{
		int result = super.getBlockChance();

		result+= getArmoursBlockChance();

		return result;
	}

	private int getArmoursAbsorbChance()
	{
		int result = 0;
		for(Armour armour: armours.values())
		{
			result += armour.getAbsorbChance();
		}
		return result;
	}

	public @Override int getAbsorbChance()
	{
		int result = super.getAbsorbChance();
		
		result+= getArmoursAbsorbChance();

		return result;
	}

	private void levelUp()
	{
		// Note that level must be incremented before class and race level up are handled.
		setLevel(getLevel()+1);
		pclass.levelUp(this);
		race.levelUp(this);
		getMessages().addMessage("Level up!");
	}

	private int expForNextLevel()
	{
		double dResult = 100.0 * Math.pow(1.50, level);
		return (int) dResult;
	}

	public void killed(Monster monster)
	{
		getMessages().addMessage(monster.getName() + " died, giving " + monster.getExp() + " exp!");
		int newExp = getExp()+monster.getExp();

		if(newExp >= expForNextLevel())
		{
           levelUp();
		}

		newExp = (newExp >= expForNextLevel()) ? 0 : newExp;
		setExp(newExp);
	}

	public boolean attack(Monster monster)
	{
		boolean killed = super.attack(monster);

		if(killed)
		{
           killed(monster);
		}
		return killed;
	}

	public void setCauseOfDeath(String inCauseOfDeath)
	{
		causeOfDeath = inCauseOfDeath;
	}

	public String getCauseOfDeath()
	{
		return causeOfDeath;
	}

	public Grave createGrave()
	{
		return new Grave(this);
	}
}