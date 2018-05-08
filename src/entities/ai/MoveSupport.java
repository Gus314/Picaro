package entities.ai;

import control.Map;
import entities.Monster;

public class MoveSupport extends Move
{
    public MoveSupport(Monster inMonster, Map inMap)
    {
        super(inMonster, inMap);
    }

    @Override
    public void move()
    {
        System.out.println("MoveSupport::move() - todo.");
        // TODO: Implement.
    }
}
