package mapgeneration.data.providers;

import enums.LoadableSkill;
import skills.Skill;
import skills.SummonSkill;
import skills.monster.act1.*;

import java.util.HashMap;
import java.util.Map;

public class SkillProvider
{
    private Map<LoadableSkill, Skill> skills;

    public SkillProvider()
    {
        skills = new HashMap<LoadableSkill, Skill>();

        skills.put(LoadableSkill.REQUESTAID, new RequestAid("rat"));
        skills.put(LoadableSkill.BITE, new Bite());
        skills.put(LoadableSkill.ROLLUP, new RollUp());
        skills.put(LoadableSkill.FLEE, new Flee());
        skills.put(LoadableSkill.POISONFANG, new PoisonFang());
        skills.put(LoadableSkill.STING, new Sting());
        skills.put(LoadableSkill.SCRATCH, new Scratch());
        skills.put(LoadableSkill.DODGE, new Dodge());
        skills.put(LoadableSkill.KICK, new Kick());
        skills.put(LoadableSkill.LAUGH, new Laugh());
        skills.put(LoadableSkill.SOAR, new Soar());
        skills.put(LoadableSkill.BURYHEAD, new BuryHead());
        skills.put(LoadableSkill.GORE, new Gore());
        skills.put(LoadableSkill.SET, new Set());
        skills.put(LoadableSkill.DIG, new Dig());
        skills.put(LoadableSkill.MAUL, new Maul());
        skills.put(LoadableSkill.HOWL, new Howl());
        skills.put(LoadableSkill.ROAR, new Roar());
        skills.put(LoadableSkill.TRUMPET, new Trumpet());
        skills.put(LoadableSkill.CLAW, new Claw());

    }

    public Skill get(LoadableSkill skill)
    {
        return skills.get(skill);
    }

    public void populate(MonsterProvider monsterProvider)
    {
        for(Skill skill: skills.values())
        {
            if(skill instanceof SummonSkill)
            {
                SummonSkill summonSkill = (SummonSkill) skill;
                summonSkill.populate(monsterProvider);
            }
        }
    }
}