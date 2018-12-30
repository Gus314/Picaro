package skills.monster.act3;

import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.StatType;
import skills.AreaSkill;
import enums.SkillType;
import skills.CombatHelper;
import statuses.Recklessness;

import java.io.Serializable;
import java.util.List;

public class FireBreath extends AreaSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }


    private static final int cost = 25;
    private static final SkillType skillType = SkillType.MAGICAL;
    private static final String name = "Fire Breath";
    private static final int range = 4;

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public int getRadius() {
        return 4;
    }

    @Override
    public String action(Creature source, List<Creature> targets)
    {
        String message = source.getName() + " breathed fire.";

        for(Creature target: targets)
        {
            int baseDamage = CombatHelper.magicDamage(source, target);
            int adjustedDamage = (int) Math.ceil(baseDamage * 1.3);
            target.changeStat(StatType.LIFE, adjustedDamage*-1);
            if(adjustedDamage >= 0)
            {
                message += target.getName() + " took " + adjustedDamage + " damage.";
            }
            else
            {
                message += target.getName() + " absorbed " + adjustedDamage*-1 + " damage.";
            }
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
