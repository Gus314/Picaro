package entities.equipment.factories;

import entities.equipment.Relic;
import enums.RelicEffect;

public class RelicFactory
{
	private RelicEffect effect;
	private int amount;
	private String name;
	private int minLevel;
	private int maxLevel;
	
	public RelicFactory(RelicEffect inEffect, int inAmount, String inName, int inMinLevel, int inMaxLevel)
	{
		effect = inEffect;
		amount = inAmount;
		name = inName;
		minLevel = inMinLevel;
		maxLevel = inMaxLevel;
	}
	
	public int getMinLevel(){return minLevel;}

	public int getMaxLevel(){return maxLevel;}
	
	public Relic construct(int inRow, int inColumn)
	{
		return new Relic(effect, amount, name, 'r', inRow, inColumn, minLevel, maxLevel);
	}
}
