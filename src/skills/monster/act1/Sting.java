package skills.monster.act1;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import skills.TargetSkill;
import statuses.Poison;
import enums.SkillType;

import java.io.Serializable;

public class Sting extends TargetSkill implements Serializable
{
    private static final int cost = 0;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Sting";
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
        String message = source.getName() + " stung " + target.getName();
        int baseDamage = Controller.getGenerator().nextInt(5)+15;
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

        int duration = 3;
        int intensity = 2;
        Poison poison = new Poison(target, duration, intensity);
        target.addStatusEffect(poison);

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
