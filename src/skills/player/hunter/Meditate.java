package skills.player.hunter;

import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.SelfSkill;
import statuses.Zen;

public class Meditate extends SelfSkill
{
    @Override
    public String action(Creature source)
    {
        Zen zen = new Zen(source, 5);
        source.addStatusEffect(zen);
        subtractCost(source);
        return source.getName() + " begins to meditate.";
    }

    @Override
    public int getCost()
    {
        return 5;
    }

    @Override
    public SkillType getSkillType()
    {
        return SkillType.PHYSICAL;
    }

    @Override
    public String getName()
    {
        return "Meditate";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.SUPPORT;
    }

    @Override
    public String getDescription()
    {
        return "Meditate, entering a zen state.";
    }
}
