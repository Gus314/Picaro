package entities;

import java.io.Serializable;

public class UpStairs extends Entity implements Serializable
{
    private static final String name = "Stairs Up";

    public UpStairs(int inRow, int inColumn)
    {
        super('<', inRow, inColumn, name);
    }

    public boolean blocksLineOfSight(){ return false;}

    public boolean passable(){return true;}
}
