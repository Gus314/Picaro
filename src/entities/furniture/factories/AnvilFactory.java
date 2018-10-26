package entities.furniture.factories;

import entities.furniture.Anvil;
import enums.WeaponModificationType;

import java.io.Serializable;

public class AnvilFactory extends FurnitureFactory implements Serializable
{
    private int minLevel;
    private int maxLevel;
    private String name;
    private WeaponModificationType type;
    private int intensity;

    public AnvilFactory(int inMinLevel, int inMaxLevel, String inName, WeaponModificationType inType, int inIntensity)
    {
        minLevel = inMinLevel;
        maxLevel = inMaxLevel;
        name = inName;
        type = inType;
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
    public Anvil construct(int inRow, int inColumn)
    {
        return new Anvil(inRow, inColumn, name, type, intensity);
    }
}