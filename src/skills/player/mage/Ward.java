package skills.player.mage;

import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.SelfSkill;
import statuses.Shield;

public class Ward extends SelfSkill
{
    @Override
    public String action(Creature source)
    {
        Shield shield = new Shield(source, 5, 4);
        source.addStatusEffect(shield);
        subtractCost(source);
        return source.getName() + " cast Ward.";
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
        return "Ward";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.DEFEND;
    }

    @Override
    public String getDescription()
    {
        return "Protect self with a shielding ward.";
    }
}
