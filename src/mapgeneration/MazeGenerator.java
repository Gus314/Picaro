package mapgeneration;

import control.Controller;
import control.Coordinate;
import control.Map;
import entities.Entity;
import entities.Player;
import entities.DownStairs;
import entities.UpStairs;
import entities.factories.*;
import enums.FloorType;
import enums.MapElementType;
import ui.Messages;

public class MazeGenerator 
{
	private Map map;
	private Integer[][] data;
	private Messages messages;
	private Player player;
	private Coordinate upStairs;
	private Coordinate downStairs;

	public MazeGenerator(int inMinRoomSize, int inMaxRoomSize, Messages inMessages)
	{
		data = null;
		messages = inMessages;
		upStairs = null;
		downStairs = null;
	}

	public void loadPersistedMap(PersistedMap persistedMap)
	{
		map = persistedMap.getMap();
		data = persistedMap.getData();
		upStairs = persistedMap.getUpStairs();
		downStairs = persistedMap.getDownStairs();
	}

	public Player getPlayer(){return player;}

	public PersistedMap getPersistedMap()
	{
		return new PersistedMap(map, data, upStairs, downStairs);
	}

	public Map getMap()
	{
		return map;
	}

	public Integer[][] getData(){return data;}

	public void construct(int level, Player inPlayer)
	{
		player = inPlayer;
		map = new Map(50, 50);

	    RoomGraph graph = new RoomGraph();
	    data = graph.determineLayout();
		
		// add walls
		WallFactory wallfac = new WallFactory();

		// add floor
		FloorFactory floorFac = new FloorFactory();

		for(int i = 0; i < data.length; i++)
			for(int j = 0; j < data[0].length; j++)
			{
				if(data[i][j] == FloorType.FLOOR.getValue())
				{
					map.addEntry(floorFac.construct(i,j));
				}
				final boolean adjacentFloor = ((i > 0) && data[i-1][j] == FloorType.FLOOR.getValue()) ||
						((i < data.length-1) && data[i+1][j] == FloorType.FLOOR.getValue()) ||
						((j > 0) && data[i][j-1] == FloorType.FLOOR.getValue()) ||
						((j < data.length-1) && data[i][j+1] == FloorType.FLOOR.getValue()) ||
						(i > 0 && j > 0 && data[i-1][j-1] == FloorType.FLOOR.getValue()) ||
						(i < data.length - 1 && j < data.length - 1 && data[i+1][j+1] == FloorType.FLOOR.getValue()) ||
						(i > 0 && j < data.length - 1 && data[i-1][j+1] == FloorType.FLOOR.getValue()) ||
						(i < data.length - 1 && j > 0 && data[i+1][j-1] == FloorType.FLOOR.getValue());
				if(data[i][j]==0 && adjacentFloor)
				{
					map.addEntry(wallfac.construct(i, j));
				}
			}
		
		positionPlayer(inPlayer);
		MazeFactories mazeFactories = new MazeFactories(map, messages, inPlayer);

		// TODO: Update map in factories!
		addEntities(level, mazeFactories);
		addStairs(level);

	}
		
	private void positionPlayer(Player inPlayer)
	{
		for(;;)
		{
			int row = Controller.getGenerator().nextInt(map.getRows());
			int column = Controller.getGenerator().nextInt(map.getColumns());
			if(data[row][column]==FloorType.FLOOR.getValue())
			{
				data[row][column] = 3;
				player = inPlayer;
				player.setRow(row);
				player.setColumn(column);
				return;
			}
		}
	}

	public void addEntities(int level, MazeFactories mazeFactories)
	{
		for(int i = 0; i < map.getRows(); i++)
			for(int j = 0; j < map.getColumns(); j++)
			{
			if(data[i][j]==FloorType.FLOOR.getValue())
			{ // Percentage chance to spawn something
				if(Controller.getGenerator().nextInt(20)==1)
				{
					data[i][j] = 5;
                    MapElementType elementType = chooseElementType();
					map.addEntry(chooseEntity(level, elementType, i, j, mazeFactories));
				}
			}
			}
	}

	private MapElementType chooseElementType()
	{
       int choice = Controller.getGenerator().nextInt(MapElementType.values().length);
       return MapElementType.values()[choice];
	}

	private Entity chooseEntity(int level, MapElementType type, int i, int j, MazeFactories mazeFactories)
	{
		switch(type)
		{
			case ARMOUR:
				return mazeFactories.chooseArmour(level).construct(i, j);
			case CONSUMABLE:
				return mazeFactories.chooseConsumable(level).construct(i, j);
			case MONSTER:
				return mazeFactories.chooseMonster(level).construct(i, j);
			case RELIC:
				return mazeFactories.chooseRelic(level).construct(i, j);
			case WEAPON:
				return mazeFactories.chooseWeapon(level).construct(i, j);
			default:
			{
				System.out.println("MazeGenerator::chooseEntity - unknown entity type");
				return mazeFactories.chooseMonster(level).construct(i, j); // TODO: Throw an exception.
			}
		}
	}

	private void addStairs(int level)
	{
		if(level > 1)
		{
			data[player.getRow()][player.getColumn()] = 5;
			upStairs = new Coordinate(player.getRow(), player.getColumn());
			map.addEntry(new UpStairs(player.getRow(), player.getColumn()));
		}

		for(;;)
		{
			int i = Controller.getGenerator().nextInt(map.getRows());
			int j = Controller.getGenerator().nextInt(map.getColumns());
			if(data[i][j]==FloorType.FLOOR.getValue())
			{
				data[i][j] = 5;
				map.addEntry(new DownStairs(i, j));
				downStairs = new Coordinate(i, j);
				return; // only add one set of stairs.
			}
		}
	}
}
