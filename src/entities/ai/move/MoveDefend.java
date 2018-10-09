package entities.ai.move;

import control.Controller;
import control.Map;
import entities.Creature;
import entities.Entity;
import entities.Floor;
import entities.Monster;
import entities.ai.pathing.PathFinder;
import entities.ai.pathing.PathInfo;
import entities.ai.pathing.ValidPathInfo;
import enums.Direction;
import enums.Faction;

import javax.naming.ldap.Control;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class MoveDefend extends Move
{
    public MoveDefend(Monster inMonster, Map inMap)
    {
        super(inMonster, inMap);
    }

    private java.util.Map<Creature, ValidPathInfo> findValidPathsToTargets(List<Creature> targets)
    {
        java.util.Map<Creature, ValidPathInfo> result = new HashMap<Creature, ValidPathInfo>();

        for(Creature target: targets)
        {
            PathInfo pathInfo = PathFinder.findShortestPath(getMonster(), target, getMap(), getSuggestedSearchSize());

            if(pathInfo instanceof ValidPathInfo)
            {
                result.put(target, (ValidPathInfo)pathInfo);
            }
        }

        return result;
    }

    private static ValidPathInfo chooseTargetPath(java.util.Map<Creature, ValidPathInfo> validPathsToTargets)
    {
        // TODO: Improve target selection.
        Creature target = (Creature) validPathsToTargets.keySet().toArray()[0];
        return validPathsToTargets.get(target);
    }

    @Override
    public void move(java.util.Map<Faction, List<Creature>> targets)
    {
        if(getMonster().canMove())
        {
            java.util.Map<Creature, ValidPathInfo> validPathsToTargets = findValidPathsToTargets(targets.get(getMonster().getFaction().opposing()));

            if(validPathsToTargets.keySet().isEmpty())
            {
                randomMove();
            }
            else
            {
                ValidPathInfo pathInfo = chooseTargetPath(validPathsToTargets);
                // Attempt to stay at the same distance on average.
                boolean closer = Controller.getGenerator().nextBoolean();

                Direction direction = closer ? pathInfo.closerMove(getMonster()) :
                        pathInfo.furtherMove(getMonster());
                getMonster().move(direction, 1);
            }
        }
    }
}
