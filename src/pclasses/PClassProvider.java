package pclasses;

import entities.factories.MonsterFactory;
import enums.Faction;
import skills.player.*;
import skills.Skill;

import java.util.*;

public class PClassProvider
{
    public List<Pclass> getPClasses()
    {
        List<Pclass> result = new ArrayList<Pclass>();

        Map<Integer, Collection<Skill>> hunterSkills = new HashMap<Integer, Collection<Skill>>();
        addSkill(hunterSkills, 1, new PoisonDart());
        addSkill(hunterSkills, 1, new Stab());
        //result.add(new Pclass("Hunter", hunterSkills));

        Map<Integer, Collection<Skill>> warriorSkills = new HashMap<Integer, Collection<Skill>>();
        addSkill(warriorSkills, 1, new Bash());
        addSkill(warriorSkills, 1, new Toughen());
        result.add(new Pclass("Warrior", warriorSkills));

        Map<Integer, Collection<Skill>> mageSkills = new HashMap<Integer, Collection<Skill>>();
        addSkill(mageSkills, 1, new Heal());
        Collection<Skill> skills = new ArrayList<Skill>();
        addSkill(mageSkills, 2, new Fireball());
        //result.add(new Pclass("Mage", mageSkills));
        return result;
    }

    public void addSkill(Map<Integer, Collection<Skill>> classSkills, int level, Skill skill)
    {
        if(!classSkills.containsKey(level))
        {
            classSkills.put(level, new ArrayList<Skill>());
        }

        classSkills.get(level).add(skill);
    }
}
