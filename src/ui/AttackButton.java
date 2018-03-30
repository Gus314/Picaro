package ui;

import entities.*;
import enums.MapDisplayMode;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class AttackButton extends JButton
{
    private MapDisplay mapDisplay;

    public AttackButton(MapDisplay inMapDisplay)
    {
        this.setText("Attack");
        this.addActionListener(new AttackListener(this));
        mapDisplay = inMapDisplay;
    }


    public class AttackListener implements ActionListener
    {
        private AttackButton attackButton;

        public AttackListener(AttackButton inAttackButton)
        {
            attackButton = inAttackButton;
        }

        public void actionPerformed(ActionEvent ae)
        {
            mapDisplay.changeMode(MapDisplayMode.ATTACK);
        }
    }

}
