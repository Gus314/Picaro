package ui.inventory;

import entities.equipment.Armour;
import enums.ArmourType;

import javax.swing.*;

public class ArmourPanel extends JPanel
{
    private JLabel name;
    private JLabel defense;
    private JLabel magicDefense;
    private JLabel blockChance;
    private JLabel absorbChance;

    public ArmourPanel(ArmourType armourType)
    {
        JPanel armPanel = new JPanel();
        this.add(armPanel);
        armPanel.add(new JLabel("|" + armourType + ":"));
        name = new JLabel();
        armPanel.add(name);
        armPanel.add(new JLabel("|Defense:"));
        defense = new JLabel();
        armPanel.add(defense);

        armPanel.add(new JLabel("|Magic Defense:"));
        magicDefense = new JLabel();
        armPanel.add(magicDefense);

        armPanel.add(new JLabel("|Block Chance:"));
        blockChance = new JLabel();
        armPanel.add(blockChance);

        armPanel.add(new JLabel("|Absorb Chance:"));
        absorbChance = new JLabel();
        armPanel.add(absorbChance);
    }

    public void refresh(Armour arm)
    {
        if(arm!= null)
        {
            name.setText(arm.getName());
            defense.setText(((Integer)(arm.getDefense())).toString());
            magicDefense.setText(((Integer)(arm.getMagicDefense())).toString());
            blockChance.setText(((Integer)(arm.getBlockChance())).toString());
            absorbChance.setText(((Integer)(arm.getAbsorbChance())).toString());
        }
        else
        {
            name.setText("None");
            defense.setText("0");
            magicDefense.setText("0");
            blockChance.setText("0");
            absorbChance.setText("0");
        }
    }
}
