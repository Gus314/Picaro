package entities;

public class Wall extends Entity
{
	public Wall(int inRow, int inColumn)
	{
		super('x', inRow, inColumn);
	}

	public boolean blocksLineOfSight(){ return true;}
}
