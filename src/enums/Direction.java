package enums;

import java.util.Random;

public enum Direction
{
    UPLEFT(3), UPRIGHT(1), DOWNLEFT(5), DOWNRIGHT(7), UP(2), LEFT(4), RIGHT(0), DOWN(6);

    private int value;

    Direction(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }

    public static Direction random()
    {
        return fromValue(generator.nextInt(8));
    }

    private static Direction fromValue(int value)
    {
        for(Direction dir: Direction.values())
        {
            if(dir.getValue() == value)
            {
                return dir;
            }
        }

        // TODO: Add exception.
        return null;
    }

    private static final Random generator = new Random();

    public int rowShift()
    {
        switch(this)
        {
            case UP:
            case UPLEFT:
            case UPRIGHT:
                return -1;
            case LEFT:
            case RIGHT:
                return 0;
            case DOWNLEFT:
            case DOWN:
            case DOWNRIGHT:
                return 1;
            default:
                return 0; // TODO: Add exception.
        }
    }

    public int columnShift()
    {
        switch(this)
        {
            case UPLEFT:
            case LEFT:
            case DOWNLEFT:
                return -1;
            case UP:
            case DOWN:
                return 0;
            case UPRIGHT:
            case RIGHT:
            case DOWNRIGHT:
                return 1;
            default:
                return 0; // TODO: Add exception.
        }
    }
}
