package entities.factories;

import entities.Entity;

public abstract class AbstractEntityFactory
{
	private Character cha;
	public abstract Entity construct(int inRow, int inColumn);
}
