package pclasses;

import skills.player.Fireball;
import skills.player.Heal;
import skills.player.PoisonDart;
import skills.Skill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        mageSkills.put(2, new Fireball());
        result.add(new Pclass("Mage", mageSkills));

       return result;
    }
}
