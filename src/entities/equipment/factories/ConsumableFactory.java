package entities.equipment.factories;

import entities.equipment.Consumable;
import entities.creatures.Player;
import entities.factories.AbstractEntityFactory;
import enums.ConsumableType;
import ui.mainwindow.Messages;

public class ConsumableFactory extends AbstractEntityFactory
{
	private Character cha;
	private int amount;
	private ConsumableType type;
	private Messages messages;
	private String name;
	private Player player;
	private int minLevel;
	private int maxLevel;
	
	public ConsumableFactory(Character inCha, int inAmount, ConsumableType inType, Messages inMessages, String inName, Player inPlayer, int inMinLevel, int inMaxLevel)
	{
		cha = inCha;
		type = inType;
		amount = inAmount;
		name = inName;
		messages = inMessages;
		player = inPlayer;
		minLevel = inMinLevel;
		maxLevel = inMaxLevel;
	}

	public String getName(){return name;}

	public int getMinLevel(){return minLevel;}

	public int getMaxLevel(){return maxLevel;}
	
	public Consumable construct(int inRow, int inColumn)
	{
		return new Consumable(cha, inRow, inColumn, name, type, amount, player, messages, minLevel, maxLevel);
	}
}
