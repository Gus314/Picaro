package ui.inventory;

import control.Map;
import entities.creatures.Player;
import entities.equipment.Item;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DropButton extends JButton
{
    private Item item;
    private Player player;
    private InventoryWindow invWindow;
    private Map map;

    public DropButton(Item inItem, Player inPlayer, InventoryWindow inInvWindow, Map inMap)
    {
        item = inItem;
        player = inPlayer;
        invWindow = inInvWindow;
        map = inMap;
        this.setText("Drop " + item.getName());
        this.addActionListener(new DropButton.DropListener());
    }

    public class DropListener implements ActionListener
    {
        public DropListener(){}

        public void actionPerformed(ActionEvent ae)
        {
            player.getItems().remove(item);
            item.setRow(player.getRow());
            item.setColumn(player.getColumn());
            map.addEntry(item);
            invWindow.refresh();
        }
    }
}
