package skills.player.mage;

import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.TargetSkill;
import statuses.Cynical;

public class Skeptic extends TargetSkill
{
    @Override
    public int getRange()
    {
        return 7;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        Cynical cynical = new Cynical(target, 5);
        target.addStatusEffect(cynical);
        subtractCost(source);
        return getName() + " cast a skeptical spell upon " + target.getName() + ".";
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
        return "Skeptic";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.ATTACK;
    }

    @Override
    public String getDescription()
    {
        return "Make target more cynical of the world around them.";
    }
}
