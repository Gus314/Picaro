package skills.player.warrior;

import control.Controller;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.SelfSkill;
import skills.TargetSkill;

public class Whirlwind extends SelfSkill
{
    @Override
    public String getDescription()
    {
        return "Attack all enemies around you for reduced damage.";
    }


    private static final int cost = 8;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Whirlwind";

    @Override
    public String action(Creature source)
    {
        String message = source.getName() + " unleashes a whirlwind of attacks.";

        for(Creature target: source.getSurroundingCreatures())
        {
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
                return "Whirlwind was absorbed for " + amount + " hp!";
            }

            int targetLife = target.getLife();

            String targetName = target.getName();

            targetLife -= damage;
            target.setLife(targetLife);
            message += targetName + " took " + damage + " damage!";
        }

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