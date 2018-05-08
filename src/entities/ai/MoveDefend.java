package entities.ai;

import control.Map;
import entities.Monster;

public class MoveDefend extends Move
{
    public MoveDefend(Monster inMonster, Map inMap)
    {
        super(inMonster, inMap);
    }

    @Override
    public void move()
    {
        System.out.println("MoveDefend::move() - todo.");
        // TODO: Implement.
    }
}
