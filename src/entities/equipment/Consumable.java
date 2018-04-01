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

	public Consumable(Character inCha, int inRow, int inColumn, String inName, ConsumableType inType, int inAmount, Player inPlayer, Messages inMessages, int inLevel)
	{
		super(inCha, inRow, inColumn, inName, inLevel);
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
			int oldLife = player.getLife();
			int newLife = oldLife + amount;
			int maxLife = player.getMaxLife();

			if(newLife > maxLife)
				newLife = maxLife;
			player.setLife(newLife);
			messages.addMessage("You were healed for " + ((Integer)(newLife - oldLife)).toString() + " hp!");
			player.getItems().remove(this);
			break;
		}
	}
}
