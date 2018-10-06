package entities;

import java.io.Serializable;

public class Wall extends Entity implements Serializable
{
	private static final String name = "Wall";

	public Wall(int inRow, int inColumn)
	{
		super('x', inRow, inColumn, name);
	}

	public boolean blocksLineOfSight(){ return true;}

	public boolean passable(){return false;}
}
