package skills.player.mage;

import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.SelfSkill;
import statuses.Wise;

public class Pedagogue extends SelfSkill
{
    @Override
    public String action(Creature source)
    {
        Wise wise = new Wise(source, 5, 5);
        source.addStatusEffect(wise);
        subtractCost(source);
        return source.getName() + " cast a pedagogical spell upon themselves.";
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
        return "Pedagogue";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.SUPPORT;
    }

    @Override
    public String getDescription()
    {
        return " cast a spell to improve own intelligence.";
    }
}
