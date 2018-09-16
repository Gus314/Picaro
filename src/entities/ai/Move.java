package entities.ai;

import control.Controller;
import control.Map;
import entities.Entity;
import entities.Monster;
import enums.Direction;

import java.util.ArrayList;
import java.util.List;

public abstract class Move
{
    private Monster monster;
    private Map map;
    private static final int suggestedSearchSize = 10;

    protected Monster getMonster(){return monster;}
    protected Map getMap(){return map;}

    public Move(Monster inMonster, Map inMap)
    {
        monster = inMonster;
        map = inMap;
    }

    public static int getSuggestedSearchSize(){return suggestedSearchSize;}

    public abstract void move();

    protected void randomMove()
    {
        int row = getMonster().getRow();
        int column = getMonster().getColumn();

        Direction[] directionArray = Direction.values();
        ArrayList<Direction> directions =  new ArrayList<Direction>();
        for(int i = 0; i < directionArray.length; i++)
        {
            directions.add(directionArray[i]);
        }

        while(!directions.isEmpty())
        {
            int index = Controller.getGenerator().nextInt(directions.size());
            Direction direction = directions.remove(index);

            List<Entity> here = getMap().atPosition(row + direction.rowShift(), column + direction.columnShift());
            if(Entity.passable(here))
            {
                getMonster().move(direction, 1);
                break;
            }
        }
    }

    protected boolean canMove()
    {
        int row = getMonster().getRow();
        int column = getMonster().getColumn();

        for(Direction direction: Direction.values())
        {
            List<Entity> here = getMap().atPosition(row + direction.rowShift(), column + direction.columnShift());
            if(Entity.passable(here))
            {
                return true;
            }
        }

        return false;
    }
}
