package skills;

import enums.SkillType;
import enums.TargetType;

public abstract class Skill
{
    public abstract int getCost();
    public abstract SkillType getSkillType();
    public abstract String getName();
    public abstract TargetType getTargetType();
}
