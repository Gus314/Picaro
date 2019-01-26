package skills.player.hunter;

import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.SelfSkill;
import statuses.Risky;

public class RiskTaker extends SelfSkill
{
    @Override
    public String action(Creature source)
    {
        Risky risky = new Risky(source, 5);
        source.addStatusEffect(risky);
        subtractCost(source);
        return source.getName() + " becomes a risk taker.";
    }

    @Override
    public int getCost()
    {
        return 8;
    }

    @Override
    public SkillType getSkillType()
    {
        return SkillType.PHYSICAL;
    }

    @Override
    public String getName()
    {
        return "Risk Taker";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.SUPPORT;
    }

    @Override
    public String getDescription()
    {
        return "Prepare to take some reckless risks, greatly increasing crit chance at the cost of defense.";
    }
}
