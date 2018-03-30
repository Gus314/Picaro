package entities;

import control.Map;
import ui.Messages;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import enums.Direction;

public class Creature extends Entity
{
	private int life;
	private static Random generator = new Random();
	private Map map;
	private Messages messages;
	private int attack;
	private int defense;
	private String name;
	private int exp;
	private int level;

	public Creature(Character inCha, int inRow, int inColumn, int inLife, int inAttack, int inDefense, Map inMap, Messages inMessages, String inName, int inExp, int inLevel)
	{
		super(inCha, inRow, inColumn);
		life = inLife;
		map = inMap;
		messages = inMessages;
		attack = inAttack;
		defense = inDefense;
		name = inName;
		exp = inExp;
		level = inLevel;
	}

	public boolean blocksLineOfSight(){ return true;}
	
	public int getLevel()
	{
		return level;
	}
	
	public int getExp()
	{
		return exp;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getAttack()
	{
		return attack;
	}
	
	public int getDefense()
	{
		return defense;
	}
	
	public int getLife()
	{
		return life;
	}
	
	public void setLife(int inLife)
	{
		life = inLife;
	}

	private boolean attack()
	{
		boolean attacked = false;

		Player player = null;

		for(Entity ent: map.lineOfSight(this, 1))
		{
			if(ent instanceof Player)
			{
				player = (Player) ent;
				break;
			}
		}

		if(player!=null)
		{
			attacked = true;
			int playerLife = player.getLife();
			int playerDef = player.getDefense();
			int playerBlockChance = player.getBlockChance();
			int playerAbsorbChance = player.getAbsorbChance();

			if(generator.nextInt(100 - playerBlockChance) == 0)
			{
				messages.addMessage("Attack was blocked!");
				return attacked;
			}

			int damage = attack - playerDef;
			if(damage <= 0)
				messages.addMessage("Defense nullified attack!");
			else if(generator.nextInt(100 - playerAbsorbChance) == 0)
			{
				int newPlayerLife = playerLife + damage;
				int maxPlayerLife = player.getMaxLife();
				int amount = damage;
				if(newPlayerLife > maxPlayerLife)
				{
					amount = maxPlayerLife - playerLife;
				}
				player.setLife(newPlayerLife);
				messages.addMessage("Attack was absorbed for " + amount + " hp!");
			}
			else
			{
				playerLife -= damage;
				player.setLife(playerLife);
				messages.addMessage("Took " + damage + " damage from " + name + "!");
				if(playerLife <= 0)
				{
					JOptionPane.showMessageDialog(messages.getTopLevelAncestor(), "You have died!");
					System.exit(0);
				}
			}
		}
		return attacked;
	}

	private boolean canMove()
	{
		int row = this.getRow();
		int column = this.getColumn();

		for(Direction direction: Direction.values())
		{
			Entity existing = map.atPosition(row + direction.rowShift(), column + direction.columnShift());
			if(existing == null || existing instanceof Floor)
			{
				return true;
			}
		}

		return false;
	}

	public void takeTurn()
	{
	    if(attack())
		{
			// Cannot both attack and move in a turn.
			return;
		}

	    Direction[] directionArray = Direction.values();
	    ArrayList<Direction> directions =  new ArrayList<Direction>();
	    for(int i = 0; i < directionArray.length; i++)
		{
			directions.add(directionArray[i]);
		}

	    if(canMove())
		{
			int row = this.getRow();
			int column = this.getColumn();

			while(!directions.isEmpty())
			{
				int index = generator.nextInt(directions.size());
				Direction direction = directions.remove(index);

				Entity existing = map.atPosition(row + direction.rowShift(), column + direction.columnShift());
				if(existing == null || existing instanceof Floor)
				{
					move(direction, 1);
					break;
				}
			}
		}

	}
}
