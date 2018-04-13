package entities;

public class Wall extends Entity
{
	private static final String name = "Wall";

	public Wall(int inRow, int inColumn)
	{
		super('x', inRow, inColumn, name);
	}

	public boolean blocksLineOfSight(){ return true;}
}
