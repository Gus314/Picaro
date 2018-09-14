package ui.inventory;

import control.Map;
import entities.Player;
import entities.equipment.*;
import ui.mainwindow.Stats;
import ui.mainwindow.Status;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class ItemPanel extends JPanel
{
    private JPanel itemPanel;
    private JPanel usePanel;
    private JPanel dropPanel;
    private JPanel invPanel;
    private JTable itemsTable;
    private Player player;
    private Stats stats;
    private Status status;
    private InventoryWindow inventoryWindow;
    private Map map;
    private static final int itemsPerPage = 10;
    private static final int numColumns = 10;

    public JPanel getUsePanel()
    {
        return usePanel;
    }

    public ItemPanel(Player inPlayer, Stats inStats, Status inStatus, InventoryWindow inInventoryWindow, Map inMap)
    {
        player = inPlayer;
        stats = inStats;
        status = inStatus;
        inventoryWindow = inInventoryWindow;
        map = inMap;

        usePanel = new JPanel();
        dropPanel = new JPanel();
        invPanel = new JPanel();
        usePanel.setLayout(new GridLayout(itemsPerPage, 1));
        dropPanel.setLayout(new GridLayout(itemsPerPage, 1));
        invPanel.setLayout(new GridLayout(1, 3));
        add(invPanel);

        itemPanel = new JPanel();
        invPanel.add(itemPanel);
        invPanel.add(usePanel);
        invPanel.add(dropPanel);
        usePanel.add(new JLabel("Use"));
        dropPanel.add(new JLabel("Drop"));
        itemPanel.add(new JLabel("Items:"));
        itemsTable = new JTable(itemsPerPage+1, numColumns);
        itemPanel.add(itemsTable);

        itemsTable.setValueAt("Name", 0, 0);
        itemsTable.setValueAt("Type", 0, 1);
        itemsTable.setValueAt("Min", 0, 2);
        itemsTable.setValueAt("Max", 0, 3);
        itemsTable.setValueAt("Crit Chance", 0, 4);
        itemsTable.setValueAt("Intelligence", 0, 5);
        itemsTable.setValueAt("Defense", 0, 6);
        itemsTable.setValueAt("Magic Defense", 0, 7);
        itemsTable.setValueAt("Block", 0, 8);
        itemsTable.setValueAt("Absorb", 0, 9);
    }

    public void refresh(Vector<Item> items)
    {
        // Reset
        for(int i = 1; i < itemsPerPage; i++)
        {
            for(int j = 0; j < numColumns; j++)
            {
                itemsTable.setValueAt(0, i, j);
            }
        }
        usePanel.removeAll();
        usePanel.add(new JLabel("Use"));

        dropPanel.removeAll();
        dropPanel.add(new JLabel("Drop"));

        if(items.size() > 0)
        {
            int itemNum = 0;
            for(Item item: items)
            {
                itemsTable.setValueAt(item.getName(), itemNum+1, 0);
                if(item instanceof Weapon)
                {
                    Weapon wItem = (Weapon) item;
                    itemsTable.setValueAt("Weapon", itemNum+1, 1);
                    itemsTable.setValueAt(wItem.getMinDamage(), itemNum+1, 2);
                    itemsTable.setValueAt(wItem.getMaxDamage(), itemNum+1, 3);
                    itemsTable.setValueAt(wItem.getCritChance(), itemNum+1, 4);
                    itemsTable.setValueAt(wItem.getIntelligence(), itemNum+1, 5);
                    itemsTable.setValueAt(0, itemNum+1, 5);
                    itemsTable.setValueAt(0, itemNum+1, 6);
                    itemsTable.setValueAt(0, itemNum+1, 7);
                    itemsTable.setValueAt(0, itemNum+1, 8);
                    itemsTable.setValueAt(0, itemNum+1, 9);
                }
                else if(item instanceof Armour)
                {
                    Armour aItem= (Armour) item;
                    itemsTable.setValueAt("Armour", itemNum+1, 1);
                    itemsTable.setValueAt(0, itemNum+1, 2);
                    itemsTable.setValueAt(0, itemNum+1, 3);
                    itemsTable.setValueAt(0, itemNum+1, 4);
                    itemsTable.setValueAt(0, itemNum+1, 5);
                    itemsTable.setValueAt(aItem.getDefense(), itemNum+1, 6);
                    itemsTable.setValueAt(aItem.getMagicDefense(), itemNum+1, 7);
                    itemsTable.setValueAt(aItem.getBlockChance(), itemNum+1, 8);
                    itemsTable.setValueAt(aItem.getAbsorbChance(), itemNum+1, 9);
                }
                else if(item instanceof Consumable)
                {
                    itemsTable.setValueAt("Consumable", itemNum+1, 1);
                    itemsTable.setValueAt(0, itemNum+1, 2);
                    itemsTable.setValueAt(0, itemNum+1, 3);
                    itemsTable.setValueAt(0, itemNum+1, 4);
                    itemsTable.setValueAt(0, itemNum+1, 5);
                    itemsTable.setValueAt(0, itemNum+1, 6);
                    itemsTable.setValueAt(0, itemNum+1, 7);
                    itemsTable.setValueAt(0, itemNum+1, 8);
                    itemsTable.setValueAt(0, itemNum+1, 9);
                }
                else if(item instanceof Relic)
                {
                    itemsTable.setValueAt("Relic", itemNum+1, 1);
                    itemsTable.setValueAt(0, itemNum+1, 2);
                    itemsTable.setValueAt(0, itemNum+1, 3);
                    itemsTable.setValueAt(0, itemNum+1, 4);
                    itemsTable.setValueAt(0, itemNum+1, 5);
                    itemsTable.setValueAt(0, itemNum+1, 6);
                    itemsTable.setValueAt(0, itemNum+1, 7);
                    itemsTable.setValueAt(0, itemNum+1, 8);
                    itemsTable.setValueAt(0, itemNum+1, 9);
                }
                usePanel.add(new UseButton(item, player, inventoryWindow, stats, status));
                dropPanel.add(new DropButton(item, player, inventoryWindow, map));

                itemNum++;
            }
        }
        usePanel.repaint();
        dropPanel.repaint();
    }
}

