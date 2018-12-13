package ui.inventory;

import entities.creatures.Player;
import entities.equipment.*;
import ui.inventory.interfaces.IItemProvider;
import ui.mainwindow.Stats;
import ui.mainwindow.Status;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Optional;

public class UseButton extends JButton
{
	private IItemProvider itemProvider;
	private Player player;
	private InventoryWindow invWindow;
	private Stats stats;
	private Status status;

	public UseButton(IItemProvider inItemProvider, Player inPlayer, InventoryWindow inInvWindow, Stats inStats, Status inStatus)
	{
		itemProvider = inItemProvider;
		player = inPlayer;
		invWindow = inInvWindow;
		stats = inStats;
		status = inStatus;
		this.setText("Use");
		this.addActionListener(new UseListener());
	}
	
	public class UseListener implements ActionListener
	{
		public UseListener() { }
		
		public void actionPerformed(ActionEvent ae)
		{
			Collection<Item> items = player.getItems();
			Optional<Item> itemOptional = itemProvider.getItem();

			if(!itemOptional.isPresent())
			{
				return;
			}

			Item item = itemOptional.get();

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
