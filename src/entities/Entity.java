package entities;

import enums.Direction;

public class Entity
{
	private Character cha;
	private int row;
	private int column;
	
	
	public Entity(Character inCha, int inRow, int inColumn)
	{
		cha = inCha;
		row = inRow;
		column = inColumn;
	}
	
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
