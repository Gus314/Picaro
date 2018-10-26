package entities.furniture;

import entities.Player;
import enums.StatType;

public class Shrine extends  Furniture
{
    private StatType stat;
    private int intensity;

    public Shrine(int inRow, int inColumn, String inName, StatType inStat, int inIntensity)
    {
        super('*', inRow, inColumn, inName);
        stat = inStat;
        intensity = inIntensity;
    }

    @Override
    public void use(Player player)
    {
        if(!getUsed())
        {
            player.getMessages().addMessage(player.getName() + " prayed at the " + getName() + ".");
            String message = player.changeStat(stat, intensity);
            player.getMessages().addMessage(message);
            setUsed(true);
        }
        else
        {
            player.getMessages().addMessage(getName() + " felt no presence at this " + getName() + ".");
        }
    }
}
