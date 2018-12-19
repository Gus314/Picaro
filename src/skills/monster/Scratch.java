package skills.monster;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import skills.TargetSkill;
import statuses.Bleed;
import enums.SkillType;

import java.io.Serializable;

public class Scratch extends TargetSkill implements Serializable
{
    private static final int cost = 0;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Bite";
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
        String message = source.getName() + " scratched " + target.getName();
        int baseDamage = Controller.getGenerator().nextInt(8)+7;
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

        int duration = 8;
        int intensity = 1;
        Bleed bleed = new Bleed(target, duration, intensity);
        target.addStatusEffect(bleed);

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
