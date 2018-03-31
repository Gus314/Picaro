package ui;

import control.DungeonManager;
import control.Map;
import control.TurnHandler;
import entities.Player;
import ui.spells.SpellsDialog;
import ui.techniques.TechniquesDialog;

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
	private TechniquesDialog techniquesDialog;
	private SpellsDialog spellsDialog;

	public void showTechniques()
	{
		techniquesDialog.refresh();
		mainPanel.add(techniquesDialog);
		mainPanel.remove(actions);
	}

	public void showSpells()
	{
		spellsDialog.refresh();
		mainPanel.add(spellsDialog);
		mainPanel.remove(actions);
	}

	public void showActions()
	{
		mainPanel.add(actions);
		mainPanel.remove(techniquesDialog);
		mainPanel.remove(spellsDialog);
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
	MapDisplay mapDisplay = new MapDisplay(map, player, stats, messages);
	TurnHandler turnHandler = new TurnHandler(player, mapDisplay, messages, invWind, dm);
	Mover mover = new Mover(turnHandler);
	this.addKeyListener(mover);
	mainPanel.add(mapDisplay);
	dm.setMapDisplay(mapDisplay);
	JPanel infoPanel = new JPanel();

	techniquesDialog = new TechniquesDialog(this, player);
	spellsDialog = new SpellsDialog(this, player);
	actions = new Actions(mapDisplay, this);
	showActions();

	mainPanel.add(infoPanel);
	infoPanel.setLayout(new GridLayout(2,1));
	infoPanel.add(stats);
	infoPanel.add(messages);
	messages.addMessage("A new game has begun!");

	this.setVisible(true);
	}
}
