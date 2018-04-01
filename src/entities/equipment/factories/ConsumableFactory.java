package entities.equipment.factories;

import entities.equipment.Consumable;
import entities.Player;
import entities.factories.AbstractEntityFactory;
import enums.ConsumableType;
import ui.Messages;

public class ConsumableFactory extends AbstractEntityFactory
{
	private Character cha;
	private int amount;
	private ConsumableType type;
	private Messages messages;
	private String name;
	private Player player;
	private int level;
	
	public ConsumableFactory(Character inCha, int inAmount, ConsumableType inType, Messages inMessages, String inName, Player inPlayer, int inLevel)
	{
		cha = inCha;
		type = inType;
		amount = inAmount;
		name = inName;
		messages = inMessages;
		player = inPlayer;
		level = inLevel;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public Consumable construct(int inRow, int inColumn)
	{
		return new Consumable(cha, inRow, inColumn, name, type, amount, player, messages, level);
	}
}
