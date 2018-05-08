package entities.ai;

import control.Map;
import entities.Monster;

public class MoveRetreat extends Move
{
    public MoveRetreat(Monster inMonster, Map inMap)
    {
        super(inMonster, inMap);
    }

    @Override
    public void move()
    {
        System.out.println("MoveRetreat::move() - todo.");
        // TODO: Implement.
    }
}
