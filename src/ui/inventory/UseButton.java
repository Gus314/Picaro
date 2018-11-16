package ui.inventory;

import entities.creatures.Player;
import entities.equipment.*;
import ui.mainwindow.Stats;
import ui.mainwindow.Status;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class UseButton extends JButton
{
	private Item item;
	private Player player;
	private InventoryWindow invWindow;
	private Stats stats;
	private Status status;

	public UseButton(Item inItem, Player inPlayer, InventoryWindow inInvWindow, Stats inStats, Status inStatus)
	{
		item = inItem;
		player = inPlayer;
		invWindow = inInvWindow;
		stats = inStats;
		status = inStatus;
		this.setText("Use " + item.getName());
		this.addActionListener(new UseListener());
	}
	
	public class UseListener implements ActionListener
	{
		public UseListener() { }
		
		public void actionPerformed(ActionEvent ae)
		{
			Collection<Item> items = player.getItems();
			
			if(item instanceof Weapon)
			{
				Weapon oldWeapon = player.getWeapon();
				if(oldWeapon != null)
				{
					items.add(oldWeapon);
				}
				items.remove(item);
				player.setWeapon((Weapon)item);
			}
			else if(item instanceof Armour)
			{
				Armour newArmour = (Armour)item;
				Armour oldArmour = player.getArmour(newArmour.getArmourType());
				if(oldArmour != null)
				{
					items.add(oldArmour);
				}
				player.setArmour((Armour)item);
				items.remove(item);	
			}
			else if(item instanceof Relic)
			{
				if(player.canAddRelic())
				{
					player.addRelic((Relic)item);
					items.remove(item);
				}
			}
			else if(item instanceof Consumable)
			{
				((Consumable) item).consume();
				items.remove(item);
			}
			stats.refresh();
			status.refresh();
			invWindow.refresh();
		}
	}

}
