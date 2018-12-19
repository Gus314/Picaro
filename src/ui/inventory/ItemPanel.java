package ui.inventory;

import control.Map;
import entities.creatures.Player;
import entities.equipment.*;
import ui.inventory.interfaces.IItemProvider;
import ui.mainwindow.Stats;
import ui.mainwindow.Status;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class ItemPanel extends JPanel implements IItemProvider
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
    private static final String[] weaponColumnNames = {"Name", "Min Damage", "Max Damage", "Crit Chance", "Intelligence"};
    private static final String[] armourColumnNames = {"Name", "Type", "Defense", "Magic Defense", "Block", "Absorb"};
    private static final String[] relicColumnNames = {"Name", "Status Effect"};
    private static final String[] consumableColumnNames = {"Name", "Type", "Amount"};
    private java.util.List<Item> items;
    private JRadioButton weaponsButton;
    private JRadioButton armourButton;
    private JRadioButton relicsButton;
    private JRadioButton consumablesButton;

    public ItemPanel(Player inPlayer, Stats inStats, Status inStatus, InventoryWindow inInventoryWindow, Map inMap)
    {
        player = inPlayer;
        stats = inStats;
        status = inStatus;
        inventoryWindow = inInventoryWindow;
        map = inMap;
        items = new ArrayList<Item>();

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

        GridBagLayout itemPanelLayout = new GridBagLayout();
        itemPanel.setLayout(itemPanelLayout);

        ButtonGroup filterGroup = new ButtonGroup();
        weaponsButton = new JRadioButton("weapons");
        weaponsButton.addActionListener((e) -> {refresh(player.getItems());});
        weaponsButton.setSelected(true);
        armourButton = new JRadioButton("armour");
        armourButton.addActionListener((e) -> {refresh(player.getItems());});
        relicsButton = new JRadioButton("relics");
        relicsButton.addActionListener((e) -> {refresh(player.getItems());});
        consumablesButton = new JRadioButton("consumables");
        consumablesButton.addActionListener((e) -> {refresh(player.getItems());});
        filterGroup.add(weaponsButton);
        filterGroup.add(armourButton);
        filterGroup.add(relicsButton);
        filterGroup.add(consumablesButton);
        JPanel filterPanel = new JPanel();
        filterPanel.add(weaponsButton);
        filterPanel.add(armourButton);
        filterPanel.add(relicsButton);
        filterPanel.add(consumablesButton);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
        itemPanel.add(filterPanel, constraints);

        itemsTable = new JTable();
        DefaultTableModel model = new DefaultTableModel(weaponColumnNames, 0);
        itemsTable.setModel(model);
        itemsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.weightx = 1;
        constraints.weighty = 11;
        itemPanel.add(new JScrollPane(itemsTable), constraints);

        setLayout(new GridLayout());

        JButton useButton = new UseButton(this, player, inventoryWindow, stats, status);
        usePanel.add(useButton);
        dropPanel.add(new DropButton(this, player, inventoryWindow, map));
    }

    public Optional<Item> getItem()
    {
        int selection = itemsTable.getSelectedRow();

        if(selection >= 0)
        {
            return Optional.of(items.get(selection));
        }
        else
        {
            return Optional.empty();
        }
    }

    public void refresh(Collection<Item> newItems)
    {
        items.clear();

        for(Item item: newItems)
        {
            if(weaponsButton.isSelected() && item instanceof  Weapon)
            {
                items.add(item);
            }
            if(armourButton.isSelected() && item instanceof  Armour)
            {
                items.add(item);
            }
            if(consumablesButton.isSelected() && item instanceof  Consumable)
            {
                items.add(item);
            }
            if(relicsButton.isSelected() && item instanceof  Relic)
            {
                items.add(item);
            }
        }

        String[] columnNames = weaponsButton.isSelected() ? weaponColumnNames :
                armourButton.isSelected() ? armourColumnNames :
                        consumablesButton.isSelected() ? consumableColumnNames :
                                relicColumnNames; // Assume relicsButton selected.

        DefaultTableModel model = new DefaultTableModel(columnNames, items.size());
        itemsTable.setModel(model);

        if(items.size() > 0)
        {
            int itemNum = 0;
            for(Item item: items)
            {
                itemsTable.setValueAt(item.getName(), itemNum, 0);
                if(item instanceof Weapon)
                {
                    Weapon wItem = (Weapon) item;
                    itemsTable.setValueAt(wItem.getMinDamage(), itemNum, 1);
                    itemsTable.setValueAt(wItem.getMaxDamage(), itemNum, 2);
                    itemsTable.setValueAt(wItem.getCritChance(), itemNum, 3);
                    itemsTable.setValueAt(wItem.getIntelligence(), itemNum, 4);
                }
                else if(item instanceof Armour)
                {
                    Armour aItem= (Armour) item;
                    itemsTable.setValueAt(aItem.getArmourType().toString(), itemNum, 1);
                    itemsTable.setValueAt(aItem.getDefense(), itemNum, 2);
                    itemsTable.setValueAt(aItem.getMagicDefense(), itemNum, 3);
                    itemsTable.setValueAt(aItem.getBlockChance(), itemNum, 4);
                    itemsTable.setValueAt(aItem.getAbsorbChance(), itemNum, 5);
                }
                else if(item instanceof Consumable)
                {
                    Consumable cItem = (Consumable) item;
                    itemsTable.setValueAt(cItem.getType().toString(), itemNum, 1);
                    itemsTable.setValueAt(cItem.getAmount(), itemNum, 2);
                }
                else if(item instanceof Relic)
                {
                    Relic rItem = (Relic) item;
                    itemsTable.setValueAt(rItem.getStatusEffect().getName(), itemNum, 1);
                }
                itemNum++;
            }
        }
    }
}

