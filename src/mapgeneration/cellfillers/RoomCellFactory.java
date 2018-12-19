package mapgeneration.cellfillers;

import java.io.Serializable;

public class RoomCellFactory implements Serializable
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

    public void setProperRoomChance(int inProperRoomChance)
    {
        properRoomChance = inProperRoomChance;
    }
}
