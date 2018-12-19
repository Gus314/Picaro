package statuses;

import entities.creatures.Creature;
import enums.StatType;
import skills.Skill;

import java.io.Serializable;

// Note that the effect of blindness is dealt with using line of sight checks.
public class Learn extends StatusEffect implements Serializable
{
    private static final String name = "Learn";
    private static final String description = "Learn a skill.";
    private boolean previouslyKnown; // May attempt to learn an already known skill.
    private Skill skill;

    public Learn(Learn original)
    {
        super(original);
        skill = original.skill;
        previouslyKnown = original.previouslyKnown;
    }

    public Learn(Creature inTarget, Skill inSkill)
    {
        super(name, description, inTarget);
        skill = inSkill;
    }

    public @Override String action()
    {
        return "";
    }

    @Override
    public String onApplication()
    {
        if(getTarget().getSkills().stream().anyMatch(s -> s.getName() == skill.getName()))
        {
            previouslyKnown = true;
            return getTarget().getName() + " attempted to learn " + skill.getName() + " but found they already knew it.";
        }
        else
        {
            getTarget().getSkills().add(skill);
            return getTarget().getName() + " has learnt " + skill.getName() + ".";
        }
    }

    @Override
    public String onRemoval()
    {
        if(!previouslyKnown)
        {
            getTarget().getSkills().remove(skill);
            return getTarget().getName() + " has no longer learnt " + skill.getName() + ".";
        }
        else
        {
            return "";
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Learn cloned = (Learn) super.clone();
        cloned.skill = skill;
        cloned.previouslyKnown = previouslyKnown;
        return cloned;
    }
}
