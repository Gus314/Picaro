package skills.player.mage;

import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.TargetSkill;

public class SpiritShift extends TargetSkill
{
    @Override
    public int getRange()
    {
        return 7;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        int sourceOriginalMp = source.getMagicPoints();
        source.setMagicPoints(target.getMaxMagicPoints());
        target.setMagicPoints(sourceOriginalMp);
        subtractCost(source);
        return source.getName() + " swapped magic points with " + target.getName() + ".";
    }

    @Override
    public int getCost()
    {
        return 8;
    }

    @Override
    public SkillType getSkillType()
    {
        return SkillType.MAGICAL;
    }

    @Override
    public String getName()
    {
        return "Spirit Shift";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.ATTACK;
    }

    @Override
    public String getDescription()
    {
        return "Swap mp with target.";
    }
}
