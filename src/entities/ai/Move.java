package entities.ai;

import control.Map;
import entities.Entity;
import entities.Monster;
import enums.Direction;

import java.util.List;

public abstract class Move
{
    private Monster monster;
    private Map map;

    protected Monster getMonster(){return monster;}
    protected Map getMap(){return map;}

    public Move(Monster inMonster, Map inMap)
    {
        monster = inMonster;
        map = inMap;
    }

    public abstract void move();

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
