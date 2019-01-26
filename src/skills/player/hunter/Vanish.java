package skills.player.hunter;

import entities.Entity;
import entities.Floor;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.FloorSkill;

import java.util.Collection;

public class Vanish extends FloorSkill
{
    @Override
    public int getCost()
    {
        return 8;
    }

    @Override
    public SkillType getSkillType()
    {
        return SkillType.PHYSICAL;
    }

    @Override
    public String getName()
    {
        return "Vanish";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.RETREAT;
    }

    @Override
    public String getDescription()
    {
        return "Vanish and reappear at target location.";
    }

    @Override
    public int getRange()
    {
        return 8;
    }

    @Override
    public String action(Creature source, Floor floor, Collection<Entity> additions)
    {
        source.setPosition(floor.getPosition());
        subtractCost(source);
        return source.getName() + " vanished.";
    }
}
