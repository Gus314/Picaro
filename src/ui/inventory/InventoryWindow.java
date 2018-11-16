package ui.inventory;

import entities.creatures.Player;
import ui.mainwindow.Stats;
import ui.mainwindow.Status;

import javax.swing.*;
import java.awt.GridLayout;

public class InventoryWindow extends JPanel
{
	private Player player;
	private Stats stats;
	private control.Map map;
	private ArmourPanel armourPanel;
	private WeaponPanel weaponPanel;
	private RelicPanel relicPanel;
	private ItemPanel itemPanel;
	private JPanel overviewPanel;

	public InventoryWindow(Player inPlayer, Stats inStats, Status inStatus, control.Map inMap)
	{
		this.setLayout(new GridLayout(2,1));
		map = inMap; // Only requires interface to add item?

		overviewPanel = new JPanel();
		overviewPanel.setLayout(new GridLayout(3,1));
        add(overviewPanel);

		stats = inStats;
		player = inPlayer;
        weaponPanel = new WeaponPanel(player.getWeapon());
        armourPanel = new ArmourPanel(player.getArmours());
        relicPanel = new RelicPanel(this, player);
        itemPanel = new ItemPanel(player, stats, inStatus,this, map);

		overviewPanel.add(weaponPanel);
		overviewPanel.add(armourPanel);
		overviewPanel.add(relicPanel);
		add(new JScrollPane(itemPanel));
		refresh();
	}
	
	public void refresh()
	{
		weaponPanel.refresh(player.getWeapon());
		armourPanel.refresh(player.getArmours());
		relicPanel.refresh(player.getRelics());
		itemPanel.refresh(player.getItems());
	}
}
