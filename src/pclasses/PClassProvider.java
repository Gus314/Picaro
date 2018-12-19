package pclasses;

import skills.Skill;
import skills.player.hunter.PoisonDart;
import skills.player.hunter.Stab;
import skills.player.mage.Fireball;
import skills.player.mage.Heal;
import skills.player.warrior.*;

import java.util.*;

public class PClassProvider
{
    public List<Pclass> getPClasses()
    {
        List<Pclass> result = new ArrayList<Pclass>();
        result.add(getWarrior());
        // result.add(getMage());
        // result.add(getHunter());

        return result;
    }

    public Pclass getWarrior()
    {
        Map<Integer, Collection<Skill>> warriorSkills = new HashMap<Integer, Collection<Skill>>();
        addSkill(warriorSkills, 1, new Bash());
        addSkill(warriorSkills, 1, new Toughen());
        addSkill(warriorSkills, 1, new Dash());
        addSkill(warriorSkills, 2, new Daze());
        addSkill(warriorSkills, 4, new Relax());
        addSkill(warriorSkills, 6, new Whirlwind());
        addSkill(warriorSkills, 8, new Bulwark());
        addSkill(warriorSkills, 10, new Toughen());
        addSkill(warriorSkills, 12, new Ready());
        addSkill(warriorSkills, 14, new Charge());
        addSkill(warriorSkills, 16, new PowerSurge());

        return new Pclass("Warrior", warriorSkills);
    }

    public Pclass getMage()
    {
        Map<Integer, Collection<Skill>> mageSkills = new HashMap<Integer, Collection<Skill>>();
        addSkill(mageSkills, 1, new Heal());
        Collection<Skill> skills = new ArrayList<Skill>();
        addSkill(mageSkills, 2, new Fireball());
        return new Pclass("Mage", mageSkills);
    }

    public Pclass getHunter()
    {
        Map<Integer, Collection<Skill>> hunterSkills = new HashMap<Integer, Collection<Skill>>();
        addSkill(hunterSkills, 1, new PoisonDart());
        addSkill(hunterSkills, 1, new Stab());
        return new Pclass("Hunter", hunterSkills);
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
