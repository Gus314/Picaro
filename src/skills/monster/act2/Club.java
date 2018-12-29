package skills.monster.act2;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import skills.TargetSkill;
import statuses.Bleed;
import enums.SkillType;

import java.io.Serializable;

public class Club extends TargetSkill implements Serializable
{
    private static final int cost = 8;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Club";
    private static final int range = 1;

    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        String message = source.getName() + " clubbed " + target.getName();
        int damageDifference = source.getMaxDamage() - source.getMinDamage();
        int bound = (damageDifference > 0) ? damageDifference : 1;
        int baseDamage = Controller.getGenerator().nextInt(bound) + source.getMinDamage();
        baseDamage = (int) Math.ceil(baseDamage* 1.4);
        int damageReduction = target.getDefense()/2 + Controller.getGenerator().nextInt(target.getDefense()/2);
        int adjustedDamage = baseDamage - damageReduction;
        int maxDamage = target.getLife();
        if(adjustedDamage > maxDamage)
        {
            adjustedDamage = maxDamage;
        }
        else if(adjustedDamage < 0)
        {
            adjustedDamage = 0;
        }

        target.setLife(target.getLife() - adjustedDamage);

        message = message + " causing " + adjustedDamage + " damage.";
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
        return SkillBehaviour.ATTACK;
    }
}
