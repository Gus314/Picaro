package skills;

import entities.creatures.Creature;
import enums.TargetType;

public abstract class SelfSkill extends Skill
{
    public abstract String action(Creature source);

    public TargetType getTargetType(){ return TargetType.SELF;}
}
