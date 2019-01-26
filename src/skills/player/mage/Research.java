package skills.player.mage;

import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.SelfSkill;
import statuses.Studied;

public class Research extends SelfSkill
{
    @Override
    public String action(Creature source)
    {
        Studied studied = new Studied(source, 5);
        source.addStatusEffect(studied);
        return source.getName() + " studied the world around themselves intently.";
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
        return "Research";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.SUPPORT;
    }

    @Override
    public String getDescription()
    {
        return "Research the world around you intently, becoming studied.";
    }
}
