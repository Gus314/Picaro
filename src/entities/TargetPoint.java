package entities;

public class TargetPoint extends Entity
{
    public TargetPoint(int inRow, int inColumn)
    {
        super('.', inRow, inColumn);
    }

    public boolean blocksLineOfSight(){ return true;}
}
