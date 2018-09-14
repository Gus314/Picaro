package pclasses;

import entities.factories.MonsterFactory;
import skills.player.CallEnemy;
import skills.player.Fireball;
import skills.player.Heal;
import skills.player.PoisonDart;
import skills.Skill;

import java.util.*;

public class PClassProvider
{
    public List<Pclass> getPClasses()
    {
        List<Pclass> result = new ArrayList<Pclass>();

        Map<Integer, Skill> hunterSkills = new HashMap<Integer, Skill>();
        hunterSkills.put(1, new PoisonDart());
        result.add(new Pclass("Hunter", hunterSkills));

        Map<Integer, Skill> mageSkills = new HashMap<Integer, Skill>();
        mageSkills.put(1, new Heal());
        Collection<Skill> skills = new ArrayList<Skill>();
        MonsterFactory call = (new MonsterFactory('D',22,0,2,0,11,0,0,1,"dummy", null, null, 15,20,0,19,18, skills,2,4));;
        mageSkills.put(1, new CallEnemy(call));

        mageSkills.put(2, new Fireball());
        result.add(new Pclass("Mage", mageSkills));

       return result;
    }
}
