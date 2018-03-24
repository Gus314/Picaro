package ui;

import entities.*;
import enums.RelicEffect;

import javax.swing.*;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Vector;

public class InventoryWindow extends JPanel
{
	private JLabel wMinDamage;
	private JLabel wMaxDamage;
	private JLabel wCritChance;
	private JLabel wName;
	private JLabel aDefense;
	private JLabel aName;
	private JLabel aBlockChance;
	private JLabel aAbsorbChance;
	
	private JLabel rName;
	private JLabel rEffect;
	private JLabel rAmount;
	
	private Player player;
	private JTable items;
	private JPanel usePanel;
	private Stats stats;
	
	public JPanel getUsePanel()
	{
		return usePanel;
	}
	
	public void focus(int height, int width)
	{
		Container con = this;
		Container newCon = this;
		while(newCon != null)
		{
			con = newCon;
			newCon = con.getParent();
		}
	}
	
	
	public InventoryWindow(Player inPlayer, Stats inStats)
	{
		this.setLayout(new GridLayout(4,1));
		
		stats = inStats;
		player = inPlayer;
		Weapon wep = player.getWeapon();
		JPanel wepPanel = new JPanel();
		this.add(wepPanel);
		wepPanel.add(new JLabel("|entities.Weapon:"));
		if(wep!=null)
		{
			wName = new JLabel(wep.getName());
			wepPanel.add(wName);
			wepPanel.add(new JLabel("|Min Damage:"));	
			wMinDamage = new JLabel(((Integer)(wep.getMinDamage())).toString());
			wepPanel.add(wMinDamage);
			wepPanel.add(new JLabel("|Max Damage:"));	
			wMaxDamage = new JLabel(((Integer)(wep.getMaxDamage())).toString());
			wepPanel.add(wMaxDamage);
			wepPanel.add(new JLabel("|Crit Chance:"));	
			wCritChance = new JLabel(((Integer)(wep.getCritChance())).toString());
			wepPanel.add(wCritChance);	
		}
		else
		{
			wName = new JLabel("None:");
			wepPanel.add(wName);
			wepPanel.add(new JLabel("|Min Damage:"));	
			wMinDamage = new JLabel("0");
			wepPanel.add(wMinDamage);
			wepPanel.add(new JLabel("|Max Damage:"));	
			wMaxDamage = new JLabel("0");
			wepPanel.add(wMaxDamage);
			wepPanel.add(new JLabel("|Crit Chance:"));	
			wCritChance = new JLabel("0");
			wepPanel.add(wCritChance);
		}
		
		Armour arm = player.getArmour();
		JPanel armPanel = new JPanel();
		this.add(armPanel);
		armPanel.add(new JLabel("|entities.Armour:"));
		if(arm!=null)
		{
			aName = new JLabel(arm.getName());
			armPanel.add(aName);
			armPanel.add(new JLabel("|Defense:"));	
			aDefense = new JLabel(((Integer)(arm.getDefense())).toString());
			armPanel.add(aDefense);
			
			armPanel.add(new JLabel("|Block Chance:"));	
			aBlockChance = new JLabel(((Integer)(arm.getBlockChance())).toString());
			armPanel.add(aBlockChance);
			
			armPanel.add(new JLabel("|Absorb Chance:"));	
			aAbsorbChance = new JLabel(((Integer)(arm.getAbsorbChance())).toString());
			armPanel.add(aAbsorbChance);
		}
		else
		{
			aName = new JLabel("None");
			armPanel.add(aName);
			armPanel.add(new JLabel("|Defense:"));	
			aDefense = new JLabel("0");
			armPanel.add(aDefense);
			
			armPanel.add(new JLabel("|Block Chance:"));	
			aBlockChance = new JLabel("0");
			armPanel.add(aBlockChance);
			
			armPanel.add(new JLabel("|Absorb Chance:"));	
			aAbsorbChance = new JLabel("0");
			armPanel.add(aAbsorbChance);
		}
			
		Relic relic = player.getRelic();
		JPanel relicPanel = new JPanel();
		this.add(relicPanel);
		relicPanel.add(new JLabel("|entities.Relic:"));
		if(relic!=null)
		{
			rName = new JLabel(relic.getName());
			relicPanel.add(rName);
			relicPanel.add(new JLabel("|Effect:"));	
			rEffect = new JLabel((relic.getEffect()).toString());
			relicPanel.add(rEffect);
			relicPanel.add(new JLabel("|Amount:"));	
			rAmount = new JLabel(((Integer)(relic.getAmount())).toString());
			relicPanel.add(rAmount);
		}
		else
		{
			rName = new JLabel("None:");
			relicPanel.add(rName);
			relicPanel.add(new JLabel("|Effect:"));	
			rEffect = new JLabel("None");
			relicPanel.add(rEffect);
			relicPanel.add(new JLabel("|Amount:"));	
			rAmount = new JLabel("0");
			relicPanel.add(rAmount);
		}
		
		JPanel itemPanel = new JPanel();
		usePanel = new JPanel();
		
		JPanel invPanel = new JPanel();
		usePanel.setLayout(new GridLayout(10, 1));
		invPanel.setLayout(new GridLayout(1, 2));
		
		//this.add(invPanel);
		JScrollPane scroller = new JScrollPane(invPanel);
		this.add(scroller);
		
		invPanel.add(itemPanel);
		invPanel.add(usePanel);
		usePanel.add(new JLabel("Use"));
		Vector<Item> itemVec = player.getItems();
		itemPanel.add(new JLabel("Items:"));
		items = new JTable(11, 8);
		/* table layout
		 * name, type, mindamage, maxdamage, critchance, defense, , blockchance, absorbchance, usebutton
		 */
		itemPanel.add(items);
		
		items.setValueAt("Name", 0, 0);
		items.setValueAt("Type", 0, 1);
		items.setValueAt("Min", 0, 2);
		items.setValueAt("Max", 0, 3);
		items.setValueAt("Crit", 0, 4);
		items.setValueAt("Defense", 0, 5);
		items.setValueAt("Block", 0, 6);
		items.setValueAt("Absorb", 0, 7);
	//	items.setValueAt("Use", 0, 6);
		
		if(itemVec.size() > 0)
		{
			int itemNum = 0;
			for(Item item: itemVec)
			{
				items.setValueAt(item.getName(), itemNum+1, 0);
				if(item instanceof Weapon)
				{			
					Weapon wItem = (Weapon) item;
					items.setValueAt("entities.Weapon", itemNum+1, 1);
					items.setValueAt(wItem.getMinDamage(), itemNum+1, 2);
					items.setValueAt(wItem.getMaxDamage(), itemNum+1, 3);
					items.setValueAt(wItem.getCritChance(), itemNum+1, 4);
					items.setValueAt(0, itemNum+1, 5);
					items.setValueAt(0, itemNum+1, 6);
					items.setValueAt(0, itemNum+1, 6);
				}
				else if(item instanceof Armour)
				{
					Armour aItem= (Armour) item;
					items.setValueAt("entities.Armour", itemNum+1, 1);
					items.setValueAt(0, itemNum+1, 2);
					items.setValueAt(0, itemNum+1, 3);
					items.setValueAt(0, itemNum+1, 4);
					items.setValueAt(aItem.getDefense(), itemNum+1, 5);
					items.setValueAt(aItem.getBlockChance(), itemNum+1, 6);
					items.setValueAt(aItem.getAbsorbChance(), itemNum+1, 7);
				}
				else if(item instanceof Consumable)
				{
					items.setValueAt("entities.Consumable", itemNum+1, 1);
					items.setValueAt(0, itemNum+1, 2);
					items.setValueAt(0, itemNum+1, 3);
					items.setValueAt(0, itemNum+1, 4);
					items.setValueAt(0, itemNum+1, 5);
					items.setValueAt(0, itemNum+1, 6);
					items.setValueAt(0, itemNum+1, 7);
				}
				else if(item instanceof Relic)
				{
					items.setValueAt("entities.Relic", itemNum+1, 1);
					items.setValueAt(0, itemNum+1, 2);
					items.setValueAt(0, itemNum+1, 3);
					items.setValueAt(0, itemNum+1, 4);
					items.setValueAt(0, itemNum+1, 5);
					items.setValueAt(0, itemNum+1, 6);
					items.setValueAt(0, itemNum+1, 7);
				}
				usePanel.add(new UseButton(item, player, this, stats));
			
				itemNum++;
			}
		}
		else
		{
			items.setValueAt("None", 1, 0);
		}
	}
	
