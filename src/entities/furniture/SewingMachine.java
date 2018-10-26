package entities.furniture;

import control.Controller;
import entities.Player;
import entities.equipment.Armour;
import entities.equipment.Weapon;
import enums.ArmourModificationType;
import statuses.Groggy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SewingMachine extends Furniture implements Serializable
{
    private int intensity;
    private ArmourModificationType type;

    public SewingMachine(int inRow, int inColumn, String inName, ArmourModificationType inType, int inIntensity)
    {
        super('£', inRow, inColumn, inName);
        type = inType;
        intensity = inIntensity;
    }

    @Override
    public void use(Player player)
    {
        if(!getUsed())
        {
            List<Armour> armours = new ArrayList<Armour>(player.getArmours().values());
            Armour armour = armours.get(Controller.getGenerator().nextInt(armours.size()));
            int change = armour.changeStat(type, intensity);
            player.getMessages().addMessage("adjusts " + armour.getName() + " changing " + type + " by " + change + ".");
            setUsed(true);
        }
        else
        {
            player.getMessages().addMessage("The sewing machine is out of thread.");
        }
    }
}