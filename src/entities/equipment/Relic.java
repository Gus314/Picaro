package entities.equipment;

import enums.RelicEffect;

public class Relic extends Item
{
	private RelicEffect effect;
	private int amount;
	
	public Relic(RelicEffect inEffect, int inAmount, String inName, Character inCha, int inRow, int inColumn, int inLevel)
	{
		super(inCha, inRow, inColumn, inName, inLevel);
		effect = inEffect;
		amount = inAmount;
	}
	
	public RelicEffect getEffect()
	{
		return effect;
	}
	
	public int getAmount()
	{
		return amount;
	}
}
