package ui;

import entities.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class UseButton extends JButton
{
	private Item item;
	private Player player;
	private InventoryWindow invWindow;
	private Stats stats;
	
	public UseButton(Item inItem, Player inPlayer, InventoryWindow inInvWindow, Stats inStats)
	{
		item = inItem;
		player = inPlayer;
		invWindow = inInvWindow;
		stats = inStats;
		this.setText("Use " + item.getName());
		this.addActionListener(new UseListener(this));
	}
	
	
	public class UseListener implements ActionListener
	{
		private UseButton useButton;
		
		public UseListener(UseButton inUseButton)
		{
			useButton = inUseButton;
		}
		
		public void actionPerformed(ActionEvent ae)
		{
			Vector<Item> items = player.getItems();
			
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
				Armour oldArmour = player.getArmour();
				if(oldArmour != null)
				{
					items.add(oldArmour);
				}
				player.setArmour((Armour)item);
				items.remove(item);	
			}
			else if(item instanceof Relic)
			{
				Relic oldRelic = player.getRelic();
				if(oldRelic != null)
				{
					items.add(oldRelic);
				}
				player.setRelic((Relic)item);
				items.remove(item);	
			}
			else if(item instanceof Consumable)
			{
				((Consumable) item).consume();
				items.remove(item);
			}
			stats.refresh();
			invWindow.getUsePanel().remove(useButton);
			invWindow.refresh();
		}
	}

}
