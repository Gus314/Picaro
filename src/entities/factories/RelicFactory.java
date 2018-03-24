package entities.factories;

import entities.Relic;
import enums.RelicEffect;

public class RelicFactory
{
	private RelicEffect effect;
	private int amount;
	private String name;
	private int level;
	
	public RelicFactory(RelicEffect inEffect, int inAmount, String inName, int inLevel)
	{
		effect = inEffect;
		amount = inAmount;
		name = inName;
		level = inLevel;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public Relic construct(int inRow, int inColumn)
	{
		return new Relic(effect, amount, name, 'r', inRow, inColumn, level);
	}
}
