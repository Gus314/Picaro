package skills.player.hunter;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.TargetSkill;

import java.io.Serializable;

public class Stab extends TargetSkill implements Serializable
{
    private static final int cost = 4;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Stab";
    private static final int range = 1;

    @Override
    public String getDescription()
    {
        return "Attack for greatly increased damage but with no possiblity of a critical.";
    }


    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        if(Controller.getGenerator().nextInt(100 - target.getBlockChance()) == 0)
        {
            return "Attack was blocked!";

        }

        int damage = source.getMinDamage() + Controller.getGenerator().nextInt(source.getMaxDamage() - source.getMinDamage()) - target.getDefense();

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
            return "Attack was absorbed for " + amount + " hp!";
        }

        int targetLife = target.getLife();

        damage = damage * 2;
        String targetName = target.getName();

        if(damage <= 0)
        {
            return targetName + " defense nullified attack!";
        }

        targetLife -= damage;
        target.setLife(targetLife);
        subtractCost(source);

        return targetName + " took " + damage + " damage!";
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
