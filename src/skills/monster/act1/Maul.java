package skills.monster.act1;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import skills.TargetSkill;
import statuses.Bleed;
import enums.SkillType;

import java.io.Serializable;

public class Maul extends TargetSkill implements Serializable
{
    private static final int cost = 5;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Maul";
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
        String message = source.getName() + " mauled " + target.getName();
        int damage = Controller.getGenerator().nextInt(8)+7;
        int maxDamage = target.getLife();
        if(damage > maxDamage)
        {
            damage = maxDamage;
        }
        else if(damage < 0)
        {
            damage = 0;
        }

        target.setLife(target.getLife() - damage);

        int duration = 8;
        int intensity = 1;
        Bleed bleed = new Bleed(target, duration, intensity);
        target.addStatusEffect(bleed);

        message = message + " causing " + damage + " damage.";
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
