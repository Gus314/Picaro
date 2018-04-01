package ui;

import entities.*;
import entities.equipment.*;
import enums.RelicEffect;

import javax.swing.*;

import java.awt.Container;
import java.awt.GridLayout;
import java.util.Vector;

public class InventoryWindow extends JPanel
{
	private JLabel wMinDamage;
	private JLabel wMaxDamage;
	private JLabel wCritChance;
	private JLabel wIntelligence;
	private JLabel wName;
	private JLabel aDefense;
	private JLabel aName;
	private JLabel aBlockChance;
	private JLabel aAbsorbChance;
	private JLabel aMagicDefense;
	
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
		wepPanel.add(new JLabel("|Weapon:"));
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
			wepPanel.add(new JLabel("|Intelligence:"));
			wIntelligence = new JLabel(((Integer)(wep.getIntelligence())).toString());
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
			wepPanel.add(new JLabel("|Intelligence:"));
			wIntelligence = new JLabel("0");
			wepPanel.add(wIntelligence);
		}
		
		Armour arm = player.getArmour();
		JPanel armPanel = new JPanel();
		this.add(armPanel);
		armPanel.add(new JLabel("|Armour:"));
		if(arm!=null)
		{
			aName = new JLabel(arm.getName());
			armPanel.add(aName);
			armPanel.add(new JLabel("|Defense:"));	
			aDefense = new JLabel(((Integer)(arm.getDefense())).toString());
			armPanel.add(aDefense);

			armPanel.add(new JLabel("|Magic Defense:"));
			aMagicDefense = new JLabel(((Integer)(arm.getMagicDefense())).toString());
			armPanel.add(aMagicDefense);

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

			armPanel.add(new JLabel("|Magic Defense:"));
			aMagicDefense = new JLabel("0");
			armPanel.add(aMagicDefense);

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
		relicPanel.add(new JLabel("|Relic:"));
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
		items = new JTable(11, 10);
		/* table layout
		 * name, type, mindamage, maxdamage, critchance, intelligence, defense, magicdefense, blockchance, absorbchance, usebutton
		 */
		itemPanel.add(items);
		
		items.setValueAt("Name", 0, 0);
		items.setValueAt("Type", 0, 1);
		items.setValueAt("Min", 0, 2);
		items.setValueAt("Max", 0, 3);
		items.setValueAt("Crit Chance", 0, 4);
		items.setValueAt("Intelligence", 0, 5);
		items.setValueAt("Defense", 0, 6);
		items.setValueAt("Magic Defense", 0, 7);
		items.setValueAt("Block", 0, 8);
		items.setValueAt("Absorb", 0, 9);
		
		if(itemVec.size() > 0)
		{
			int itemNum = 0;
			for(Item item: itemVec)
			{
				items.setValueAt(item.getName(), itemNum+1, 0);
				if(item instanceof Weapon)
				{			
					Weapon wItem = (Weapon) item;
					items.setValueAt("Weapon", itemNum+1, 1);
					items.setValueAt(wItem.getMinDamage(), itemNum+1, 2);
					items.setValueAt(wItem.getMaxDamage(), itemNum+1, 3);
					items.setValueAt(wItem.getCritChance(), itemNum+1, 4);
					items.setValueAt(wItem.getIntelligence(), itemNum+1, 5);
					items.setValueAt(0, itemNum+1, 5);
					items.setValueAt(0, itemNum+1, 6);
					items.setValueAt(0, itemNum+1, 7);
					items.setValueAt(0, itemNum+1, 8);
					items.setValueAt(0, itemNum+1, 9);
				}
				else if(item instanceof Armour)
				{
					Armour aItem= (Armour) item;
					items.setValueAt("Armour", itemNum+1, 1);
					items.setValueAt(0, itemNum+1, 2);
					items.setValueAt(0, itemNum+1, 3);
					items.setValueAt(0, itemNum+1, 4);
					items.setValueAt(0, itemNum+1, 5);
					items.setValueAt(aItem.getDefense(), itemNum+1, 6);
					items.setValueAt(aItem.getMagicDefense(), itemNum+1, 7);
					items.setValueAt(aItem.getBlockChance(), itemNum+1, 8);
					items.setValueAt(aItem.getAbsorbChance(), itemNum+1, 9);
				}
				else if(item instanceof Consumable)
				{
					items.setValueAt("Consumable", itemNum+1, 1);
					items.setValueAt(0, itemNum+1, 2);
					items.setValueAt(0, itemNum+1, 3);
					items.setValueAt(0, itemNum+1, 4);
					items.setValueAt(0, itemNum+1, 5);
					items.setValueAt(0, itemNum+1, 6);
					items.setValueAt(0, itemNum+1, 7);
					items.setValueAt(0, itemNum+1, 8);
					items.setValueAt(0, itemNum+1, 9);
				}
				else if(item instanceof Relic)
				{
					items.setValueAt("Relic", itemNum+1, 1);
					items.setValueAt(0, itemNum+1, 2);
					items.setValueAt(0, itemNum+1, 3);
					items.setValueAt(0, itemNum+1, 4);
					items.setValueAt(0, itemNum+1, 5);
					items.setValueAt(0, itemNum+1, 6);
					items.setValueAt(0, itemNum+1, 7);
					items.setValueAt(0, itemNum+1, 8);
					items.setValueAt(0, itemNum+1, 9);
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
				for(int j = 0; j < 10; j++)
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
			wIntelligence.setText(((Integer)(wep.getIntelligence())).toString());
		}
		else
		{
			wName.setText("None");
			wMinDamage.setText("0");	
			wMaxDamage.setText("0");
			wCritChance.setText("0");
			wIntelligence.setText("0");
		}

		Armour arm = player.getArmour();
		if(arm!= null)
		{
			aName.setText(arm.getName());
			aDefense.setText(((Integer)(arm.getDefense())).toString());
			aMagicDefense.setText(((Integer)(arm.getMagicDefense())).toString());
			aBlockChance.setText(((Integer)(arm.getBlockChance())).toString());	
			aAbsorbChance.setText(((Integer)(arm.getAbsorbChance())).toString());	
		}
		else
		{
			aName.setText("None");
			aDefense.setText("0");
			aMagicDefense.setText("0");
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
					items.setValueAt("entities.equipment.Weapon", itemNum+1, 1);
					items.setValueAt(wItem.getMinDamage(), itemNum+1, 2);
					items.setValueAt(wItem.getMaxDamage(), itemNum+1, 3);
					items.setValueAt(wItem.getCritChance(), itemNum+1, 4);
					items.setValueAt(wItem.getIntelligence(), itemNum+1, 5);
					items.setValueAt("-", itemNum+1, 5);
					items.setValueAt("-", itemNum+1, 6);
					items.setValueAt("-", itemNum+1, 7);
					items.setValueAt("-", itemNum+1, 8);
					items.setValueAt("-", itemNum+1, 9);
				}
				else if(item instanceof Armour)
				{
					Armour aItem= (Armour) item;
					items.setValueAt("entities.equipment.Armour", itemNum+1, 1);
					items.setValueAt("-", itemNum+1, 2);
					items.setValueAt("-", itemNum+1, 3);
					items.setValueAt("-", itemNum+1, 4);
					items.setValueAt("-", itemNum+1, 5);
					items.setValueAt(aItem.getDefense(), itemNum+1, 6);
					items.setValueAt(aItem.getMagicDefense(), itemNum+1, 7);
					items.setValueAt(aItem.getBlockChance(), itemNum+1, 8);
					items.setValueAt(aItem.getAbsorbChance(), itemNum+1, 9);
				}
				else if(item instanceof Consumable)
				{
					items.setValueAt("entities.equipment.Consumable", itemNum+1, 1);
					items.setValueAt("-", itemNum+1, 2);
					items.setValueAt("-", itemNum+1, 3);
					items.setValueAt("-", itemNum+1, 4);
					items.setValueAt("-", itemNum+1, 5);
					items.setValueAt("-", itemNum+1, 6);
					items.setValueAt("-", itemNum+1, 7);
					items.setValueAt("-", itemNum+1, 8);
					items.setValueAt("-", itemNum+1, 9);
				}

				else if(item instanceof Relic)
				{
					items.setValueAt("entities.equipment.Relic", itemNum+1, 1);
					items.setValueAt("-", itemNum+1, 2); //mindamage
					items.setValueAt("-", itemNum+1, 3); //maxdamage
					items.setValueAt("-", itemNum+1, 4); // critchance
					items.setValueAt("-", itemNum+1, 5); // intelligence
					items.setValueAt("-", itemNum+1, 6); // defense
					items.setValueAt("-", itemNum+1, 7); // magicdefense
					items.setValueAt("-", itemNum+1, 8); // blockchance
					items.setValueAt("-", itemNum+1, 9); // absorbchance
					
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
						case Intelligence:
						items.setValueAt(((Relic)item).getAmount(), itemNum+1, 5);
						break;
					case Defense:
						items.setValueAt(((Relic)item).getAmount(), itemNum+1, 6);
						break;
					case MagicDefense:
						items.setValueAt(((Relic)item).getAmount(), itemNum+1, 7);
						break;
					case BlockChance:
						items.setValueAt(((Relic)item).getAmount(), itemNum+1, 8);
						break;
					case AbsorbChance:
						items.setValueAt(((Relic)item).getAmount(), itemNum+1, 9);
						break;
					}
				}
				usePanel.add(new UseButton(item, player, this, stats));
				itemNum++;
			}
		}
		else
		{

		}
	}

}
