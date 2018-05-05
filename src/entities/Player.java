package entities;

import control.Map;
import control.PlayerInitialData;
import entities.equipment.Armour;
import entities.equipment.Item;
import entities.equipment.Relic;
import entities.equipment.Weapon;
import enums.ArmourType;
import pclasses.Pclass;
import races.Race;
import skills.Fireball;
import skills.Heal;
import skills.PoisonDart;
import enums.RelicEffect;
import ui.mainwindow.Messages;

import java.util.HashMap;
import java.util.Vector;

public class Player extends Creature
{	
	private Weapon weapon;
	private java.util.Map<ArmourType, Armour> armours;
	private Relic relic;
	private Vector<Item> items;
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

	public Player(Map inMap, Messages inMessages, PlayerInitialData playerInitialData)
	{
		super('@', 0, 0, inMap, inMessages, initialDefense, playerInitialData.getName(), initialLife, initialLife, initialMinDamage, initialMaxDamage, initialCritChance, initialBlockChance, initialAbsorbChance, initialRange, initialExp,
				initialMaxPhysicalPoints, initialMaxPhysicalPoints, initialMaxMagicPoints, initialMaxMagicPoints, initialIntelligence, initialMagicDefence);

		race = playerInitialData.getRace();
		race.initialise(this);

		pclass = playerInitialData.getPclass();
		pclass.initialise(this);

		items = new Vector<Item>();
		weapon = null;
		armours = new HashMap<>();
		relic = null;
		level = initialLevel;
	}

	public boolean passable(){return false;}

	public boolean blocksLineOfSight(){ return true;}

	public void addItem(Item inItem)
	{
		items.add(inItem);
	}
	
	public Vector<Item> getItems()
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

	public @Override int getIntelligence()
	{
		int result = super.getIntelligence();

		if(weapon!=null)
			result+= weapon.getIntelligence();

		if(relic!=null && relic.getEffect()== RelicEffect.Intelligence)
			result+= relic.getAmount();

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
		
		if(relic!=null && relic.getEffect()== RelicEffect.Defense)
			result+= relic.getAmount();
		
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

		if(relic!=null && relic.getEffect()== RelicEffect.MagicDefense)
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
	
	public void setArmour(Armour armour)
	{
		armours.put(armour.getArmourType(), armour);
	}
	
	public Armour getArmour(ArmourType armourType)
	{
		return armours.get(armourType);
	}
	
	public Relic getRelic()
	{
		return relic;
	}

	
	public void setRelic(Relic inRelic)
	{
		relic = inRelic;
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
		
		if(relic!=null && relic.getEffect()== RelicEffect.BlockChance)
			result+= relic.getAmount();
		
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
		
		if(relic!=null && relic.getEffect()== RelicEffect.AbsorbChance)
			result+= relic.getAmount();
		
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
		double dResult = 100.0 + ((double)level * 25);
		return (int) dResult;
	}

	public void killed(Monster monster)
	{
		// TODO : Change exp required per level.
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
}