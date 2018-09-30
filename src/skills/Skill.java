package skills;

import entities.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import enums.TargetType;

public abstract class Skill
{
    public abstract int getCost();
    public abstract SkillType getSkillType();
    public abstract String getName();
    public abstract TargetType getTargetType();
    public abstract SkillBehaviour getSkillBehaviour();

    protected void subtractCost(Creature source)
    {
        switch(getSkillType())
        {
            case PHYSICAL:
            {
                int pp = source.getPhysicalPoints();
                pp = (getCost() < pp) ? (pp - getCost()) : 0;
                source.setPhysicalPoints(pp);
                break;
            }
            case MAGICAL:
            {
                int mp = source.getMagicPoints();
                mp = (getCost() < mp) ? (mp - getCost()) : 0;
                source.setMagicPoints(mp);
                break;
            }
            default:
            {
                System.out.println("Skill::subtractCost() - unexpected skill type.");
                break;
            }
        }
    }
}
