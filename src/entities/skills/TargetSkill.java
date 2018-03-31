package entities.skills;

import entities.Creature;

import java.util.List;

public abstract class TargetSkill extends Skill
{
    public abstract int getRange();

    public abstract String action(Creature source, Creature target);
}
