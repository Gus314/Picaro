package skills;

import enums.SkillType;
import enums.TargetType;

import java.util.Random;

public abstract class Skill
{
    public abstract int getCost();
    public abstract SkillType getSkillType();
    public abstract String getName();
    public abstract TargetType getTargetType();

    private static Random generator = new Random();
    protected Random getGenerator(){ return generator;}
}
