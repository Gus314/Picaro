package entities.furniture.factories;

import entities.factories.AbstractEntityFactory;
import entities.furniture.Furniture;

public abstract class FurnitureFactory extends AbstractEntityFactory
{
    public abstract int getMinLevel();

    public abstract int getMaxLevel();

    protected String name;

    public String getName(){return name;}

    @Override
    public abstract Furniture construct(int inRow, int inColumn);
}
