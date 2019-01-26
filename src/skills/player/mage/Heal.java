package skills.player.mage;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.SelfSkill;

import java.io.Serializable;

public class Heal extends SelfSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Recover hp.";
    }

    private static final int cost = 2;
    private static final SkillType skillType = SkillType.MAGICAL;
    private static final String name = "Heal";

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
    public String action(Creature source)
    {
        int healAmount = (source.getIntelligence() / 2) + Controller.getGenerator().nextInt(source.getIntelligence());
        int healableAmount = source.getMaxLife() - source.getLife();

        // Do not heal to more than max life.
        if(healableAmount < healAmount)
        {
            healAmount = healableAmount;
        }

        source.setLife(source.getLife() + healAmount);
        subtractCost(source);
        return source.getName() + " cast heal and recovered " + healAmount + " hp.";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.SUPPORT;
    }
}
