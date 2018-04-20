package entities.equipment;

import entities.Player;
import enums.ConsumableType;
import ui.Messages;

public class Consumable extends Item
{	
	private ConsumableType type;
	private int amount;
	private Player player;
	private Messages messages;

	public Consumable(Character inCha, int inRow, int inColumn, String inName, ConsumableType inType, int inAmount, Player inPlayer, Messages inMessages, int inMinLevel, int inMaxLevel)
	{
		super(inCha, inRow, inColumn, inName, inMinLevel, inMaxLevel);
		type = inType;
		amount = inAmount;
		player = inPlayer;
		messages = inMessages;
	}
	
	public void consume()
	{
		switch(type)
		{
			case RestoreHP:
			{
				int oldLife = player.getLife();
				int newLife = oldLife + amount;
				int maxLife = player.getMaxLife();

				if (newLife > maxLife)
					newLife = maxLife;
				player.setLife(newLife);
				messages.addMessage("You were healed for " + ((Integer) (newLife - oldLife)).toString() + " hp!");
				player.getItems().remove(this);
				break;
			}
			case RestorePP:
			{
				int oldPp = player.getPhysicalPoints();
				int newPp = oldPp + amount;
				int maxPp = player.getMaxPhysicalPoints();

				if (newPp > maxPp)
					newPp = maxPp;
				player.setPhysicalPoints(newPp);
				messages.addMessage("You were healed for " + ((Integer) (newPp - oldPp)).toString() + " pp!");
				player.getItems().remove(this);
				break;
			}
			case RestoreMP:
			{
				int oldMp = player.getMagicPoints();
				int newMp = oldMp + amount;
				int maxMp = player.getMaxMagicPoints();

				if (newMp > maxMp)
					newMp = maxMp;
				player.setLife(newMp);
				messages.addMessage("You were healed for " + ((Integer) (newMp - oldMp)).toString() + " mp!");
				player.getItems().remove(this);
				break;
			}
		}
	}
}
