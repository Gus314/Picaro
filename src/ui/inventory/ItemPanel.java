package ui.inventory;

import control.Map;
import entities.Player;
import entities.equipment.*;
import ui.mainwindow.Stats;
import ui.mainwindow.Status;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Collection;
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
    private static final String[] columnNames = {"Name", "Type", "Min", "Max", "Crit Chance", "Intelligence", "Defense", "Magic Defense", "Block", "Absorb"};

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

        invPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        add(invPanel);

        itemPanel = new JPanel();

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 10;
        constraints.weighty = 1;
        invPanel.add(itemPanel, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        invPanel.add(usePanel, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        invPanel.add(dropPanel, constraints);

        usePanel.add(new JLabel("Use"));
        dropPanel.add(new JLabel("Drop"));
        itemPanel.add(new JLabel("Items"));
        itemsTable = new JTable();
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        itemsTable.setModel(model);
        itemPanel.add(new JScrollPane(itemsTable));
    }

    public void refresh(Collection<Item> items)
    {
        DefaultTableModel model = new DefaultTableModel(columnNames, items.size());
        itemsTable.setModel(model);

        usePanel.removeAll();
        usePanel.add(new JLabel("Use"));

        dropPanel.removeAll();
        dropPanel.add(new JLabel("Drop"));

        if(items.size() > 0)
        {
            int itemNum = 0;
            for(Item item: items)
            {
                itemsTable.setValueAt(item.getName(), itemNum, 0);
                if(item instanceof Weapon)
                {
                    Weapon wItem = (Weapon) item;
                    itemsTable.setValueAt("Weapon", itemNum, 1);
                    itemsTable.setValueAt(wItem.getMinDamage(), itemNum, 2);
                    itemsTable.setValueAt(wItem.getMaxDamage(), itemNum, 3);
                    itemsTable.setValueAt(wItem.getCritChance(), itemNum, 4);
                    itemsTable.setValueAt(wItem.getIntelligence(), itemNum, 5);
                    itemsTable.setValueAt(0, itemNum, 5);
                    itemsTable.setValueAt(0, itemNum, 6);
                    itemsTable.setValueAt(0, itemNum, 7);
                    itemsTable.setValueAt(0, itemNum, 8);
                    itemsTable.setValueAt(0, itemNum, 9);
                }
                else if(item instanceof Armour)
                {
                    Armour aItem= (Armour) item;
                    itemsTable.setValueAt("Armour", itemNum, 1);
                    itemsTable.setValueAt(0, itemNum, 2);
                    itemsTable.setValueAt(0, itemNum, 3);
                    itemsTable.setValueAt(0, itemNum, 4);
                    itemsTable.setValueAt(0, itemNum, 5);
                    itemsTable.setValueAt(aItem.getDefense(), itemNum, 6);
                    itemsTable.setValueAt(aItem.getMagicDefense(), itemNum, 7);
                    itemsTable.setValueAt(aItem.getBlockChance(), itemNum, 8);
                    itemsTable.setValueAt(aItem.getAbsorbChance(), itemNum, 9);
                }
                else if(item instanceof Consumable)
                {
                    itemsTable.setValueAt("Consumable", itemNum, 1);
                    itemsTable.setValueAt(0, itemNum, 2);
                    itemsTable.setValueAt(0, itemNum, 3);
                    itemsTable.setValueAt(0, itemNum, 4);
                    itemsTable.setValueAt(0, itemNum, 5);
                    itemsTable.setValueAt(0, itemNum, 6);
                    itemsTable.setValueAt(0, itemNum, 7);
                    itemsTable.setValueAt(0, itemNum, 8);
                    itemsTable.setValueAt(0, itemNum, 9);
                }
                else if(item instanceof Relic)
                {
                    itemsTable.setValueAt("Relic", itemNum, 1);
                    itemsTable.setValueAt(0, itemNum, 2);
                    itemsTable.setValueAt(0, itemNum, 3);
                    itemsTable.setValueAt(0, itemNum, 4);
                    itemsTable.setValueAt(0, itemNum, 5);
                    itemsTable.setValueAt(0, itemNum, 6);
                    itemsTable.setValueAt(0, itemNum, 7);
                    itemsTable.setValueAt(0, itemNum, 8);
                    itemsTable.setValueAt(0, itemNum, 9);
                }
                JButton useButton = new UseButton(item, player, inventoryWindow, stats, status);
                if(item instanceof Relic && (!player.canAddRelic()))
                {
                    useButton.setEnabled(false);
                }
                usePanel.add(useButton);
                dropPanel.add(new DropButton(item, player, inventoryWindow, map));

                itemNum++;
            }
        }
        usePanel.repaint();
        dropPanel.repaint();
    }
}

