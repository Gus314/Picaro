package control;

import enums.Direction;

import java.io.Serializable;

public class Coordinate implements Serializable
{
    private int row;
    private int column;

    public Coordinate(int inRow, int inColumn)
    {
        row = inRow;
        column = inColumn;
    }

    public int getRow(){return row;}
    public int getColumn(){return column;}

    public @Override boolean equals(Object o)
    {
        if(!(o instanceof Coordinate))
        {
            return false;
        }
        else
        {
            Coordinate other = (Coordinate) o;
            return (row == other.getRow()) && (column == other.getColumn());
        }
    }

    public @Override int hashCode()
    {
        return (93 * row) + (91 * column);
    }

    public static int adjustRow(Direction direction, int row)
    {
        return row + direction.rowShift();
    }

    public static int adjustColumn(Direction direction, int column)
    {
        return column + direction.columnShift();
    }

    // TODO: Replace repeated code with this method.
    public Coordinate move(Direction direction)
    {
        int newRow = adjustRow(direction, getRow());
        int newColumn = adjustColumn(direction, getColumn());
        return new Coordinate(newRow, newColumn);
    }

    public Direction determineDirection(Coordinate target)
    {
        int rowChange = target.getRow() - getRow();
        int columnChange = target.getColumn() - getColumn();

        if (rowChange == 0 && columnChange == 0)
        {
            System.out.println("Coordinate::determineDirection - source equals target.");
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

        System.out.println("Coordinate::determineDirection - failed to determine direction.");
        return Direction.DOWN;
    }
}
