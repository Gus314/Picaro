package skills.monster.act1;

import entities.creatures.Creature;
import enums.SkillBehaviour;
import skills.SelfSkill;
import enums.SkillType;
import statuses.Shield;

import java.io.Serializable;

public class Dodge extends SelfSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    private static final int cost = 7;
    private static final SkillType skillType = SkillType.MAGICAL;
    private static final String name = "Dodge";

    @Override
    public String action(Creature source)
    {
        String message = source.getName() + " prepared to dodge magic.";

        int duration = 4;
        int intensity = 6;
        Shield shield = new Shield(source, duration, intensity);
        source.addStatusEffect(shield);

        subtractCost(source);

        return message;
    }

    @Override
    public int getCost()
    {
        return cost;
    }

    @Override
    public SkillType getSkillType()
    {
        return skillType;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.DEFEND;
    }
}
