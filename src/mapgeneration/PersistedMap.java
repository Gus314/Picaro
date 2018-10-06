package mapgeneration;

import control.Coordinate;
import control.Map;

import java.io.Serializable;

public class PersistedMap implements Serializable
{
    private Map map;
    private Coordinate upStairs;
    private Coordinate downStairs;

    public PersistedMap(Map map, Coordinate upStairs, Coordinate downStairs)
    {
        this.map = map;
        this.upStairs = upStairs;
        this.downStairs = downStairs;
    }

    public Map getMap() {
        return map;
    }

    public Coordinate getUpStairs() {
        return upStairs;
    }

    public Coordinate getDownStairs() {
        return downStairs;
    }
}
