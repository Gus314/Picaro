package entities;

import control.Coordinate;
import entities.equipment.Item;
import enums.Direction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javafx.scene.input.KeyCode.T;

public abstract class Entity implements Serializable
{
	private Character cha;
	private int row;
	private int column;
	private String name;
	
	public Entity(Character inCha, int inRow, int inColumn, String inName)
	{
		cha = inCha;
		row = inRow;
		column = inColumn;
		name = inName;
	}

	public String getName(){return name;}

	public Character getChar()
	{
		return cha;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getColumn()
	{
		return column;
	}
	
	public void setRow(int inRow)
	{
		row = inRow;
	}
	
	public void setColumn(int inColumn)
	{
		column = inColumn;
	}

	public abstract boolean blocksLineOfSight();

	public abstract boolean passable();

	public Coordinate getPosition(){return new Coordinate(row, column);}

	public static boolean passable(List<Entity> entities)
	{
		for(Entity ent: entities)
		{
			if(!ent.passable())
			{
				return false;
			}
		}

		return true;
	}

	public static boolean containsMonster(List<Entity> entities)
	{
		for(Entity ent: entities)
		{
			if(ent instanceof Monster)
			{
				return true;
			}
		}

		return false;
	}

	public static Monster getMonster(List<Entity> entities)
	{
		for(Entity ent: entities)
		{
			if(ent instanceof Monster)
			{
				return (Monster)ent;
			}
		}

		return null;
	}

	public static <T> List<Item> getItems(List<T> list)
	{
		List<Item> result = new ArrayList<>();

		for(T entry : list)
		{
			if(entry instanceof Item)
			{
				result.add((Item) entry);
			}
		}
		return result;
	}

	public void move(Direction direction, int amount)
	{
	   switch(direction)
	   {
			case UPLEFT:
			{
				moveUpLeft(amount);
				break;
			}
			case UPRIGHT:
			{
				moveUpRight(amount);
				break;
			}
			case DOWNLEFT:
			{
				moveUpRight(amount * -1);
				break;
			}
			case DOWNRIGHT:
			{
				moveUpLeft(amount * -1);
				break;
			}
			case UP:
			{
				moveDown(amount * -1);
				break;
			}
			case LEFT:
			{
				moveRight(amount * -1);
				break;
			}
			case RIGHT:
			{
				moveRight(amount);
				break;
			}
			case DOWN:
			{
				moveDown(amount);
				break;
			}
	   }
	}

	private void moveUpLeft(int amount)
	{
		column = column - amount;
		row = row - amount;
	}

	private void moveUpRight(int amount)
	{
		column = column + amount;
		row = row - amount;
	}

	private void moveRight(int amount)
	{
		column = column + amount;
	}

	private void moveDown(int amount)
	{
		row = row + amount;
	}
}
