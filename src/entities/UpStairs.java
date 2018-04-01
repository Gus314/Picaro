package entities;

public class UpStairs extends Entity
{
    public UpStairs(int inRow, int inColumn)
    {
        super('<', inRow, inColumn);
    }

    public boolean blocksLineOfSight(){ return false;}
}
