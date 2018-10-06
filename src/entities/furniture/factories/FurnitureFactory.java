package entities.furniture.factories;

import entities.factories.AbstractEntityFactory;
import entities.furniture.Furniture;

public abstract class FurnitureFactory extends AbstractEntityFactory
{
    public abstract int getMinLevel();

    public abstract int getMaxLevel();

    @Override
    public abstract Furniture construct(int inRow, int inColumn);
}
