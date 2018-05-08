package entities.ai;

import control.Map;
import entities.Monster;

public abstract class Move
{
    private Monster monster;
    private Map map;

    protected Monster getMonster(){return monster;}
    protected Map getMap(){return map;}

    public Move(Monster inMonster, Map inMap)
    {
        monster = inMonster;
        map = inMap;
    }

    public abstract void move();
}
