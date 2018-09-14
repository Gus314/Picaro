package ui.mainwindow;

import control.*;
import entities.Player;
import ui.RootFrame;
import ui.actions.Actions;
import ui.inventory.InventoryWindow;
import ui.skills.spells.SpellsPanel;
import ui.skills.techniques.TechniquesPanel;

import javax.swing.*;

import java.awt.*;

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
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 25;
		constraints.gridy = 2;
		mainPanel.add(techniquesPanel, constraints);
		mainPanel.remove(actions);
		repaint();
	}

	public void showSpells()
	{
		spellsPanel.refresh();
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 25;
		constraints.gridy = 2;
		mainPanel.add(spellsPanel, constraints);
		mainPanel.remove(actions);
		repaint();
	}

	public void showActions()
	{
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 25;
		constraints.gridy = 2;
		mainPanel.add(actions, constraints);
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
		Status status = new Status(player);

		InventoryWindow invWind = new InventoryWindow(player, stats, status, map);
		this.addTab("Inventory", invWind);
		mapDisplay = new MapDisplay(map, player, stats, status, messages);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 30;
		constraints.gridy = 0;

		mainPanel.add(mapDisplay, constraints);
		dm.setMapDisplay(mapDisplay);

		JPanel infoPanel = new JPanel();
		constraints.weighty = 30;
		constraints.gridy = 1;
		mainPanel.add(infoPanel, constraints);
		infoPanel.setLayout(new GridLayout(4,1));
		infoPanel.add(optionsPanel);
		infoPanel.add(stats);
		infoPanel.add(status);
		infoPanel.add(messages);

		techniquesPanel = new TechniquesPanel(this, player, mapDisplay, messages);
		spellsPanel = new SpellsPanel(this, player, mapDisplay, messages);

		TurnHandler turnHandler = new TurnHandler(player, mapDisplay, messages, invWind, dm, options);
		Mover mover = new Mover(turnHandler);
		this.addKeyListener(mover);
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
		mainPanel.setLayout(new GridBagLayout());

		options = new Options(false);
		optionsPanel = new OptionsPanel(options);
		messages = new Messages();
	}
}
