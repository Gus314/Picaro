package entities.furniture.factories;

import entities.factories.AbstractEntityFactory;
import entities.furniture.Box;

public class BoxFactory extends FurnitureFactory
{
    private int minLevel;
    private int maxLevel;
    private String name;
    private AbstractEntityFactory factory;

    public BoxFactory(int inMinLevel, int inMaxLevel, String inName, AbstractEntityFactory inFactory)
    {
        minLevel = inMinLevel;
        maxLevel = inMaxLevel;
        name = inName;
        factory = inFactory;
    }

    @Override
    public int getMinLevel()
    {
        return minLevel;
    }

    @Override
    public int getMaxLevel()
    {
        return maxLevel;
    }

    @Override
    public Box construct(int inRow, int inColumn)
    {
        return new Box(inRow, inColumn, name, factory);
    }
}
