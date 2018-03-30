package entities;

public abstract class Item extends Entity
{
	private String name;
	private int level;
	
	public Item(Character inCha, int inRow, int inColumn, String inName, int inLevel) 
	{	
		super(inCha, inRow, inColumn);
		name = inName;
		level = inLevel;
	};

	public String getName()
	{
		return name;
	}

	public boolean blocksLineOfSight(){ return true;}

	public int getLevel;
}
