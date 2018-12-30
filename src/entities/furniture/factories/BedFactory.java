package entities.furniture.factories;

import entities.furniture.Bed;

import java.io.Serializable;

public class BedFactory extends FurnitureFactory implements Serializable
{
    private int minLevel;
    private int maxLevel;

    public BedFactory(int inMinLevel, int inMaxLevel, String inName)
    {
        minLevel = inMinLevel;
        maxLevel = inMaxLevel;
        name = inName;
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
    public Bed construct(int inRow, int inColumn)
    {
        return new Bed(inRow, inColumn, name);
    }
}
