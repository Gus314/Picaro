package ui;

import control.DungeonManager;
import control.Map;
import control.TurnHandler;
import entities.Player;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import java.awt.GridLayout;
import java.awt.Font;

public class MainWindow extends JTabbedPane
{
	public MainWindow()
	{	
		UIDefaults uid = UIManager.getDefaults();
		Font font = uid.getFont("Label.font");
		Font newFont = new Font(font.getName(), font.getStyle(), 10);
		uid.put("Label.font", newFont);
		
	JPanel panel = new JPanel();
	this.addTab("Game", panel);
	
	panel.setLayout(new GridLayout(2,1));
	Messages messages = new Messages();
	
	DungeonManager dm = new DungeonManager(messages);
	dm.nextLevel();
	Player player = dm.getPlayer();
	Map map = dm.getMap();
	Stats stats = new Stats(player);
	
	
	InventoryWindow invWind = new InventoryWindow(player, stats);
	this.addTab("Inventory", invWind);
	MapDisplay mapDisplay = new MapDisplay(map, player, stats);
	TurnHandler turnHandler = new TurnHandler(player, mapDisplay, messages, invWind, dm);
	Mover mover = new Mover(turnHandler);

	this.addKeyListener(new Mover(turnHandler));
	panel.add(mapDisplay);
	dm.setMapDisplay(mapDisplay);
	JPanel infoPanel = new JPanel();
	panel.add(infoPanel);
	
	infoPanel.setLayout(new GridLayout(2,1));
	infoPanel.add(stats);
	infoPanel.add(messages);
	messages.addMessage("A new game has begun!");
	this.setVisible(true);
	}
}
