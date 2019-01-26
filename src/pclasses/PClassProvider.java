package pclasses;

import skills.Skill;
import skills.player.hunter.*;
import skills.player.mage.*;
import skills.player.warrior.*;

import java.util.*;

public class PClassProvider
{
    public List<Pclass> getPClasses()
    {
        List<Pclass> result = new ArrayList<Pclass>();
        result.add(getHunter());
        result.add(getMage());
        result.add(getWarrior());

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
        addSkill(warriorSkills, 10, new Sunder());
        addSkill(warriorSkills, 12, new Ready());
        addSkill(warriorSkills, 14, new Charge());
        addSkill(warriorSkills, 16, new PowerSurge());

        return new Pclass("Warrior", warriorSkills);
    }

    public Pclass getMage()
    {
        Map<Integer, Collection<Skill>> mageSkills = new HashMap<Integer, Collection<Skill>>();
        addSkill(mageSkills, 1, new Heal());
        addSkill(mageSkills, 1, new Fireball());
        addSkill(mageSkills, 1, new LightningBolt());
        addSkill(mageSkills, 2, new Ward());
        addSkill(mageSkills, 4, new Swap());
        addSkill(mageSkills, 6, new Pedagogue());
        addSkill(mageSkills, 8, new Tornado());
        addSkill(mageSkills, 10, new Research());
        addSkill(mageSkills, 12, new SpiritShift());
        addSkill(mageSkills, 14, new Skeptic());
        addSkill(mageSkills, 16, new Transfer());

        return new Pclass("Mage", mageSkills);
    }

    public Pclass getHunter()
    {
        Map<Integer, Collection<Skill>> hunterSkills = new HashMap<Integer, Collection<Skill>>();
        addSkill(hunterSkills, 1, new PoisonDart());
        addSkill(hunterSkills, 1, new Stab());
        addSkill(hunterSkills, 1, new Meditate());
        addSkill(hunterSkills, 2, new EyeThrust());
        addSkill(hunterSkills, 4, new HundredCuts());
        addSkill(hunterSkills, 6, new Immobilize());
        addSkill(hunterSkills, 8, new TrickShot());
        addSkill(hunterSkills, 10, new RiskTaker());
        addSkill(hunterSkills, 12, new Vanish());
        addSkill(hunterSkills, 14, new FindWeakpoint());
        addSkill(hunterSkills, 16, new DeathlyDance());

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
