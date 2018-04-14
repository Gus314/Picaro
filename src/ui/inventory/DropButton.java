package ui.inventory;

import entities.Player;
import entities.equipment.Item;
import ui.Stats;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DropButton extends JButton
{
    private Item item;
    private Player player;
    private InventoryWindow invWindow;

    public DropButton(Item inItem, Player inPlayer, InventoryWindow inInvWindow)
    {
        item = inItem;
        player = inPlayer;
        invWindow = inInvWindow;
        this.setText("Drop " + item.getName());
        this.addActionListener(new DropButton.DropListener());
    }

    public class DropListener implements ActionListener
    {
        public DropListener(){}

        public void actionPerformed(ActionEvent ae)
        {
            player.getItems().remove(item);
            invWindow.refresh();
        }
    }
}
