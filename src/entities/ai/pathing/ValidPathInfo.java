package entities.ai.pathing;

import control.Coordinate;
import entities.Entity;
import enums.Direction;

public class ValidPathInfo extends PathInfo
{
    private Coordinate closer;

    public ValidPathInfo(Coordinate inCloser)
    {
        closer = inCloser;
    }

    public Direction closerMove(Entity source)
    {
        Coordinate start = new Coordinate(source.getRow(), source.getColumn());

        return determineDirection(start, closer);
    }

    private static Direction determineDirection(Coordinate source, Coordinate target) {
        int rowChange = target.getRow() - source.getRow();
        int columnChange = target.getColumn() - source.getColumn();

        if (rowChange == 0 && columnChange == 0)
        {
            System.out.println("ValidPathInfo::determineDirection - source equals target.");
            return Direction.UP;
        }

        boolean up = rowChange < 0;
        boolean down = rowChange > 0;
        boolean left = columnChange < 0;
        boolean right = columnChange > 0;

        if(up && left)
        {
            return Direction.UPLEFT;
        }
        if(up && right)
        {
            return Direction.UPRIGHT;
        }
        if(down && left)
        {
            return Direction.DOWNLEFT;
        }
        if(down && right)
        {
            return Direction.DOWNRIGHT;
        }
        if(left)
        {
            return Direction.LEFT;
        }
        if(right)
        {
            return Direction.RIGHT;
        }
        if(up)
        {
            return Direction.UP;
        }
        if(down)
        {
            return Direction.DOWN;
        }

        System.out.println("ValidPathInfo::determineDirection - failed to determine direction.");
        return Direction.DOWN;
    }

    public Direction furtherMove(Entity source)
    {
        // TODO: Fix this.
        return Direction.UP;
    }
}
