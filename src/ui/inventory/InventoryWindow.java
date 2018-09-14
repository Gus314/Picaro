package ui.inventory;

import entities.*;
import enums.ArmourType;
import ui.mainwindow.Stats;
import ui.mainwindow.Status;

import javax.swing.*;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

public class InventoryWindow extends JPanel
{
	private Player player;
	private Stats stats;
	private control.Map map;
	private Map<ArmourType, ArmourPanel> armourPanels;
	private WeaponPanel weaponPanel;
	private RelicPanel relicPanel;
	private ItemPanel itemPanel;
	private JPanel overviewPanel;

	public InventoryWindow(Player inPlayer, Stats inStats, Status inStatus, control.Map inMap)
	{
		this.setLayout(new GridLayout(2,1));
		map = inMap; // Only requires interface to add item?

		overviewPanel = new JPanel();
		overviewPanel.setLayout(new GridLayout(6,1));
        add(overviewPanel);

		stats = inStats;
		player = inPlayer;
		armourPanels = new HashMap<ArmourType, ArmourPanel>();
        weaponPanel = new WeaponPanel(player.getWeapon());
        relicPanel = new RelicPanel(player.getRelic());
        itemPanel = new ItemPanel(player, stats, inStatus,this, map);

		overviewPanel.add(weaponPanel);
		for(ArmourType armourType: ArmourType.values())
		{
			ArmourPanel armourPanel = new ArmourPanel(armourType);
			armourPanels.put(armourType, armourPanel);
			overviewPanel.add(armourPanel);
		}
		overviewPanel.add(relicPanel);
		add(new JScrollPane(itemPanel));
	}
	
	public void refresh()
	{
		weaponPanel.refresh(player.getWeapon());
		for(ArmourType armourType: ArmourType.values())
		{
			armourPanels.get(armourType).refresh(player.getArmour(armourType));
		}
		relicPanel.refresh(player.getRelic());
		itemPanel.refresh(player.getItems());
	}
}
