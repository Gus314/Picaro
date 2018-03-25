package enums;

public enum FloorType
{
    EMPTY(0), OUTSIDE(1), FLOOR(2);

    private int value;

    FloorType(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }
}
