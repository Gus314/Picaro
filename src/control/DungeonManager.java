package control;

import entities.creatures.Player;
import mapgeneration.MazeGenerator;
import mapgeneration.PersistedMap;
import ui.mainwindow.MapDisplay;
import ui.mainwindow.Messages;

import java.io.*;
import java.util.HashMap;

public class DungeonManager
{
	private MazeGenerator mazeGen;
	private Player player;
	private Messages messages;
	private MapDisplay mapDisplay;
	private int level;
	private java.util.Map<Integer, PersistedMap> levels;
    private static final int rows = 50;
    private static final int columns = 200;
    private static final int rowCellSize = 10;
    private static final int columnCellSize = 10;
    private static final int baseProperRoomChance = 67;
    private static final int baseRandomFloorPercentage = 16;
    private static final String characterFilename = "picaro.sav";

	public DungeonManager(Messages inMessages, PlayerInitialData playerInitialData)
	{
		messages = inMessages;
		mazeGen = new MazeGenerator(rows, columns, rowCellSize, columnCellSize, baseProperRoomChance, baseRandomFloorPercentage, messages);
		player = new Player(mazeGen.getMap(), messages, playerInitialData);
		level = 0;
		levels = new HashMap<Integer, PersistedMap>();
	}

	public MapDisplay getMapDisplay()
	{
		return mapDisplay;
	}

	public int getLevel()
	{
		return level;
	}

	private int determineProperRoomChance(int level)
	{
		return baseProperRoomChance + level;
	}

	private int determineRandomFloorPercentage(int level)
	{
		return baseRandomFloorPercentage - level;
	}

	public DungeonManager()
	{
		try
		{
			FileInputStream fileInputStream = new FileInputStream("Picaro.sav");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			mazeGen = (MazeGenerator) objectInputStream.readObject();
			player = (Player) objectInputStream.readObject();
			levels = (java.util.Map<Integer, PersistedMap>) objectInputStream.readObject();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}

	}

	public void endGame(Grave grave)
	{
		GraveStore.addGrave(grave);
		File characterFile = new File(characterFilename);
		if(characterFile.exists())
		{
			characterFile.delete();
		}
	}

	public void save()
	{
		try
		{
			FileOutputStream fileOutputStream = new FileOutputStream(characterFilename);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(mazeGen);
			objectOutputStream.writeObject(player);
			objectOutputStream.writeObject(levels);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}




	private void changeLevel(boolean descending)
	{
		PersistedMap persistedMap = null;

		if(levels.containsKey(level))
		{
			persistedMap = levels.get(level);
			mazeGen.loadPersistedMap(persistedMap);
		}
		else
		{
			mazeGen.setRandomFloorPercentage(determineRandomFloorPercentage(level));
			mazeGen.setProperRoomChance(determineProperRoomChance(level));
			mazeGen.construct(level, player);
			persistedMap = mazeGen.getPersistedMap();
			levels.put(level, persistedMap);
		}

		Player player = mazeGen.getPlayer();

		if(level != 1)
		{
			Coordinate stairs = descending? persistedMap.getUpStairs() : persistedMap.getDownStairs();
			player.setRow(stairs.getRow());
			player.setColumn(stairs.getColumn());
		}
		else if(!descending)
		{
			Coordinate stairs  = persistedMap.getDownStairs();
			player.setRow(stairs.getRow());
			player.setColumn(stairs.getColumn());

		}

		if(mapDisplay!=null)
		{
			mapDisplay.setMap(persistedMap.getMap());
		}
	}

	public void nextLevel()
	{		
		level++;
        changeLevel(true);
	}

	public void previousLevel()
	{
		if(level > 1)
		{
			level--;
			changeLevel(false);
		}
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
