package mapgeneration.cellfillers;

public class ValidCellConnection extends CellConnection
{
    private int offset;

    public ValidCellConnection(int inOffset)
    {
        offset = inOffset;
    }

    public int getOffset(){return offset;}
}
