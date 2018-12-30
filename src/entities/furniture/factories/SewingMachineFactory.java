package entities.furniture.factories;

import entities.furniture.Bed;
import entities.furniture.SewingMachine;
import enums.ArmourModificationType;

import java.io.Serializable;

public class SewingMachineFactory extends FurnitureFactory implements Serializable
{
    private int minLevel;
    private int maxLevel;
    private ArmourModificationType type;
    private int intensity;

    public SewingMachineFactory(int inMinLevel, int inMaxLevel, String inName, ArmourModificationType inType, int inIntensity)
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
    public SewingMachine construct(int inRow, int inColumn)
    {
        return new SewingMachine(inRow, inColumn, name, type, intensity);
    }
}
