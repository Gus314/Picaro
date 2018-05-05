package entities;

import control.Controller;
import control.Map;
import ui.mainwindow.Messages;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import enums.Direction;

public class Monster extends Creature
{
	private int minLevel;
	private int maxLevel;

	public boolean passable(){return false;}

	public Monster(Character inCha, int inRow, int inColumn, Map inMap, Messages inMessages, int inDefense, String inName, int inMaxLife, int inLife, int inMinDamage, int inMaxDamage, int inCritChance, int inBlockChance,
				   int inAbsorbChance, int inRange, int inExp, int inPhysicalPoints, int inMaxPhysicalPoints, int inMagicPoints, int inMaxMagicPoints, int inIntelligence, int inMagicDefense, int inMinLevel, int inMaxLevel)
	{
		super(inCha, inRow, inColumn, inMap, inMessages, inDefense, inName, inMaxLife, inLife, inMinDamage, inMaxDamage, inCritChance, inBlockChance,
				inAbsorbChance, inRange, inExp, inPhysicalPoints, inMaxPhysicalPoints, inMagicPoints, inMaxMagicPoints, inIntelligence, inMagicDefense);
		minLevel = inMinLevel;
		maxLevel = inMaxLevel;
	}

	public int getMinLevel(){return minLevel;}

	public int getMaxLevel(){return maxLevel;}

	private void attack(Player player)
	{
		boolean killed = super.attack(player);
		if(killed)
		{
			JOptionPane.showMessageDialog(getMessages().getTopLevelAncestor(), "You have died!");
			System.exit(0);
		}
	}

	private boolean canMove()
	{
		int row = this.getRow();
		int column = this.getColumn();

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

	public void takeTurn()
	{
		Player player = null;

		for(Entity ent: getMap().lineOfSight(this, getRange()))
		{
			if(ent instanceof Player)
			{
				player = (Player) ent;
				break;
			}
		}

	    if(player != null)
		{
			// Cannot both attack and move in a turn.
			attack(player);
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
				int index = Controller.getGenerator().nextInt(directions.size());
				Direction direction = directions.remove(index);

				List<Entity> here = getMap().atPosition(row + direction.rowShift(), column + direction.columnShift());
				if(Entity.passable(here))
				{
					move(direction, 1);
					break;
				}
			}
		}

	}
}
