package skills;

import entities.creatures.Creature;
import enums.TargetType;

public abstract class TargetSkill extends Skill
{
    public abstract int getRange();

    public abstract String action(Creature source, Creature target);

    public TargetType getTargetType(){ return TargetType.TARGET;}
}