	public void refresh()
	{
		for(int i = 1; i < 11; i++)
			if(items.getValueAt(i, 0)!= null)
			{
				for(int j = 0; j < 8; j++)
				{	
					items.setValueAt(null, i, j);
				}
			}
		
		Weapon wep = player.getWeapon();
		if(wep!=null)
		{
			wName.setText(wep.getName());
			wMinDamage.setText(((Integer)(wep.getMinDamage())).toString());	
			wMaxDamage.setText(((Integer)(wep.getMaxDamage())).toString());
			wCritChance.setText(((Integer)(wep.getCritChance())).toString());
		}
		else
		{
			wName.setText("None");
			wMinDamage.setText("0");	
			wMaxDamage.setText("0");
			wCritChance.setText("0");
		}

		Armour arm = player.getArmour();
		if(arm!= null)
		{
			aName.setText(arm.getName());
			aDefense.setText(((Integer)(arm.getDefense())).toString());	
			aBlockChance.setText(((Integer)(arm.getBlockChance())).toString());	
			aAbsorbChance.setText(((Integer)(arm.getAbsorbChance())).toString());	
		}
		else
		{
			aName.setText("None");
			aDefense.setText("0");
			aBlockChance.setText("0");
			aAbsorbChance.setText("0");
		}
		
		Relic relic = player.getRelic();
		if(relic!=null)
		{
			rName.setText(relic.getName());
			rEffect.setText((relic.getEffect()).toString());	
			rAmount.setText(((Integer)(relic.getAmount())).toString());
		}
		else
		{
			rName.setText("None");
			rEffect.setText("None");	
			rAmount.setText("0");
		}
		
		// Items Panel
		Vector<Item> itemVec = player.getItems();
		usePanel.removeAll();
		usePanel.add(new JLabel("Use"));
		if(itemVec.size() > 0)
		{
			int itemNum = 0;
			for(Item item: itemVec)
			{
				items.setValueAt(item.getName(), itemNum+1, 0);
				if(item instanceof Weapon)
				{			
					Weapon wItem = (Weapon) item;
					items.setValueAt("entities.Weapon", itemNum+1, 1);
					items.setValueAt(wItem.getMinDamage(), itemNum+1, 2);
					items.setValueAt(wItem.getMaxDamage(), itemNum+1, 3);
					items.setValueAt(wItem.getCritChance(), itemNum+1, 4);
					items.setValueAt("-", itemNum+1, 5);
					items.setValueAt("-", itemNum+1, 6);
					items.setValueAt("-", itemNum+1, 7);
				}
				else if(item instanceof Armour)
				{
					Armour aItem= (Armour) item;
					items.setValueAt("entities.Armour", itemNum+1, 1);
					items.setValueAt("-", itemNum+1, 2);
					items.setValueAt("-", itemNum+1, 3);
					items.setValueAt("-", itemNum+1, 4);
					items.setValueAt(aItem.getDefense(), itemNum+1, 5);
					items.setValueAt(aItem.getBlockChance(), itemNum+1, 6);
					items.setValueAt(aItem.getAbsorbChance(), itemNum+1, 7);
				}
				else if(item instanceof Consumable)
				{
					items.setValueAt("entities.Consumable", itemNum+1, 1);
					items.setValueAt("-", itemNum+1, 2);
					items.setValueAt("-", itemNum+1, 3);
					items.setValueAt("-", itemNum+1, 4);
					items.setValueAt("-", itemNum+1, 5);
					items.setValueAt("-", itemNum+1, 6);
					items.setValueAt("-", itemNum+1, 7);
				}

				else if(item instanceof Relic)
				{
					items.setValueAt("entities.Relic", itemNum+1, 1);
					items.setValueAt("-", itemNum+1, 2); //mindamage
					items.setValueAt("-", itemNum+1, 3); //maxdamage
					items.setValueAt("-", itemNum+1, 4); // critchance
					items.setValueAt("-", itemNum+1, 5); // defense
					items.setValueAt("-", itemNum+1, 6); // blockchance
					items.setValueAt("-", itemNum+1, 7); // absorbchance
					
					RelicEffect effect = ((Relic)item).getEffect();
					switch(effect)
					{
					case Damage:
						items.setValueAt(((Relic)item).getAmount(), itemNum+1, 2);
						items.setValueAt(((Relic)item).getAmount(), itemNum+1, 3);
						break;
					case CritChance:
						items.setValueAt(((Relic)item).getAmount(), itemNum+1, 4);
						break;
					case Defense:
						items.setValueAt(((Relic)item).getAmount(), itemNum+1, 5);
						break;
					case BlockChance:
						items.setValueAt(((Relic)item).getAmount(), itemNum+1, 6);
						break;
					case AbsorbChance:
						items.setValueAt(((Relic)item).getAmount(), itemNum+1, 7);
						break;
					}
				}
//				items.setValueAt(new ui.UseButton(item, player), itemNum+1, 6);
				usePanel.add(new UseButton(item, player, this, stats));
				itemNum++;
			}
		}
		else
		{
	//		items.setValueAt("None", 1, 0);
		}
	}

}
