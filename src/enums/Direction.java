package enums;

public enum Direction
{
    UPLEFT, UPRIGHT, DOWNLEFT, DOWNRIGHT, UP, LEFT, RIGHT, DOWN;

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
            {
                System.out.println("Direction::rowShift() - unexpected direction.");
                return 0;
            }
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
            {
                System.out.println("Direction::columnShift() - unexpecrted direction.");
                return 0;
            }
        }
    }
}
