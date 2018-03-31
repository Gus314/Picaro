package entities.skills;

import entities.Creature;

import java.util.List;

public abstract class SelfSkill extends Skill
{
    public abstract String action(Creature source);
}
