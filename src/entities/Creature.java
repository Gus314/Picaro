package entities;

import control.Map;
import ui.Messages;

import java.util.Random;

import javax.swing.JOptionPane;

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
	
	public void takeTurn()
	{
		int direction = generator.nextInt(4);
		int row = this.getRow();
		int column = this.getColumn();
		Entity left = map.atPosition(row, column-1);
		Entity right = map.atPosition(row, column+1);
		Entity up = map.atPosition(row-1, column);
		Entity down = map.atPosition(row+1, column);
		Player player = null;
		
		// attack
		if(up!= null && up instanceof Player)
			player = (Player) up;
		if(down!= null && down instanceof Player)
			player = (Player) down;
		if(left!= null && left instanceof Player)
			player = (Player) left;
		if(right!= null && right instanceof Player)
			player = (Player) right;
		
		if(player!=null)
		{
			int playerLife = player.getLife();
			int playerDef = player.getDefense();
			int playerBlockChance = player.getBlockChance();
			int playerAbsorbChance = player.getAbsorbChance();
			
			if(generator.nextInt(100 - playerBlockChance) == 0)
			{
				messages.addMessage("Attack was blocked!");
				return;
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
			return;
		}
		
		// move		
		switch(direction)
		{
		case 0:
			if(up == null)
				this.setRow(row-1);
			break;
		case 1:
			if(down == null)
				this.setRow(row+1);
			break;
		case 2: 
			if(left == null)
				this.setColumn(column-1);
			break;
		case 3:
			if(right == null)
				this.setColumn(column+1);
			break;
		}
	}
}
