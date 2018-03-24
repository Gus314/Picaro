package entities.factories;

import entities.Floor;

public class FloorFactory extends AbstractEntityFactory
{
    private Character cha;
    public Floor construct(int inRow, int inColumn)
    {
        return new Floor(inRow, inColumn);
    }
}
