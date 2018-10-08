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
            default:
            {
                System.out.println("TurnHandler::adjustRow - unexpected direction.");
                return row;
            }
        }
    }

    public static int adjustColumn(Direction direction, int column)
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
            default:
            {
                System.out.println("TurnHandler::adjustColumn - unexpected direction.");
                return column;
            }
        }
    }
}
