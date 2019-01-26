package skills.player.mage;

import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.TargetSkill;
import statuses.StatusEffect;

public class Transfer extends TargetSkill
{
    @Override
    public int getRange()
    {
        return 7;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        for(StatusEffect status: source.getStatusEffects())
        {
            source.removeStatusEffect(status);
            target.addStatusEffect(status);
        }

        subtractCost(source);
        return source.getName() + " transferred their status effects to " + target.getName();
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
        return "Transfer";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.ATTACK;
    }

    @Override
    public String getDescription()
    {
        return "Transfer all current status effects from caster to target.";
    }
}
