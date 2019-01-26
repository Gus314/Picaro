package skills.player.mage;

import control.Coordinate;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.TargetSkill;

public class Swap extends TargetSkill
{
    @Override
    public int getRange()
    {
        return 7;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        Coordinate originalSourcePosition = source.getPosition();
        source.setPosition(target.getPosition());
        target.setPosition(originalSourcePosition);
        subtractCost(source);
        return source.getName() + " swaps places with " + target.getName() + ".";
    }

    @Override
    public int getCost()
    {
        return 5;
    }

    @Override
    public SkillType getSkillType()
    {
        return SkillType.MAGICAL;
    }

    @Override
    public String getName()
    {
        return "Swap";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.SUPPORT;
    }

    @Override
    public String getDescription()
    {
        return "Swap places with target.";
    }
}
