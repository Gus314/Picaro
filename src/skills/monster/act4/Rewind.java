package skills.monster.act4;

import control.Controller;
import control.Coordinate;
import entities.Entity;
import entities.Floor;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.StatType;
import skills.SelfSkill;
import skills.TargetSkill;
import enums.SkillType;
import statuses.Bleed;
import statuses.Recklessness;

import java.io.Serializable;
import java.util.Collection;

public class Rewind extends SelfSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    private static final int cost = 10;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Rewind";

    private Coordinate lastPosition;
    private int lastLife;
    private int lastMp;
    private int lastPp;
    private boolean prepared = false;

    @Override
    public int getCost()
    {
        return cost;
    }

    @Override
    public SkillType getSkillType()
    {
        return skillType;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.RETREAT;
    }

    @Override
    public String action(Creature source)
    {
        // Update fields for next use of rewind.
        lastPosition = source.getPosition();
        lastLife = source.getLife();
        lastMp = source.getMagicPoints();
        lastPp = source.getPhysicalPoints();

        String message = "";
        if(prepared)
        {
            source.setLife(lastLife);
            source.setMagicPoints(lastMp);
            source.setPhysicalPoints(lastPp);

            if(source.getMap().isEmpty(lastPosition))
            {
                source.setPosition(lastPosition);
            }
            else
            {
                Collection<Entity> nowAtLastPosition = source.getMap().atPosition(lastPosition.getRow(), lastPosition.getColumn());
                for(Entity next: nowAtLastPosition)
                {
                    // Only move creatures to prevent multiple creatures being on position rewound to.
                    if(next instanceof Creature)
                    {
                        next.setPosition(source.getPosition());
                    }
                }
                source.setPosition(lastPosition);
            }
            message = source.getName() + " rewound time.";
        }
        else
        {
            prepared = true;
            message =  source.getName() + " prepared to rewind time.";
        }

        subtractCost(source);
        return message;
    }
}
