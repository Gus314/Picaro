package skills;

import entities.Creature;
import statuses.Poison;
import enums.SkillType;

public class PoisonDart extends TargetSkill
{
    private static final int cost = 3;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Poison Dart";
    private static final int range = 4;

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        String message = source.getName() + " fired a poison dart at " + target.getName();
        int baseDamage = getGenerator().nextInt(4);
        int damageReduction = target.getDefense()/2 + getGenerator().nextInt(target.getDefense()/2);
        int adjustedDamage = baseDamage - damageReduction;
        int maxDamage = target.getLife();
        if(adjustedDamage > maxDamage)
        {
            adjustedDamage = maxDamage;
        }

        target.setLife(target.getLife() - adjustedDamage);
        message = message + " causing " + adjustedDamage + " damage and ";

        // 50% chance.
        boolean poisoned = getGenerator().nextInt(10) > 4;
        if(poisoned)
        {
            int duration = 10;
            int intensity = 1;
            Poison poison = new Poison(target, duration, intensity);
            target.addStatusEffect(poison);
            message = message + " successfully inflicting ";
        }
        else
        {
            message = message + " failing to inflict ";
        }

        message = message + " poison.";

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
}
