package control;

import entities.Player;
import mapgeneration.MazeGenerator;
import ui.MapDisplay;
import ui.Messages;

public class DungeonManager
{
	private MazeGenerator mazeGen;
	private Player player;
	private Messages messages;
	private MapDisplay mapDisplay;
	private int level;
	
	public DungeonManager(Messages inMessages)
	{
		messages = inMessages;
		mazeGen = new MazeGenerator(2, 8, messages);
		player = new Player(messages);
		level = 0;
	}
	
	public MapDisplay getMapDisplay()
	{
		return mapDisplay;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public void nextLevel()
	{		
		level++;
		mazeGen.construct(level);
		mazeGen.positionPlayer(player);		
		mazeGen.initialiseFactories();
		mazeGen.addMonsters();
		mazeGen.addItems();
		mazeGen.addRelics();
		mazeGen.addStairs();
		
		if(mapDisplay!=null)
			mapDisplay.setMap(mazeGen.getMap());
	}

	public void setMapDisplay(MapDisplay inMapDisplay)
	{
		mapDisplay = inMapDisplay;
	}
	
	public Map getMap()
	{
		return mazeGen.getMap();
	}
	
	public Player getPlayer()
	{
		return player;
	}
}
