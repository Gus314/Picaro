package skills;

import entities.Creature;
import enums.TargetType;

import java.util.List;

public abstract class AreaSkill extends Skill
{
    public abstract int getRange();
    public abstract int getRadius();

    public abstract String action(Creature source, List<Creature> targets);

    public TargetType getTargetType(){ return TargetType.AREA;}
}
