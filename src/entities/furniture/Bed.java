package entities.furniture;

import entities.Player;
import statuses.Groggy;

import java.io.Serializable;

public class Bed extends Furniture implements Serializable
{
    public Bed(int inRow, int inColumn, String inName)
    {
        super('[', inRow, inColumn, inName);
    }

    @Override
    public void use(Player player)
    {
        if(!getUsed())
        {
            Groggy groggy = new Groggy(player, 10, 10);
            player.addStatusEffect(groggy);
            player.setLife(player.getMaxLife());
            player.setMagicPoints(player.getMaxMagicPoints());
            player.setPhysicalPoints(player.getMaxPhysicalPoints());
            player.getMessages().addMessage(player.getName() + "rests in " + getName() + ", recovering health and mp/pp.");
            setUsed(true);
        }
        else
        {
            player.getMessages().addMessage("The bed is too messy to sleep in.");
        }
    }
}
