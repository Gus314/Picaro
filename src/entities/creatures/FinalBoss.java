package entities.creatures;

import control.Map;
import enums.Faction;
import enums.LoadableSkill;
import mapgeneration.data.providers.SkillProvider;
import skills.Skill;
import ui.mainwindow.Messages;

import java.util.ArrayList;

public class FinalBoss extends Monster
{
    public FinalBoss(int inRow, int inColumn, Map inMap, Messages inMessages, int level)
    {
        super('P', inRow, inColumn, inMap, inMessages, Faction.FOE, 144, "Lord Picaro", 1, 432*8, 300, 317, 10, 5, 0, 1, 337,
                177, 177, 177, 177, 317, 317, new ArrayList<Skill>(), level, level);

        // Give final boss all loadable skills.
        SkillProvider skillProvider = new SkillProvider();
        for(LoadableSkill loadableSkill: LoadableSkill.values())
        {
            getSkills().add(skillProvider.get(loadableSkill));
        }
    }

    public void onDeath()
    {
        getMessages().addMessage("Game won, good job chaps!");
    }
}
