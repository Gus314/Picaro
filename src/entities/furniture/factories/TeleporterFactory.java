package entities.furniture.factories;

import entities.furniture.Teleporter;

import java.io.Serializable;

public class TeleporterFactory extends FurnitureFactory implements Serializable
{
    private int minLevel;
    private int maxLevel;

    public TeleporterFactory(int inMinLevel, int inMaxLevel, String inName)
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
    public Teleporter construct(int inRow, int inColumn)
    {
        return new Teleporter(inRow, inColumn, name);
    }
}