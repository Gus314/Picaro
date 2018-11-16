package entities.furniture;

import entities.Entity;
import entities.creatures.Player;

import java.io.Serializable;

public abstract class Furniture extends Entity implements Serializable
{
    public Furniture(Character inCha, int inRow, int inColumn, String inName)
    {
        super(inCha, inRow, inColumn, inName);
        used = false;
    }

    private boolean used;

    protected boolean getUsed(){return used;}

    protected void setUsed(boolean inUsed){used = inUsed;}

    public abstract void use(Player player);

    public boolean blocksLineOfSight(){ return false;}

    public boolean passable(){return true;}
}