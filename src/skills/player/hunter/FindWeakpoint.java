package skills.player.hunter;

import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.TargetSkill;
import statuses.Exposed;

public class FindWeakpoint extends TargetSkill
{
    @Override
    public int getRange()
    {
        return 1;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        Exposed exposed = new Exposed(target, 5);
        target.addStatusEffect(exposed);

        subtractCost(source);
        return source.getName() + " finds a weakpoint on " + target.getName();
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
        return "Find weakpoint";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.ATTACK;
    }

    @Override
    public String getDescription()
    {
        return "Expose a weakpoint on target.";
    }
}
