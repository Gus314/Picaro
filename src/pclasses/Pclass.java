package pclasses;

import entities.Player;
import skills.Skill;
import java.util.Map;

public class Pclass
{
    private String name;
    private Map<Integer, Skill> skills;

    public Pclass(String inName, Map<Integer, Skill> inSkills)
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
            player.addSkill(skills.get(1));
        }
    }

    public void levelUp(Player player)
    {
        if(skills.containsKey(player.getLevel()))
        {
            player.addSkill(skills.get(player.getLevel()));
        }
    }
}
