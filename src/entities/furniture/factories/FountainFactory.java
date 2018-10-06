package entities.furniture.factories;

import entities.furniture.Fountain;
import statuses.StatusEffect;

public class FountainFactory extends FurnitureFactory
{
    private int minLevel;
    private int maxLevel;
    private String name;
    private StatusEffect status;

    public FountainFactory(int inMinLevel, int inMaxLevel, String inName, StatusEffect inStatus)
    {
        minLevel = inMinLevel;
        maxLevel = inMaxLevel;
        name = inName;
        status = inStatus;
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
    public Fountain construct(int inRow, int inColumn)
    {
        return new Fountain(inRow, inColumn, name, status);
    }
}
