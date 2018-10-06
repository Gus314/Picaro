package mapgeneration.cellfillers;

import java.io.Serializable;

public class ValidCellConnection extends CellConnection implements Serializable
{
    private int offset;

    public ValidCellConnection(int inOffset)
    {
        offset = inOffset;
    }

    public int getOffset(){return offset;}
}
