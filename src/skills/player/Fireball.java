package skills.player;

import control.Controller;
import entities.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.AreaSkill;

import java.io.Serializable;
import java.util.List;

public class Fireball extends AreaSkill implements Serializable
{
    private static final int range = 5;
    private static final int radius = 3;
    private static final int cost = 6;
    private static final SkillType skillType = SkillType.MAGICAL;
    private static final String name = "Fireball";

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public int getRadius() {
        return radius;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public SkillType getSkillType() {
        return skillType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String action(Creature source, List<Creature> targets)
    {
        String message = source.getName() + " cast fireball, hitting the following: ";
        int baseDamage = source.getIntelligence()/2 + Controller.getGenerator().nextInt(source.getIntelligence()/2);

        for(Creature target: targets)
        {
            int baseReduction = source.getMagicDefense()/2 + Controller.getGenerator().nextInt(source.getMagicDefense()/2);
            int actualDamage = (baseReduction > baseDamage) ? 0 : baseDamage - baseReduction;
            int maxDamage = target.getLife();
            if(actualDamage > maxDamage)
            {
                actualDamage = maxDamage;
            }
            target.setLife(target.getLife() - actualDamage);
            message += target.getName() + " for " + actualDamage + " damage.";
        }
        subtractCost(source);
        return message;
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.SUPPORT;
    }
}
