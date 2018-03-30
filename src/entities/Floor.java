package entities;

public class Floor extends Entity
{
    public Floor(int inRow, int inColumn)
    {
        super('.', inRow, inColumn);
    }

    public boolean blocksLineOfSight(){ return false;}
}
