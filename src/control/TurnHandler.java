package control;

import entities.creatures.Monster;
import entities.creatures.Player;
import entities.equipment.Item;
import enums.Direction;
import entities.*;
import ui.inventory.InventoryWindow;
import ui.mainwindow.MapDisplay;
import ui.mainwindow.Messages;

import java.util.List;

public class TurnHandler
{
    private Player player;
    private Map map;
    private MapDisplay mapDisplay;
    private Messages messages;
    private InventoryWindow invWind;
    private DungeonManager dm;
    private Options options;

    public TurnHandler(Player inPlayer, MapDisplay inMapDisplay, Messages inMessages, InventoryWindow inInvWind, DungeonManager inDm, Options inOptions)
    {
        player = inPlayer;
        mapDisplay = inMapDisplay;
        map = mapDisplay.getMap();
        messages = inMessages;
        invWind = inInvWind;
        dm = inDm;
        options = inOptions;
    }

    public void update()
    {
        mapDisplay.refresh();
        invWind.refresh();
    }

    public void nextLevel()
    {
        dm.nextLevel();
        map = dm.getMap();
        mapDisplay = dm.getMapDisplay();
        messages.addMessage("You descend to level " + dm.getLevel() + "!");
    }

    public void previousLevel()
    {
        dm.previousLevel();
        map = dm.getMap();
        mapDisplay = dm.getMapDisplay();
        messages.addMessage("You ascend to level " + dm.getLevel() + "!");
    }

    public void pickUp(Item item)
    {
        player.addItem(item);
        messages.addMessage("Picked up " + item.getName());
        map.removeEntity(item);
    }

    public void changeLevel()
    {
        List<Entity> here = map.atPosition(player.getRow(), player.getColumn());

        for(Entity ent: here)
        {
            if(ent instanceof DownStairs)
            {
                nextLevel();
                return;
            }
            else if(ent instanceof UpStairs)
            {
                previousLevel();
                return;
            }
        }
    }

    public void pickupItems()
    {
        List<Entity> here = map.atPosition(player.getRow(), player.getColumn());

        List<Item> items = Entity.getItems(here);

        for(Item item: items)
        {
            pickUp(item);
        }
    }

    public void listItems()
    {
        List<Entity> here = map.atPosition(player.getRow(), player.getColumn());
        List<Item> items = Entity.getItems(here);

        for(Item item: items)
        {
            messages.addMessage("There is a " + item.getName() + " on the floor.");
        }
    }

    public void movePlayer(Direction direction)
    {
        int row = player.getRow();
        int column = player.getColumn();

        List<Entity> here = map.atPosition(Coordinate.adjustRow(direction, row), Coordinate.adjustColumn(direction, column));
        if(Entity.passable(here))
        {
            if(!player.isStunned())
            {
                // Stunned players cannot move but will lose their turn trying.
                player.move(direction, 1);
            }
        }
        else if(Entity.containsMonster(here))
        {
            Monster monster = Entity.getMonster(here);
            boolean killed = player.attack(monster);
            if(killed)
            {
                map.removeEntity(monster);
            }
        }

        if(options.getAutoPickup())
        {
            pickupItems();
        }
        else
        {
            listItems();
        }

        map.takeTurns();
        update();
    }
}
