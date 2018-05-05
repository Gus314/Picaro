package ui.actions;

import enums.MapDisplayMode;
import ui.mainwindow.MapDisplay;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
