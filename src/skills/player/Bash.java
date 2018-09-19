package skills.player;

import control.Controller;
import entities.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.TargetSkill;

public class Bash extends TargetSkill
{
    private static final int cost = 5;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Bash";
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
            return "Bash was absorbed for " + amount + " hp!";
        }

        int targetLife = target.getLife();

        String targetName = target.getName();

        targetLife -= damage;
        target.setLife(targetLife);

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
