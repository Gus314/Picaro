package entities.equipment;

import statuses.StatusEffect;

public class Relic extends Item
{
	private StatusEffect statusEffect;
	
	public Relic(StatusEffect inStatusEffect, String inName, Character inCha, int inRow, int inColumn, int inMinLevel, int inMaxLevel)
	{
		super(inCha, inRow, inColumn, inName, inMinLevel, inMaxLevel);
		statusEffect = inStatusEffect;
	}
	
	public StatusEffect getStatusEffect()
	{
		return statusEffect;
	}
}
