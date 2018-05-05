package ui.actions;

import control.TurnHandler;
import enums.MapDisplayMode;
import ui.MapDisplay;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PickupButton extends JButton
{
    private TurnHandler turnHandler;

    public PickupButton(TurnHandler inTurnHandler)
    {
        this.setText("Pickup");
        this.addActionListener(new PickupListener());
        turnHandler = inTurnHandler;
    }

    public class PickupListener implements ActionListener
    {
        public PickupListener(){}

        public void actionPerformed(ActionEvent ae)
        {
            turnHandler.pickupItems();
            getParent().getParent().getParent().requestFocus();
        }
    }
}
