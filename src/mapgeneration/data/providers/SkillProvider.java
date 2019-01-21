package mapgeneration.data.providers;

import enums.LoadableSkill;
import skills.Skill;
import skills.SummonSkill;
import skills.VariedSummonSkill;
import skills.monster.act1.*;
import skills.monster.act2.*;
import skills.monster.act3.*;
import skills.monster.act4.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SkillProvider
{
    private Map<LoadableSkill, Skill> skills;

    public SkillProvider()
    {
        skills = new HashMap<LoadableSkill, Skill>();

        //act1
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

        //act2
        skills.put(LoadableSkill.BRAINS, new Brains());
        skills.put(LoadableSkill.BULLRUSH, new Bullrush());
        skills.put(LoadableSkill.CHARM, new Charm());
        skills.put(LoadableSkill.CHATTER, new Chatter());
        skills.put(LoadableSkill.CLUB, new Club());
        skills.put(LoadableSkill.FLURRY, new Flurry());
        skills.put(LoadableSkill.HAUNT, new Haunt());
        skills.put(LoadableSkill.HIDE, new Hide());
        skills.put(LoadableSkill.LIFEDRAIN, new LifeDrain());
        skills.put(LoadableSkill.MOCK, new Mock());
        skills.put(LoadableSkill.PESTILENCE, new Pestilence());

        Collection<String> summonNames = new ArrayList<String>();
        summonNames.add("skeleton");
        summonNames.add("zombie");
        skills.put(LoadableSkill.RAISE, new Raise(summonNames));

        summonNames.clear();
        summonNames.add("wooden groggy obelisk");
        summonNames.add("wooden naive obelisk");
        skills.put(LoadableSkill.RITUAL, new Ritual(summonNames));
        skills.put(LoadableSkill.SACRIFICE, new Sacrifice());
        skills.put(LoadableSkill.SAVAGE, new Savage());
        skills.put(LoadableSkill.SCREAM, new Scream());
        skills.put(LoadableSkill.SNIPE, new Snipe());
        skills.put(LoadableSkill.SWIPE, new Swipe());
        skills.put(LoadableSkill.WAIL, new Wail());
        skills.put(LoadableSkill.WRAP, new Wrap());

        //act3
        skills.put(LoadableSkill.PUNISH, new Punish());
        skills.put(LoadableSkill.LOSE, new Lose());
        skills.put(LoadableSkill.POUND, new Pound());
        skills.put(LoadableSkill.GAZE, new Gaze());
        skills.put(LoadableSkill.RIDDLE, new Riddle());
        skills.put(LoadableSkill.GUST, new Gust());
        skills.put(LoadableSkill.SPINES, new Spines());
        skills.put(LoadableSkill.MOLTENBITE, new MoltenBite());
        skills.put(LoadableSkill.BACKHOOF, new BackHoof());
        skills.put(LoadableSkill.SKEWER, new Skewer());
        skills.put(LoadableSkill.TEAR, new Tear());
        skills.put(LoadableSkill.TRAMPLE, new Trample());
        skills.put(LoadableSkill.CONSUME, new Consume());
        skills.put(LoadableSkill.LURE, new Lure());
        skills.put(LoadableSkill.WEB, new Web());
        skills.put(LoadableSkill.RISE, new Rise());
        skills.put(LoadableSkill.WINDUP, new WindUp());
        skills.put(LoadableSkill.REGROW, new Regrow());
        skills.put(LoadableSkill.SPROUT, new Sprout());
        skills.put(LoadableSkill.FIREBREATH, new FireBreath());

        //act4
        skills.put(LoadableSkill.TSUNAMI, new Tsunami());
        skills.put(LoadableSkill.HELLFIRE, new Hellfire());
        skills.put(LoadableSkill.REWIND, new Rewind());
        skills.put(LoadableSkill.THUNDERBOLT, new Thunderbolt());
        skills.put(LoadableSkill.DRINK, new Drink());
        summonNames.clear();
        summonNames.add("poseidon");
        summonNames.add("hades");
        summonNames.add("kronos");
        summonNames.add("zeus");
        summonNames.add("bacchus");
        skills.put(LoadableSkill.RESURRECT, new Resurrect(summonNames));
        skills.put(LoadableSkill.PROTECTION, new Protection());
        skills.put(LoadableSkill.SANDSTORM, new Sandstorm());
        skills.put(LoadableSkill.DOOM, new Doom());
        skills.put(LoadableSkill.INSPIRE, new Inspire());
        skills.put(LoadableSkill.DESTROY, new Destroy());
        skills.put(LoadableSkill.CLEANSE, new Cleanse());
        skills.put(LoadableSkill.PROVIDE, new Provide());
        skills.put(LoadableSkill.TRANSFORM, new Transform());
        skills.put(LoadableSkill.STORM, new Storm());
        skills.put(LoadableSkill.RULE, new Rule());
        skills.put(LoadableSkill.CLEAVE, new Cleave());
        skills.put(LoadableSkill.CHANT, new Chant());
        skills.put(LoadableSkill.COMMAND, new Command());
        skills.put(LoadableSkill.REPOSITION, new Reposition());
    }

    public Skill get(LoadableSkill skill)
    {
        return skills.get(skill);
    }

    public void populate(Collection<EntityProvider> entityProviders)
    {
        for(Skill skill: skills.values())
        {
            if(skill instanceof SummonSkill)
            {
                SummonSkill summonSkill = (SummonSkill) skill;
                summonSkill.populate(entityProviders);
            }
            else if(skill instanceof VariedSummonSkill)
            {
                VariedSummonSkill variedSummonSkill = (VariedSummonSkill) skill;
                variedSummonSkill.populate(entityProviders);
            }
        }
    }
}