package entities;

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
	
	
	public void moveRight(int amount)
	{
		column = column + amount;
	}
	
	public void moveDown(int amount)
	{
		row = row + amount;
	}
}
