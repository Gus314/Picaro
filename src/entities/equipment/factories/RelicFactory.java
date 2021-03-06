package entities.equipment.factories;

import entities.equipment.Relic;
import entities.factories.AbstractEntityFactory;
import statuses.StatusEffect;

public class RelicFactory extends AbstractEntityFactory
{
	private StatusEffect statusEffect;
	private String name;
	private int minLevel;
	private int maxLevel;

	public RelicFactory(StatusEffect inStatusEffect, String inName, int inMinLevel, int inMaxLevel)
	{
		statusEffect = inStatusEffect;
		name = inName;
		minLevel = inMinLevel;
		maxLevel = inMaxLevel;
	}

	public String getName(){return name;}

	public int getMinLevel(){return minLevel;}

	public int getMaxLevel(){return maxLevel;}
	
	public Relic construct(int inRow, int inColumn)
	{
		return new Relic(statusEffect, name, 'r', inRow, inColumn, minLevel, maxLevel);
	}
}
