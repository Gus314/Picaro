package mapgeneration.cellfillers;

import control.Controller;

public class CellConnectionFactory
{
    public static CellConnection construct(boolean valid, int maxOffset)
    {
        if(valid)
        {
            // Return offset between -maxoffset and maxoffset.
            int offset = Controller.getGenerator().nextInt((maxOffset*2)+1) - maxOffset;
            return new ValidCellConnection(offset);
        }
        else
        {
            return new InvalidCellConnection();
        }
    }
}
