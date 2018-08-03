package entities.ai;

import control.Controller;
import control.Map;
import entities.Entity;
import entities.Monster;
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
        // TODO: Move meaningfully.
        Direction[] directionArray = Direction.values();
        ArrayList<Direction> directions =  new ArrayList<Direction>();
        for(int i = 0; i < directionArray.length; i++)
        {
            directions.add(directionArray[i]);
        }

        if(canMove())
        {
            int row = getMonster().getRow();
            int column = getMonster().getColumn();

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
}
