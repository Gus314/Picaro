package entities;

public class Floor extends Entity
{
    private static final String name = "Floor";

    public Floor(int inRow, int inColumn)
    {
        super('.', inRow, inColumn, name);
    }

    public boolean blocksLineOfSight(){ return false;}

    public boolean passable(){return true;}
}
