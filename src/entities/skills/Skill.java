package entities.skills;

import entities.Creature;
import enums.SkillType;

import java.util.List;

public abstract class Skill
{
    public abstract int getCost();
    public abstract SkillType getSkillType();
    public abstract String getName();
}
