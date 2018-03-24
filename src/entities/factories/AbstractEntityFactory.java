package entities.factories;

import entities.Entity;

public abstract class AbstractEntityFactory
{
	private Character cha;
	public Entity construct(int inRow, int inColumn, Character inCha)
	{
		return new Entity(inCha, inRow, inColumn);
	}
}
