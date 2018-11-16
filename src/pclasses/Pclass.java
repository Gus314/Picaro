package pclasses;

import entities.creatures.Player;
import skills.Skill;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

public class Pclass implements Serializable
{
    private String name;
    private Map<Integer, Collection<Skill>> skills;

    public Pclass(String inName, Map<Integer, Collection<Skill>> inSkills)
    {
        name = inName;
        skills = inSkills;
    }

    public @Override String toString(){return name;}

    public String getName(){return name;}

    public void initialise(Player player)
    {
        if(skills.containsKey(1))
        {
            for(Skill skill: skills.get(1))
            {
                player.addSkill(skill);
            }
        }
    }

    public void levelUp(Player player)
    {
        if(skills.containsKey(player.getLevel()))
        {
            for(Skill skill: skills.get(player.getLevel()))
            {
                player.addSkill(skill);
            }
        }
    }
}
