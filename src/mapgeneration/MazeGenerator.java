package mapgeneration;

import control.Controller;
import control.Coordinate;
import control.Map;
import entities.*;
import entities.creatures.FinalBoss;
import entities.creatures.Player;
import entities.factories.*;
import enums.Faction;
import enums.FloorType;
import enums.MapElementType;
import skills.Skill;
import ui.mainwindow.Messages;

import java.io.Serializable;
import java.util.ArrayList;

public class MazeGenerator implements Serializable
{
	private Map map;
	private Messages messages;
	private Player player;
	private Coordinate upStairs;
	private Coordinate downStairs;
	RoomGraph graph;
	private int finalLevel = 16;

	public MazeGenerator(int rows, int columns, int cellRows, int cellColumns, int properRoomChance, int randomFloorPercentage, Messages inMessages)
	{
		messages = inMessages;
		upStairs = null;
		downStairs = null;
		graph = new RoomGraph(rows, columns, cellRows, cellColumns, properRoomChance, randomFloorPercentage);
	}

	public void loadPersistedMap(PersistedMap persistedMap)
	{
		map = persistedMap.getMap();
		upStairs = persistedMap.getUpStairs();
		downStairs = persistedMap.getDownStairs();
	}

	public void setRandomFloorPercentage(int inRandomFloorPercentage)
	{
		graph.setRandomFloorPercentage(inRandomFloorPercentage);
	}

	public void setProperRoomChance(int inProperRoomChance)
	{
		graph.setProperRoomChance(inProperRoomChance);
	}

	public Player getPlayer(){return player;}

	public PersistedMap getPersistedMap()
	{
		return new PersistedMap(map, upStairs, downStairs);
	}

	public Map getMap()
	{
		return map;
	}

	public void construct(int level, Player inPlayer)
	{
		player = inPlayer;
		map = new Map(graph.getRows(), graph.getColumns());
	    Integer[][] data = graph.determineLayout();
		
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
						((j < data[0].length-1) && data[i][j+1] == FloorType.FLOOR.getValue()) ||
						(i > 0 && j > 0 && data[i-1][j-1] == FloorType.FLOOR.getValue()) ||
						(i < data.length - 1 && j < data[0].length - 1 && data[i+1][j+1] == FloorType.FLOOR.getValue()) ||
						(i > 0 && j < data[0].length - 1 && data[i-1][j+1] == FloorType.FLOOR.getValue()) ||
						(i < data.length - 1 && j > 0 && data[i+1][j-1] == FloorType.FLOOR.getValue());

				boolean floorNextToEmpty = data[i][j]==0 && adjacentFloor;
				boolean edge = (i == 0) || (j == 0) || (i == data.length - 1) || (j == data[0].length-1);
				if(floorNextToEmpty || edge)
				{
					if(edge)
					{
						// Ensure no entities are placed on this wall.
						data[i][j] = FloorType.EMPTY.getValue();
					}
					map.addEntry(wallfac.construct(i, j));
				}
			}
		
		positionPlayer(inPlayer, data);
		MazeFactories mazeFactories = new MazeFactories(map, messages, inPlayer);

		addEntities(level, mazeFactories, data);

		addStairs(level, data);

	}
		
	private void positionPlayer(Player inPlayer, Integer[][] data)
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

	public void addEntities(int level, MazeFactories mazeFactories, Integer[][] data)
	{
		if(level == finalLevel)
		{
			addEntitiesToFinalLevel(data);
			return;
		}

		for(int i = 0; i < map.getRows(); i++)
			for(int j = 0; j < map.getColumns(); j++)
			{
				if(data[i][j]==FloorType.FLOOR.getValue())
				{ // Percentage chance to spawn something
					if(Controller.getGenerator().nextInt(32)==1)
					{
						data[i][j] = 5;
						MapElementType elementType = MapElementSelector.chooseElementType();
						map.addEntry(chooseEntity(level, elementType, i, j, mazeFactories));
					}
				}
			}
	}

	private void addEntitiesToFinalLevel(Integer[][] data)
	{
		// Final boss must be placed or game is unwinnable.
		while(true)
		{
			for(int i = 0; i < map.getRows(); i++)
				for(int j = 0; j < map.getColumns(); j++)
				{
					if(data[i][j]==FloorType.FLOOR.getValue())
					{ // Percentage chance to spawn something
						if(Controller.getGenerator().nextInt(32)==1)
						{
							data[i][j] = 5;
							FinalBoss finalBoss = new FinalBoss(i, j, map, messages, finalLevel);
							map.addEntry(finalBoss);
							return;
						}
					}
				}
		}
	}

	private Entity chooseEntity(int level, MapElementType type, int i, int j, MazeFactories mazeFactories)
	{
		switch(type)
		{
			case ARMOUR:
				return mazeFactories.chooseArmour(level).construct(i, j);
			case CONSUMABLE:
				return mazeFactories.chooseConsumable(level).construct(i, j);
			case FURNITURE:
			    return mazeFactories.chooseFurniture(level).construct(i, j);
			case MONSTER:
				return mazeFactories.chooseMonster(level).construct(i, j);
			case RELIC:
				return mazeFactories.chooseRelic(level).construct(i, j);
			case WEAPON:
				return mazeFactories.chooseWeapon(level).construct(i, j);
			case TOTEM:
				return mazeFactories.chooseTotem(level).construct(i, j);
			default:
			{
				System.out.println("MazeGenerator::chooseEntity - unknown entity type");
				return mazeFactories.chooseMonster(level).construct(i, j);
			}
		}
	}

	private void addStairs(int level, Integer[][] data)
	{
		if(level > 1)
		{
			data[player.getRow()][player.getColumn()] = 5;
			upStairs = new Coordinate(player.getRow(), player.getColumn());
			map.addEntry(new UpStairs(player.getRow(), player.getColumn()));
		}

		if(level == finalLevel)
		{
			// The final level should not have down stairs.
			return;
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
