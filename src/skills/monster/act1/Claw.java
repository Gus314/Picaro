package skills.monster.act1;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import skills.TargetSkill;
import statuses.Bleed;
import enums.SkillType;

import java.io.Serializable;

public class Claw extends TargetSkill implements Serializable
{
    private static final int cost = 5;
    private static final SkillType skillType = SkillType.MAGICAL;
    private static final String name = "Claw";
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

    public String action(Creature source, Creature target)
    {
        String message = source.getName() + " scratched " + target.getName();
        int baseDamage = Controller.getGenerator().nextInt(8)+7;

        boolean critical = (Controller.getGenerator().nextInt(2) == 1);
        int adjustedDamage =  critical ? baseDamage * 2 : baseDamage;

        if(critical)
        {
            message = message + " with a critical attack ";
        }

        int damageReduction = target.getDefense()/2 + Controller.getGenerator().nextInt(target.getDefense()/2);
        adjustedDamage -= damageReduction;
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

        int duration = 4;
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
