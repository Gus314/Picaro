package entities.furniture;

import entities.creatures.Player;
import entities.equipment.Weapon;
import enums.WeaponModificationType;

import java.io.Serializable;

public class Anvil extends Furniture implements Serializable
{
    private int intensity;
    private WeaponModificationType type;

    public Anvil(int inRow, int inColumn, String inName, WeaponModificationType inType, int inIntensity)
    {
        super('!', inRow, inColumn, inName);
        intensity = inIntensity;
        type = inType;
    }

    @Override
    public void use(Player player)
    {
        if(!getUsed())
        {
            Weapon w = player.getWeapon();
            int change = w.changeStat(type, intensity);
            player.getMessages().addMessage("reforges " + w.getName() + " changing " + type + " by " + change + ".");
            setUsed(true);
        }
        else
        {
            player.getMessages().addMessage("The anvil is broken.");
        }
    }
}