package entities.skills;

import entities.Creature;
import enums.TargetType;

import java.util.List;

public abstract class SelfSkill extends Skill
{
    public abstract String action(Creature source);

    public TargetType getTargetType(){ return TargetType.SELF;}
}
