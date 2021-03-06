package ui.inventory;

import entities.creatures.Player;
import entities.equipment.Relic;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Collection;

public class RelicPanel extends JPanel
{
    private JTable relicTable;
    private static final String[] columnNames = {"Relic Name", "Status Effect"};
    private JPanel destroyPanel;
    private InventoryWindow inventoryWindow;
    private Player player;

   public RelicPanel(InventoryWindow inInventoryWindow, Player inPlayer)
   {
       inventoryWindow = inInventoryWindow;
       player = inPlayer;

       relicTable = new JTable();
       DefaultTableModel model = new DefaultTableModel(columnNames, Player.getMaxRelics());
       relicTable.setModel(model);

       add(new JScrollPane(relicTable));
       destroyPanel = new JPanel();
       destroyPanel.setLayout(new GridLayout(3,1));
       add(destroyPanel);

       setLayout(new GridLayout());
       refresh(player.getRelics());
   }

   public void refresh(Collection<Relic> relics)
   {
       destroyPanel.removeAll();

       for(int i = 0; i < Player.getMaxRelics(); i++)
       {
           relicTable.setValueAt("None", i, 0);
           relicTable.setValueAt(" ", i, 1);
       }

       int i = 0;
       for(Relic relic: relics)
       {
           relicTable.setValueAt(relic.getName(), i, 0);
           relicTable.setValueAt(relic.getStatusEffect().getName(), i, 1);

           destroyPanel.add(new DestroyButton(relic, player, inventoryWindow));
           i++;
       }
       destroyPanel.repaint();
   }
}
