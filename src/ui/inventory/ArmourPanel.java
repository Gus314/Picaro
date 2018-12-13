package ui.inventory;

import com.sun.javafx.collections.MappingChange;
import entities.equipment.Armour;
import enums.ArmourType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ArmourPanel extends JPanel
{
    private JTable armourTable;
    private static final String[] columnNames = {"Armour Type", "Name", "Defense", "Magic Defense", "Block Chance", "Absorb Chance"};

    public ArmourPanel(java.util.Map<ArmourType, Armour> armours)
    {
        armourTable = new JTable();
        DefaultTableModel model = new DefaultTableModel(columnNames, ArmourType.values().length);
        armourTable.setModel(model);

        add(new JScrollPane(armourTable));
        setLayout(new GridLayout());
        refresh(armours);
    }

    private void displayArmour(Armour armour)
    {
        int row = armour.getArmourType().ordinal();
        armourTable.setValueAt(armour.getArmourType(), row, 0);
        armourTable.setValueAt(armour.getName(), row, 1);
        armourTable.setValueAt(armour.getDefense(), row, 2);
        armourTable.setValueAt(armour.getMagicDefense(), row, 3);
        armourTable.setValueAt(armour.getBlockChance(), row, 4);
        armourTable.setValueAt(armour.getBlockChance(), row, 5);
    }

    private void displayNone(ArmourType armourType)
    {
        int row = armourType.ordinal();
        armourTable.setValueAt(armourType, row, 0);
        armourTable.setValueAt("None", row,1);
        armourTable.setValueAt("", row, 1);
        armourTable.setValueAt("", row, 2);
        armourTable.setValueAt("", row, 3);
        armourTable.setValueAt("", row, 4);
        armourTable.setValueAt("", row, 5);
    }

    public void refresh(java.util.Map<ArmourType, Armour> armours)
    {
        DefaultTableModel model = new DefaultTableModel(columnNames, ArmourType.values().length);
        armourTable.setModel(model);

        for(ArmourType armourType: ArmourType.values())
        {
            if(armours.containsKey(armourType))
            {
                displayArmour(armours.get(armourType));
            }
            else
            {
                displayNone(armourType);
            }
        }
    }
}
