package control;

import enums.Direction;
import entities.*;
import ui.InventoryWindow;
import ui.MapDisplay;
import ui.Messages;

import java.util.Random;

public class TurnHandler
{
    private Player player;
    private Map map;
    private MapDisplay mapDisplay;
    private Messages messages;
    private InventoryWindow invWind;
    private static final Random generator = new Random();
    private DungeonManager dm;

    public TurnHandler(Player inPlayer, MapDisplay inMapDisplay, Messages inMessages, InventoryWindow inInvWind, DungeonManager inDm)
    {
        player = inPlayer;
        mapDisplay = inMapDisplay;
        map = mapDisplay.getMap();
        messages = inMessages;
        invWind = inInvWind;
        dm = inDm;
    }

    private void update()
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

    public void pickUp(Item item)
    {
        player.addItem(item);
        messages.addMessage("Picked up " + item.getName());
        map.removeEntity(item);
    }


    private void attack(Creature creature)
    {
        int creatureLife = creature.getLife();
        int creatureDefense = creature.getDefense();
        int creatureExp = creature.getExp();
        int playerExp = player.getExp();
        int playerMinDamage = player.getMinDamage();
        int playerMaxDamage = player.getMaxDamage();
        int playerCritChance = player.getCritChance();
        Boolean criticalHit = false;

        int damage = playerMinDamage + generator.nextInt(playerMaxDamage - playerMinDamage) - creatureDefense;
        if(generator.nextInt(101 - playerCritChance) - 1 == 0)
        {
            damage = damage * 2;
            criticalHit = true;
        }
        String creatureName = creature.getName();

        if(damage <= 0)
            messages.addMessage(creatureName + " defense nullified your attack!");
        else
        {
            creatureLife -= damage;
            creature.setLife(creatureLife);
            if(!criticalHit)
                messages.addMessage("Critical hit!");
            messages.addMessage(creatureName + " took " + damage + " damage!");
            if(creatureLife <= 0)
            {
                map.removeEntity(creature);
                messages.addMessage(creatureName + " died, giving " + creatureExp + " exp!");
                int newExp = player.getExp()+creatureExp;
                if(newExp >= 100)
                {
                    newExp = newExp - 100;
                    player.setMaxLife(player.getMaxLife()+10);
                    player.setLife(player.getMaxLife());
                    player.setMinDamage(player.getMinDamage()+2);
                    player.setMaxDamage(player.getMaxDamage()+2);
                    player.setDefense(player.getDefense()+2);
                    player.setLevel(player.getLevel()+1);
                    messages.addMessage("Level up!");
                }
                player.setExp(newExp);
            }
        }
    }

    private int adjustRow(Direction direction, int row)
    {
        switch(direction)
        {
            case UPLEFT:
                return row-1;
            case UPRIGHT:
                return row-1;
            case DOWNLEFT:
                return row+1;
            case DOWNRIGHT:
                return row+1;
            case UP:
                return row-1;
            case LEFT:
                return row;
            case RIGHT:
                return row;
            case DOWN:
                return row+1;
        }

        return row;
        // TODO: Add check.
    }

    private int adjustColumn(Direction direction, int column)
    {
        switch(direction)
        {
            case UPLEFT:
                return column-1;
            case UPRIGHT:
                return column+1;
            case DOWNLEFT:
                return column-1;
            case DOWNRIGHT:
                return column+1;
            case UP:
                return column;
            case LEFT:
                return column-1;
            case RIGHT:
                return column+1;
            case DOWN:
                return column;
        }

        // TODO: Add check.
        return column;
    }

    public void movePlayer(Direction direction)
    {
        int row = player.getRow();
        int column = player.getColumn();

        Entity ent = map.atPosition(adjustRow(direction, row), adjustColumn(direction, column));
        if(ent == null || ent instanceof Item || ent instanceof Stairs || ent instanceof Floor)
            player.move(direction, 1);
        else if(ent instanceof Creature)
            attack((Creature)ent);

        ent = map.atPosition(player.getRow(), player.getColumn());
        if(ent instanceof Item)
            pickUp((Item)ent);

        if(ent instanceof Stairs)
        {
            nextLevel();
            return;
        }

        map.takeTurns();
        update();
    }
}
