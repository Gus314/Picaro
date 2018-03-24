package mapgeneration;

import control.Map;
import entities.Player;
import entities.Stairs;
import entities.factories.*;
import enums.ConsumableType;
import enums.RelicEffect;
import mapgeneration.RoomGraph;
import ui.Messages;

import java.util.Random;
import java.util.Vector;

public class MazeGenerator 
{	
	private static final Random generator = new Random();
	private Map map;
	private int[][] data;
	private Messages messages;
	private Player player;
	private Vector<CreatureFactory> creatureFactories;
	private Vector<ArmourFactory> armourFactories;
	private Vector<WeaponFactory> weaponFactories;
	private Vector<ConsumableFactory> consumableFactories;
	private Vector<RelicFactory> relicFactories;
	private int level;
	
	public MazeGenerator(int inMinRoomSize, int inMaxRoomSize, Messages inMessages)
	{
		data = null;
		messages = inMessages;
	}
	
	public Map getMap()
	{
		return map;
	}
	
	public void construct(int inLevel)
	{
		level = inLevel;

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
				if(data[i][j] == 2)
				{
					map.addEntry(floorFac.construct(i,j));
				}
				final boolean adjacentFloor = ((i > 0) && data[i-1][j] == 2) ||
						((i < data.length-1) && data[i+1][j] == 2) ||
						((j > 0) && data[i][j-1] == 2) ||
						((j < data.length-1) && data[i][j+1] == 2) ||
						(i > 0 && j > 0 && data[i-1][j-1] == 2) ||
						(i < data.length - 1 && j < data.length - 1 && data[i+1][j+1] == 2) ||
						(i > 0 && j < data.length - 1 && data[i-1][j+1] == 2) ||
						(i < data.length - 1 && j > 0 && data[i+1][j-1] == 2);
				if(data[i][j]==0 && adjacentFloor)
				{
					map.addEntry(wallfac.construct(i, j));
				}
			}
		
		
	}
		
	public void positionPlayer(Player inPlayer)
	{
		for(;;)
		{
			int row = generator.nextInt(map.getRows());
			int column = generator.nextInt(map.getColumns());
			if(data[row][column]==2)
			{
				data[row][column] = 3;
				player = inPlayer;
				player.setRow(row);
				player.setColumn(column);
				//map.addEntry(player);
				return;
			}
		}
	}
	
	public void initialiseFactories()
	{
		creatureFactories = new Vector<CreatureFactory>();
		creatureFactories.add(new CreatureFactory('G', 20, 5, 2, "Goblin", map, messages, 20,1));
		creatureFactories.add(new CreatureFactory('O', 30, 5, 4, "Orc", map, messages, 20,2));
		creatureFactories.add(new CreatureFactory('E', 40, 10, 2, "Elephant", map, messages, 20,2));
		
		weaponFactories = new Vector<WeaponFactory>();
		weaponFactories.add(new WeaponFactory(3, 5, 10, "Sword",1));
		weaponFactories.add(new WeaponFactory(6, 10, 10, "LongSword",2));
		weaponFactories.add(new WeaponFactory(4, 7, 20, "BastardSword",2));
		
		armourFactories = new Vector<ArmourFactory>();
		armourFactories.add(new ArmourFactory(5, 2, 4, "Chainmail",1));
		armourFactories.add(new ArmourFactory(7, 3, 6, "Platemail",2));
		
		consumableFactories = new Vector<ConsumableFactory>();
		consumableFactories.add(new ConsumableFactory('p', 50, ConsumableType.RestoreHP, messages, "hp potion", player,1));
		consumableFactories.add(new ConsumableFactory('p', 75, ConsumableType.RestoreHP, messages, "large hp potion", player,2));
		
		relicFactories = new Vector<RelicFactory>();
		relicFactories.add(new RelicFactory(RelicEffect.Damage, 5, "blade", 1));
		relicFactories.add(new RelicFactory(RelicEffect.CritChance, 2, "pin", 1));
		relicFactories.add(new RelicFactory(RelicEffect.Defense, 5, "cover", 1));
		relicFactories.add(new RelicFactory(RelicEffect.BlockChance, 4, "shield", 1));
		relicFactories.add(new RelicFactory(RelicEffect.AbsorbChance, 2, "vampire", 1));
		
		relicFactories.add(new RelicFactory(RelicEffect.Damage, 5, "sharp blade", 2));
		relicFactories.add(new RelicFactory(RelicEffect.CritChance, 5, "sharp pin", 2));
		relicFactories.add(new RelicFactory(RelicEffect.Defense, 5, "sturdy cover", 2));
		relicFactories.add(new RelicFactory(RelicEffect.BlockChance, 5, "powerful shield", 2));
		relicFactories.add(new RelicFactory(RelicEffect.AbsorbChance, 5, "crafty vampire", 2));
	}
	
	public void addRelics()
	{
		Vector<RelicFactory> relics = new Vector<RelicFactory>();
		
		for(RelicFactory rf: relicFactories)
		{
			if(rf.getLevel() == level)
			{
				relics.add(rf);
			}
		}
		
		for(int i = 0; i < map.getRows(); i++)
			for(int j = 0; j < map.getColumns(); j++)
			{
			if(data[i][j]==1)
			{ // Percentage chance to spawn monster
				if(generator.nextInt(40)==1)
				{
					data[i][j] = 5;
					int choice = generator.nextInt(relics.size());
					map.addEntry(relics.get(choice).construct(i, j));
				}
			}
			}
	}
	
	public void addMonsters()
	{
		Vector<CreatureFactory> creatures = new Vector<CreatureFactory>();
		
		for(CreatureFactory cf: creatureFactories)
		{
			if(cf.getLevel() == level)
			{
				cf.setMap(map);
				creatures.add(cf);
			}
		}
		
		for(int i = 0; i < map.getRows(); i++)
			for(int j = 0; j < map.getColumns(); j++)
			{
			if(data[i][j]==1)
			{ // Percentage chance to spawn monster
				if(generator.nextInt(40)==1)
				{
					data[i][j] = 3;
					int choice = generator.nextInt(creatures.size());
					map.addEntry(creatures.get(choice).construct(i, j));
				}
			}
			}
	}
	
	public void addStairs()
	{
		for(;;)
		{
			int i = generator.nextInt(map.getRows());
			int j = generator.nextInt(map.getColumns());
			if(data[i][j]==2)
			{
				data[i][j] = 5;
				map.addEntry(new Stairs(i, j));
				return; // only add one set of stairs.
			}
		}
	}
	
	public void addItems()
	{
		if(player == null)
		{
			System.out.println("Error: Must add player before items!");
			System.exit(-1);
		}
		
		Vector<WeaponFactory> weapons = new Vector<WeaponFactory>();
		for(WeaponFactory wf: weaponFactories)
		{
			if(wf.getLevel() == level)
			{
				weapons.add(wf);
			}
		}
		
		Vector<ArmourFactory> armours = new Vector<ArmourFactory>();
		for(ArmourFactory af: armourFactories)
		{
			if(af.getLevel() == level)
			{
				armours.add(af);
			}
		}
		
		Vector<ConsumableFactory> consumables = new Vector<ConsumableFactory>();
		for(ConsumableFactory cf: consumableFactories)
		{
			if(cf.getLevel() == level)
			{
				consumables.add(cf);
			}
		}
		
		for(int i = 0; i < map.getRows(); i++)
			for(int j = 0; j < map.getColumns(); j++)
			{
			if(data[i][j]==1)
			{ // Percentage chance to spawn weapon
				if(generator.nextInt(150)==1)
				{
					data[i][j] = 4;
					int choice = generator.nextInt(weapons.size());
					map.addEntry(weapons.get(choice).construct(i, j));
				}
			}
			}
		
		for(int i = 0; i < map.getRows(); i++)
			for(int j = 0; j < map.getColumns(); j++)
			{
			if(data[i][j]==1)
			{ // Percentage chance to spawn armour
				if(generator.nextInt(150)==1)
				{
					data[i][j] = 4;
					int choice = generator.nextInt(armours.size());
					map.addEntry(armours.get(choice).construct(i, j));
				}
			}
			}
		
		for(int i = 0; i < map.getRows(); i++)
			for(int j = 0; j < map.getColumns(); j++)
			{
			if(data[i][j]==1)
			{ // Percentage chance to spawn consumable
				if(generator.nextInt(150)==1)
				{
					data[i][j] = 4;
					int choice = generator.nextInt(consumables.size());
					map.addEntry(consumables.get(choice).construct(i, j));
				}
			}
			}
	}
}
