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
}
