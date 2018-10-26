package entities.furniture;

import entities.Player;
import statuses.StatusEffect;

public class Fountain extends Furniture
{
    private StatusEffect status;

    public Fountain(int inRow, int inColumn, String inName, StatusEffect inStatus)
    {
        super('&', inRow, inColumn, inName);
        status = inStatus;
    }

    @Override
    public void use(Player player)
    {
        if(!getUsed())
        {
            player.getMessages().addMessage(player.getName() + " drank at the " + getName());
            player.addStatusEffect(status);
            setUsed(true);
        }
        else
        {
            player.getMessages().addMessage("The " + getName() + " has dried up.");
        }
    }
}
