package ui.inventory;

import entities.equipment.Weapon;

import javax.swing.*;

public class WeaponPanel extends JPanel
{
    private JLabel name;
    private JLabel minDamage;
    private JLabel maxDamage;
    private JLabel critChance;
    private JLabel intelligence;

    public WeaponPanel(Weapon weapon)
    {
        add(new JLabel("|Weapon:"));
        name = new JLabel();
        add(name);
        add(new JLabel("|Min Damage:"));
        minDamage = new JLabel();
        add(minDamage);
        add(new JLabel("|Max Damage:"));
        maxDamage = new JLabel();
        add(maxDamage);
        add(new JLabel("|Crit Chance:"));
        critChance = new JLabel();
        add(critChance);
        add(new JLabel("|Intelligence:"));
        intelligence = new JLabel();
        add(intelligence);
    }

    public void refresh(Weapon weapon)
    {
        if(weapon != null)
        {
            name.setText(weapon.getName());
            minDamage.setText(((Integer)(weapon.getMinDamage())).toString());
            maxDamage = new JLabel(((Integer)(weapon.getMaxDamage())).toString());
            critChance = new JLabel(((Integer)(weapon.getCritChance())).toString());
            intelligence = new JLabel(((Integer)(weapon.getIntelligence())).toString());
        }
        else
        {
            name.setText("None");
            minDamage.setText("0");
            maxDamage.setText("0");
            critChance.setText("0");
            intelligence.setText("0");
        }
    }
}
