package skills.player.warrior;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.TargetSkill;
import statuses.Stun;

public class Daze extends TargetSkill
{
    @Override
    public String getDescription()
    {
        return "Attempt to stun an enemy.";
    }

    private static final int cost = 5;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Daze";
    private static final int range = 1;

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        String message = "";
        int damage = source.getMinDamage() + Controller.getGenerator().nextInt(source.getMaxDamage() - source.getMinDamage());
        damage -= source.getDefense();
        if(damage < 0)
        {
            return target.getName() + " defended against the attack.";
        }

        if(Controller.getGenerator().nextInt(100 - target.getAbsorbChance()) == 0)
        {
            int newTargetLife = target.getLife() + damage;
            int maxTargetLife = target.getMaxLife();
            int amount = damage;
            if(newTargetLife > maxTargetLife)
            {
                amount = maxTargetLife - target.getLife();
            }
            target.setLife(newTargetLife);
            return "Daze was absorbed for " + amount + " hp!";
        }

        int targetLife = target.getLife();

        String targetName = target.getName();

        targetLife -= damage;
        target.setLife(targetLife);

        int duration = 5;
        target.addStatusEffect(new Stun(target, duration));
        subtractCost(source);
        return targetName + " took " + damage + " damage and was stunned!";
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
