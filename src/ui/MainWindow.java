package ui;

import control.DungeonManager;
import control.Map;
import control.TurnHandler;
import entities.Player;
import ui.skills.spells.SpellsPanel;
import ui.skills.techniques.TechniquesPanel;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import java.awt.GridLayout;
import java.awt.Font;

public class MainWindow extends JTabbedPane
{
	private JPanel mainPanel;
	private Actions actions;
	private TechniquesPanel techniquesPanel;
	private SpellsPanel spellsPanel;
	private MapDisplay mapDisplay;

	public void showTechniques()
	{
		techniquesPanel.refresh();
		mainPanel.add(techniquesPanel);
		mainPanel.remove(actions);
	}

	public void showSpells()
	{
		spellsPanel.refresh();
		mainPanel.add(spellsPanel);
		mainPanel.remove(actions);
	}

	public void showActions()
	{
		mainPanel.add(actions);
		mainPanel.remove(techniquesPanel);
		mainPanel.remove(spellsPanel);
	}

	public MainWindow()
	{	
		UIDefaults uid = UIManager.getDefaults();
		Font font = uid.getFont("Label.font");
		Font newFont = new Font(font.getName(), font.getStyle(), 10);
		uid.put("Label.font", newFont);
		
	mainPanel = new JPanel();
	this.addTab("Game", mainPanel);
	
	mainPanel.setLayout(new GridLayout(3,1));
	Messages messages = new Messages();
	
	DungeonManager dm = new DungeonManager(messages);
	dm.nextLevel();
	Player player = dm.getPlayer();
	Map map = dm.getMap();
	Stats stats = new Stats(player);

	InventoryWindow invWind = new InventoryWindow(player, stats);
	this.addTab("Inventory", invWind);
	mapDisplay = new MapDisplay(map, player, stats, messages);
	TurnHandler turnHandler = new TurnHandler(player, mapDisplay, messages, invWind, dm);
	Mover mover = new Mover(turnHandler);
	this.addKeyListener(mover);
	mainPanel.add(mapDisplay);
	dm.setMapDisplay(mapDisplay);
	JPanel infoPanel = new JPanel();

	mainPanel.add(infoPanel);
	infoPanel.setLayout(new GridLayout(2,1));
	infoPanel.add(stats);
	infoPanel.add(messages);

	techniquesPanel = new TechniquesPanel(this, player, mapDisplay, messages);
	spellsPanel = new SpellsPanel(this, player, mapDisplay, messages);
	actions = new Actions(mapDisplay, this);
	showActions();

	messages.addMessage("A new game has begun!");

	this.setVisible(true);
	}
}
