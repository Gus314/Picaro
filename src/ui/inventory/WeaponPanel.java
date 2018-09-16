package ui.inventory;

import entities.equipment.Weapon;
import enums.ArmourType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class WeaponPanel extends JPanel
{
    private JTable weaponTable;
    private static final String[] columnNames = {"Weapon Name", "Min Damage", "Max Damage", "Crit Chance", "Intelligence"};

    public WeaponPanel(Weapon weapon)
    {
        weaponTable = new JTable();
        DefaultTableModel model = new DefaultTableModel(columnNames, 1);
        weaponTable.setModel(model);
        add(new JScrollPane(weaponTable));

        refresh(weapon);
    }

    public void refresh(Weapon weapon)
    {
        if(weapon != null)
        {
            final String minDamage = ((Integer)(weapon.getMinDamage())).toString();
            final String maxDamage = ((Integer)(weapon.getMaxDamage())).toString();
            final String critChance = ((Integer)(weapon.getCritChance())).toString();
            final String intelligence = ((Integer)(weapon.getIntelligence())).toString();

            weaponTable.setValueAt(weapon.getName(), 0, 0);
            weaponTable.setValueAt(minDamage, 0, 1);
            weaponTable.setValueAt(maxDamage, 0, 2);
            weaponTable.setValueAt(critChance, 0, 3);
            weaponTable.setValueAt(intelligence, 0, 4);
        }
        else
        {
            weaponTable.setValueAt("None", 0, 0);
            weaponTable.setValueAt("", 0, 1);
            weaponTable.setValueAt("", 0, 2);
            weaponTable.setValueAt("", 0, 3);
            weaponTable.setValueAt("", 0, 4);
        }
    }
}
