package entities.furniture;

import entities.Entity;
import entities.Player;

import java.io.Serializable;

public abstract class Furniture extends Entity implements Serializable
{
    public Furniture(Character inCha, int inRow, int inColumn, String inName)
    {
        super(inCha, inRow, inColumn, inName);
        used = false;
    }

    public boolean used;

    public abstract void use(Player player);

    public boolean blocksLineOfSight(){ return false;}

    public boolean passable(){return true;}
}