package skills.player.warrior;

import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import enums.StatType;
import skills.TargetSkill;

public class Sunder extends TargetSkill
{
    private static final int intensity = 10;

    @Override
    public int getRange()
    {
        return 1;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        int defenseReduction = (target.getDefense() >= intensity) ? intensity : target.getDefense();
        target.changeStat(StatType.DEFENSE, defenseReduction * -1);
        subtractCost(source);
        return source.getName() + " sundered " + target.getName() + ", permanently reducing their defense by " + defenseReduction + ".";
    }

    @Override
    public int getCost()
    {
        return 8;
    }

    @Override
    public SkillType getSkillType()
    {
        return SkillType.PHYSICAL;
    }

    @Override
    public String getName()
    {
        return "Sunder";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.ATTACK;
    }

    @Override
    public String getDescription()
    {
        return "Permanently reduce target defense by " + intensity + ".";
    }
}
