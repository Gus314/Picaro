package entities.skills;

import entities.Creature;
import enums.TargetType;

import java.util.List;

public abstract class TargetSkill extends Skill
{
    public abstract int getRange();

    public abstract String action(Creature source, Creature target);

    public TargetType getTargetType(){ return TargetType.TARGET;}
}
