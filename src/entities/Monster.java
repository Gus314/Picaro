package entities;

import control.Controller;
import control.Map;
import entities.ai.ActFactory;
import entities.ai.Brain;
import entities.ai.MoveFactory;
import enums.Behaviour;
import enums.TurnType;
import ui.mainwindow.Messages;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import enums.Direction;

public class Monster extends Creature
{
	private int minLevel;
	private int maxLevel;
	private Brain brain;

	public boolean passable(){return false;}

	public Monster(Character inCha, int inRow, int inColumn, Map inMap, Messages inMessages, int inDefense, String inName, int inMaxLife, int inLife, int inMinDamage, int inMaxDamage, int inCritChance, int inBlockChance,
				   int inAbsorbChance, int inRange, int inExp, int inPhysicalPoints, int inMaxPhysicalPoints, int inMagicPoints, int inMaxMagicPoints, int inIntelligence, int inMagicDefense, int inMinLevel, int inMaxLevel)
	{
		super(inCha, inRow, inColumn, inMap, inMessages, inDefense, inName, inMaxLife, inLife, inMinDamage, inMaxDamage, inCritChance, inBlockChance,
				inAbsorbChance, inRange, inExp, inPhysicalPoints, inMaxPhysicalPoints, inMagicPoints, inMaxMagicPoints, inIntelligence, inMagicDefense);
		minLevel = inMinLevel;
		maxLevel = inMaxLevel;
		brain = new Brain(this);
	}

	public int getMinLevel(){return minLevel;}

	public int getMaxLevel(){return maxLevel;}

	private void move(Behaviour behaviour, Monster monster, Map map)
	{
		MoveFactory.construct(behaviour, monster, map).move();
	}

	private void act(Player player, Monster monster, Messages messages, Behaviour behaviour)
	{
		ActFactory.construct(behaviour, monster, messages).act(player);

	}

	public void takeTurn()
	{
		Player player = null;

		for(Entity ent: getMap().lineOfSight(this, getRange()))
		{
			if(ent instanceof Player)
			{
				player = (Player) ent;
				break;
			}
		}

		TurnType turnType = brain.determineTurnType(player != null);
		Behaviour behaviour = brain.determineBehaviour();

		switch(turnType)
		{
			case MOVE:
			{
				move(behaviour, this, getMap());
				break;
			}
			case ACT:
			{
				act(player, this, getMessages(), behaviour);
				break;
			}
			default:
			{
				System.out.println("Monster::takeTurn() - unexpected turn type.");
				// TODO: Throw exception
			}
		}
	}
}
