package entities;

public class TargetPoint extends Entity
{
    public TargetPoint(int inRow, int inColumn)
    {
        super('.', inRow, inColumn, "Target Point");
    }

    public boolean blocksLineOfSight(){ return true;}

    public boolean passable(){return true;}
}
