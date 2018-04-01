package mapgeneration;

import control.Coordinate;
import control.Map;

public class PersistedMap
{
    private Map map;
    private Integer[][] data;
    private Coordinate upStairs;
    private Coordinate downStairs;

    public PersistedMap(Map map, Integer[][] data, Coordinate upStairs, Coordinate downStairs)
    {
        this.map = map;
        this.data = data;
        this.upStairs = upStairs;
        this.downStairs = downStairs;
    }

    public Map getMap() {
        return map;
    }

    public Integer[][] getData() {
        return data;
    }

    public Coordinate getUpStairs() {
        return upStairs;
    }

    public Coordinate getDownStairs() {
        return downStairs;
    }
}
