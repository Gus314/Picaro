package ui;

import control.Map;
import entities.Entity;
import entities.Floor;
import ui.Stats;

import java.awt.GridLayout;
import java.util.Vector;
import java.util.ArrayList;
import javax.swing.*;

public class MapDisplay extends JPanel
{
	private Map map;
	private Vector<JLabel> cells;
	private Entity player;
	private Stats stats;
	
	public void setMap(Map inMap)
	{
		map = inMap;
		this.removeAll();
		cells = new Vector<JLabel>();
		initialise();
		map.addEntry(player);
		refresh();
	}
	
	public MapDisplay(Map inMap, Entity inPlayer, Stats inStats)
	{
		super();
		GridLayout grid = new GridLayout(inMap.getRows(), inMap.getColumns());
		this.setLayout(grid);
		map = inMap;
		cells = new Vector<JLabel>();
		initialise();
		player = inPlayer;
		map.addEntry(player);
		stats = inStats;
		refresh();
	}
	
	public Map getMap()
	{
		return map;
	}
	
	private void initialise()
	{
		int entries = map.getRows() * map.getColumns();
		for(int i = 0; i < entries; i++)
		{
			JLabel l = new JLabel(" ");
			cells.add(l);
			this.add(l);
		}
	}
	
	public void refresh()
	{
		int numEntries = map.getRows() * map.getColumns();
		for(int i = 0; i < numEntries; i++)
		{
			cells.get(i).setText(" ");
		}
		
		ArrayList<Entity> entries = map.getMapEntries();
		for(Entity ent: entries)
		{
			int row = ent.getRow();
			int column = ent.getColumn();
			int position = row*map.getColumns() + column;
			if(row < 0 || column < 0)
				entries.remove(ent);
			else if((!(ent instanceof Floor)) || cells.get(position).getText().equals(" "))
			{
				cells.get(position).setText(ent.getChar().toString());
			}
		}
		stats.refresh();
	}
}
