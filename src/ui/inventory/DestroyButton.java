package ui.inventory;

import entities.creatures.Player;
import entities.equipment.Relic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DestroyButton extends JButton
{
    private Relic relic;
    private Player player;
    private InventoryWindow invWindow;

    public DestroyButton(Relic inRelic, Player inPlayer, InventoryWindow inInvWindow)
    {
        player = inPlayer;
        invWindow = inInvWindow;
        relic = inRelic;
        this.setText("Destroy " + relic.getName());
        this.addActionListener(new DestroyButton.DestroyListener());
    }

    public class DestroyListener implements ActionListener
    {
        public DestroyListener(){}

        public void actionPerformed(ActionEvent ae)
        {
            player.removeRelic(relic);
            invWindow.refresh();
        }
    }
}
