package skills;

import entities.Creature;
import enums.SkillType;

import java.util.List;

public class Fireball extends AreaSkill
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
        int baseDamage = source.getIntelligence()/2 + getGenerator().nextInt(source.getIntelligence()/2);

        for(Creature target: targets)
        {
            int baseReduction = source.getMagicDefense()/2 + getGenerator().nextInt(source.getMagicDefense()/2);
            int actualDamage = (baseReduction > baseDamage) ? 0 : baseDamage - baseReduction;
            int maxDamage = target.getLife();
            if(actualDamage > maxDamage)
            {
                actualDamage = maxDamage;
            }
            target.setLife(target.getLife() - actualDamage);
            message += target.getName() + " for " + actualDamage + " damage.";
        }

        return message;
    }
}
