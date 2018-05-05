package ui.mainwindow;

import control.*;
import entities.Player;
import ui.RootFrame;
import ui.actions.Actions;
import ui.inventory.InventoryWindow;
import ui.skills.spells.SpellsPanel;
import ui.skills.techniques.TechniquesPanel;

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.Font;

public class MainWindow extends JTabbedPane
{
	private RootFrame rootFrame;
	private JPanel mainPanel;
	private Actions actions;
	private TechniquesPanel techniquesPanel;
	private SpellsPanel spellsPanel;
	private MapDisplay mapDisplay;

	private Messages messages;
	private OptionsPanel optionsPanel;
	private Options options;

	public RootFrame getRootFrame()
	{
		return rootFrame;
	}

	public void showTechniques()
	{
		techniquesPanel.refresh();
		mainPanel.add(techniquesPanel);
		mainPanel.remove(actions);
		repaint();
	}

	public void showSpells()
	{
		spellsPanel.refresh();
		mainPanel.add(spellsPanel);
		mainPanel.remove(actions);
		repaint();
	}

	public void showActions()
	{
		mainPanel.add(actions);
		mainPanel.remove(techniquesPanel);
		mainPanel.remove(spellsPanel);
		requestFocus();
		repaint();
	}

	public void start(PlayerInitialData playerInitialData)
	{
		DungeonManager dm = new DungeonManager(messages, playerInitialData);
		dm.nextLevel();
		Player player = dm.getPlayer();
		Map map = dm.getMap();
		Stats stats = new Stats(player);

		InventoryWindow invWind = new InventoryWindow(player, stats, map);
		this.addTab("Inventory", invWind);
		mapDisplay = new MapDisplay(map, player, stats, messages);
		TurnHandler turnHandler = new TurnHandler(player, mapDisplay, messages, invWind, dm, options);
		Mover mover = new Mover(turnHandler);
		this.addKeyListener(mover);
		mainPanel.add(mapDisplay);
		dm.setMapDisplay(mapDisplay);
		JPanel infoPanel = new JPanel();

		mainPanel.add(infoPanel);
		infoPanel.setLayout(new GridLayout(3,1));
		infoPanel.add(optionsPanel);
		infoPanel.add(stats);
		infoPanel.add(messages);

		techniquesPanel = new TechniquesPanel(this, player, mapDisplay, messages);
		spellsPanel = new SpellsPanel(this, player, mapDisplay, messages);
		actions = new Actions(mapDisplay, this, turnHandler);
		showActions();

		messages.addMessage("A new game has begun!");

		this.setVisible(true);
	}

	public MainWindow(RootFrame inRootFrame)
	{
		rootFrame = inRootFrame;
		UIDefaults uid = UIManager.getDefaults();
		Font font = uid.getFont("Label.font");
		Font newFont = new Font(font.getName(), font.getStyle(), 10);
		uid.put("Label.font", newFont);

		mainPanel = new JPanel();
		this.addTab("Game", mainPanel);

		mainPanel.setLayout(new GridLayout(3,1));

		options = new Options(false);
		optionsPanel = new OptionsPanel(options);
		messages = new Messages();
	}
}
