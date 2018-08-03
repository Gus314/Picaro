package skills;

import control.Coordinate;
import control.Map;
import entities.Creature;
import entities.Entity;
import entities.Floor;
import enums.TargetType;
import mapgeneration.cellfillers.RoomCell;

import java.util.Collection;

public abstract class FloorSkill extends Skill
{
    public abstract int getRange();

    public abstract String action(Creature source, Floor floor, Collection<Entity> additions);

    public TargetType getTargetType(){ return TargetType.FLOOR;}
}
