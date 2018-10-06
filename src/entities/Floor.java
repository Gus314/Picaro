package entities;

import java.io.Serializable;

public class Floor extends Entity implements Serializable
{
    private static final String name = "Floor";

    public Floor(int inRow, int inColumn)
    {
        super('.', inRow, inColumn, name);
    }

    public boolean blocksLineOfSight(){ return false;}

    public boolean passable(){return true;}
}
