package ui.inventory;

import control.Map;
import entities.Player;
import entities.equipment.Item;
import entities.equipment.Relic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveButton extends JButton
{
    private Relic relic;
    private Player player;
    private InventoryWindow invWindow;

    public RemoveButton(Relic inRelic, Player inPlayer, InventoryWindow inInvWindow)
    {
        player = inPlayer;
        invWindow = inInvWindow;
        relic = inRelic;
        this.setText("Remove " + relic.getName());
        this.addActionListener(new RemoveButton.RemoveListener());
    }

    public class RemoveListener implements ActionListener
    {
        public RemoveListener(){}

        public void actionPerformed(ActionEvent ae)
        {
            player.removeRelic(relic);
            player.getItems().add(relic);
            invWindow.refresh();
        }
    }
}
