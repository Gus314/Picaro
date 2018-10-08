package entities.ai.pathing;

import control.Coordinate;
import entities.Entity;
import enums.Direction;

public class ValidPathInfo extends PathInfo
{
    private Coordinate closer;
    private Coordinate further;

    public ValidPathInfo(Coordinate inCloser, Coordinate inFurther)
    {
        closer = inCloser;
        further = inFurther;
    }

    public Direction closerMove(Entity source)
    {
        Coordinate start = new Coordinate(source.getRow(), source.getColumn());
        return start.determineDirection(closer);
    }

    public Direction furtherMove(Entity source)
    {
        Coordinate start = new Coordinate(source.getRow(), source.getColumn());
        return start.determineDirection(further);
    }
}
