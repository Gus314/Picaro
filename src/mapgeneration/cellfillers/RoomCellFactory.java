package mapgeneration.cellfillers;

public class RoomCellFactory
{
    private int properRoomChance;

    public RoomCellFactory(int inProperRoomChance)
    {
        properRoomChance = inProperRoomChance;
    }

    public RoomCell construct(int rows, int columns)
    {
        return new RoomCell(rows, columns, properRoomChance);
    }
}
