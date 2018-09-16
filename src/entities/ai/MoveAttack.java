package entities.ai;

import control.Controller;
import control.Coordinate;
import control.Map;
import entities.Entity;
import entities.Monster;
import entities.ai.pathing.PathFinder;
import entities.ai.pathing.PathInfo;
import entities.ai.pathing.ValidPathInfo;
import enums.Direction;

import java.util.ArrayList;
import java.util.List;

public class MoveAttack extends Move
{
    public MoveAttack(Monster inMonster, Map inMap)
    {
        super(inMonster, inMap);
    }

    @Override
    public void move()
    {
        if(canMove())
        {
            // TODO: Have other targets than the player.
            PathInfo pathInfo = PathFinder.findPath(getMonster(), getMap().getPlayer(), getMap(), Move.getSuggestedSearchSize());

            if(pathInfo instanceof ValidPathInfo)
            {
                Direction direction = ((ValidPathInfo)pathInfo).closerMove(getMonster());
                System.out.println("Moving with good path.");
                getMonster().move(direction, 1);
            }
            else
            {
                randomMove();
            }
        }
    }
}
