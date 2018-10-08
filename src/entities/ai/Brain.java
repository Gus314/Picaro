package entities.ai;

import control.Controller;
import control.Map;
import entities.Monster;
import enums.Behaviour;
import enums.SkillBehaviour;
import enums.TurnType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Brain implements Serializable
{
    private Monster monster;
    private java.util.Map<Behaviour, Integer> weights;
    // These could be made instance variables to provide greater variety in monster behaviour.
    private static final int supportWeight = 3;
    private static final int defendWeight = 3;
    private static final int retreatWeight = 10;
    private static final int attackWeight = 1;

    public Brain(Monster inMonster)
    {
        monster = inMonster;
        weights = new HashMap<Behaviour, Integer>();
        weights.put(Behaviour.SUPPORT, supportWeight);
        weights.put(Behaviour.DEFEND, defendWeight);
        weights.put(Behaviour.RETREAT, retreatWeight);
        weights.put(Behaviour.ATTACK, attackWeight);
    }

    private List<Behaviour> determineDesirableBehaviours()
    {
        List<Behaviour> desirableBehaviours = new ArrayList<Behaviour>();

        double dLife = (double) monster.getLife();
        double dMaxLife = (double) monster.getMaxLife();
        if((dLife / dMaxLife) * 100.0 < 50.0)
        {
            // It is never desirable to be both retreating and attacking.
            desirableBehaviours.add(Behaviour.RETREAT);
        }
        else
        {
            desirableBehaviours.add(Behaviour.ATTACK);
        }

        if(monster.hasSkills(SkillBehaviour.SUPPORT))
        {
            desirableBehaviours.add(Behaviour.SUPPORT);
        }

        if(monster.hasSkills(SkillBehaviour.DEFEND))
        {
            desirableBehaviours.add(Behaviour.DEFEND);
        }

        return desirableBehaviours;
    }

    private int determineTotalWeight(List<Behaviour> behaviours)
    {
        int result = 0;

        for(Behaviour behaviour: behaviours)
        {
            result +=  weights.get(behaviour);
        }

        return result;
    }

    public Behaviour determineBehaviour()
    {
        List<Behaviour> desirableBehaviours = determineDesirableBehaviours();
        int totalWeight = determineTotalWeight(desirableBehaviours);
        int choice = Controller.getGenerator().nextInt(totalWeight);

        int startIndex = 0;
        for(Behaviour behaviour: desirableBehaviours)
        {
            int endIndex = startIndex + weights.get(behaviour);
            if(choice >= startIndex && choice < endIndex)
            {
                return behaviour;
            }
            startIndex = endIndex;
        }

        System.out.println("Brain::determineBehaviour - unable to choose behaviour, defaulting to attack.");
        return Behaviour.ATTACK;
    }

    public TurnType determineTurnType(boolean inAttackRange, boolean friendNear, boolean playerSighted, Behaviour behaviour)
    {
        // Note that there may be cases where neither act nor move are possible.
        if(!playerSighted)
        {
            // Creature does not 'activate' until seen by the player.
            return TurnType.SKIP;
        }

        switch(behaviour)
        {
            case DEFEND:
            {
                boolean shouldAct = (Controller.getGenerator().nextInt(2) == 1) || (!monster.canMove());
                return shouldAct ? TurnType.ACT : TurnType.MOVE;
            }
            case ATTACK:
            {
                if(!(monster.canMove() || inAttackRange))
                {
                    return TurnType.SKIP;
                }
                return inAttackRange ? TurnType.ACT : TurnType.MOVE;
            }
            case RETREAT:
            {
                boolean canActRetreat = monster.hasSkills(SkillBehaviour.RETREAT);

                if(!(canActRetreat || monster.canMove()))
                {
                    return TurnType.SKIP;
                }

                boolean shouldAct = canActRetreat && ((Controller.getGenerator().nextInt(2) == 1) || (!monster.canMove()));

                return shouldAct ? TurnType.ACT : TurnType.MOVE;
            }
            case SUPPORT:
            {
                // Cannot make a supportive move without allies nearby.
                boolean shouldMove = (monster.canMove() && friendNear && (Controller.getGenerator().nextInt(2) == 1));
                return shouldMove ? TurnType.MOVE : TurnType.ACT;
            }
            default:
            {
                System.out.println("Brain::determineTurnType - unexpected behaviour.");
                return TurnType.SKIP;
            }
        }
    }
}
