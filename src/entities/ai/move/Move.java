package entities.ai.move;

import control.Controller;
import control.Map;
import entities.creatures.Creature;
import entities.Entity;
import entities.creatures.Monster;
import entities.ai.pathing.PathFinder;
import enums.Direction;
import enums.Faction;

import java.util.ArrayList;
import java.util.List;

public abstract class Move
{
    private Monster monster;
    private Map map;
    private PathFinder pathFinder;

    protected Monster getMonster(){return monster;}
    protected Map getMap(){return map;}
    protected PathFinder getPathFinder(){return pathFinder;}

    public Move(Monster inMonster, Map inMap)
    {
        monster = inMonster;
        map = inMap;
        pathFinder = new PathFinder(map, monster.getSightRadius());
    }

    public int getSuggestedSearchSize(){return monster.getSightRadius();}

    public abstract void move(java.util.Map<Faction, List<Creature>> targets);

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
}
