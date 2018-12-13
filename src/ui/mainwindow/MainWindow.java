package ui.mainwindow;

import control.*;
import entities.creatures.Player;
import ui.HelpWindow;
import ui.IRoot;
import ui.actions.Actions;
import ui.inventory.InventoryWindow;
import ui.shortcuts.Shortcuts;
import ui.skills.spells.SpellsPanel;
import ui.skills.techniques.TechniquesPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;

public class MainWindow extends JTabbedPane
{
	private IRoot root;
	private JPanel mainPanel;
	private Actions actions;
	private TechniquesPanel techniquesPanel;
	private SpellsPanel spellsPanel;
	private MapDisplay mapDisplay;
	private Messages messages;
	private OptionsPanel optionsPanel;
	private Options options;
	private DungeonManager dungeonManager;
	private static final int gameTabIndex = 0;

	public IRoot getRoot()
	{
		return root;
	}

	public void showTechniques()
	{
		techniquesPanel.refresh();
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 25;
		constraints.gridy = 3;
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
		constraints.gridy = 3;
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
		constraints.gridy = 3;
		mainPanel.add(actions, constraints);
		mainPanel.remove(techniquesPanel);
		mainPanel.remove(spellsPanel);
		requestFocus();
		repaint();
	}

	public void start()
	{
		messages = new Messages();
		dungeonManager = new DungeonManager();
		initialise();
	}

	private void initialise()
	{
		removeAll();

		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		options = new Options(false);
		optionsPanel = new OptionsPanel(options);

		this.addTab("Game", mainPanel);
		Player player = dungeonManager.getPlayer();
		Map map = dungeonManager.getMap();
		Stats stats = new Stats(player);
		Status status = new Status(player);

		InventoryWindow invWind = new InventoryWindow(player, stats, status, map);
		this.addTab("Inventory", invWind);

		HelpWindow helpWind = new HelpWindow();
		this.addTab("Help", helpWind);

		mapDisplay = new MapDisplay(map, player, stats, status, messages);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 30;
		constraints.gridy = 0;

		mainPanel.add(mapDisplay, constraints);
		dungeonManager.setMapDisplay(mapDisplay);

		Shortcuts shortcuts = new Shortcuts(player, messages, mapDisplay);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 5;
		constraints.gridy = 1;
		mainPanel.add(shortcuts, constraints);

		JPanel infoPanel = new JPanel();
		constraints.weighty = 35;
		constraints.gridy = 2;
		mainPanel.add(infoPanel, constraints);
		infoPanel.setLayout(new GridLayout(4,1));
		infoPanel.add(optionsPanel);
		infoPanel.add(stats);
		infoPanel.add(status);
		infoPanel.add(messages);

		techniquesPanel = new TechniquesPanel(this, player, mapDisplay, messages);
		mapDisplay.addRefreshable(techniquesPanel);
		spellsPanel = new SpellsPanel(this, player, mapDisplay, messages);
		mapDisplay.addRefreshable(spellsPanel);

		TurnHandler turnHandler = new TurnHandler(player, mapDisplay, messages, invWind, dungeonManager, options);
		Mover mover = new Mover(turnHandler);
		this.addKeyListener(mover);
		actions = new Actions(mapDisplay, this, turnHandler);
		showActions();

		messages.addMessage("A new game has begun!");

		// Refocus when returning to game tab.
		addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				if(getSelectedIndex() == gameTabIndex)
				{
					requestFocus();
				}
			}
		});

		this.setVisible(true);
	}

	public void start(PlayerInitialData playerInitialData)
	{
		messages = new Messages();
		dungeonManager = new DungeonManager(messages, playerInitialData);
		dungeonManager.nextLevel();
		initialise();
	}

	public MainWindow(IRoot inRoot)
	{
		int smallFontSize = Controller.bigFonts ? 12 : 8;
		int mediumFontSize = Controller.bigFonts ? 14 : 9;

		root = inRoot;
		UIDefaults uid = UIManager.getDefaults();
		Font labelFont = uid.getFont("Label.font");
		Font smallFont = new Font(labelFont.getName(), labelFont.getStyle(), smallFontSize);
		uid.put("Label.font", smallFont);
		Font mediumFont = new Font(smallFont.getName(), labelFont.getStyle(), mediumFontSize);
		uid.put("TextArea.font", mediumFont);
		uid.put("TextField.font", mediumFont);
		uid.put("Button.font", mediumFont);
	}

	public void gameOver(Grave grave)
	{
		dungeonManager.endGame(grave);
		root.changeToGameOver(grave);
	}

	public DungeonManager getDungeonManager(){return dungeonManager;}
}
