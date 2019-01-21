package entities;

import java.io.Serializable;

public class DownStairs extends Entity implements Serializable
{
	private static final String name = "Stairs Down";

	public DownStairs(int inRow, int inColumn)
	{
		super('>', inRow, inColumn, name);
	}

	public boolean blocksLineOfSight(){ return false;}

	public boolean passable(){return true;}
}
