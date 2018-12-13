package ui.inventory;

import control.Map;
import entities.creatures.Player;
import entities.equipment.Item;
import ui.inventory.interfaces.IItemProvider;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class DropButton extends JButton
{
    private IItemProvider itemProvider;
    private Player player;
    private InventoryWindow invWindow;
    private Map map;

    public DropButton(IItemProvider inItemProvider, Player inPlayer, InventoryWindow inInvWindow, Map inMap)
    {
        itemProvider = inItemProvider;
        player = inPlayer;
        invWindow = inInvWindow;
        map = inMap;
        this.setText("Drop");
        this.addActionListener(new DropButton.DropListener());
    }

    public class DropListener implements ActionListener
    {
        public DropListener(){}

        public void actionPerformed(ActionEvent ae)
        {
            Optional<Item> itemOptional = itemProvider.getItem();
            if(itemOptional.isPresent())
            {
                Item item = itemOptional.get();
                player.getItems().remove(item);
                item.setRow(player.getRow());
                item.setColumn(player.getColumn());
                map.addEntry(item);
                invWindow.refresh();
            }
        }
    }
}
