package entities.factories;

import entities.Wall;

public class WallFactory extends AbstractEntityFactory
{
	private Character cha;
	public Wall construct(int inRow, int inColumn)
	{
		return new Wall(inRow, inColumn);
	}
	public String getName(){return "Wall";}
}
