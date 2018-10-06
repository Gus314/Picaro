package entities.furniture.factories;

import entities.furniture.Shrine;
import enums.StatType;

public class ShrineFactory extends FurnitureFactory
{
    private int minLevel;
    private int maxLevel;
    private String name;
    private StatType stat;
    private int intensity;

    public ShrineFactory(int inMinLevel, int inMaxLevel, String inName, StatType inStat, int inIntensity)
    {
        minLevel = inMinLevel;
        maxLevel = inMaxLevel;
        name = inName;
        stat = inStat;
        intensity = inIntensity;
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
    public Shrine construct(int inRow, int inColumn)
    {
        return new Shrine(inRow, inColumn, name, stat, intensity);
    }
}
