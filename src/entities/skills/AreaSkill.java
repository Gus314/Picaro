package entities.skills;

import entities.Creature;

import java.util.List;

public abstract class AreaSkill extends Skill
{
    public abstract int getRange();
    public abstract int getRadius();

    public abstract String action(Creature source, List<Creature> targets);
}
