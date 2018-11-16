package entities.creatures;

import control.Map;
import enums.Faction;
import skills.Skill;
import ui.mainwindow.Messages;

import java.util.ArrayList;

public class FinalBoss extends Monster
{
    public FinalBoss(int inRow, int inColumn, Map inMap, Messages inMessages, int level)
    {
        super('P', inRow, inColumn, inMap, inMessages, Faction.FOE, 0, "Lord Picaro", 1, 1, 1, 1, 0, 0, 0, 1, 0,
                0, 0, 0, 0, 0, 0, new ArrayList<Skill>(), level, level);
    }

    public void onDeath()
    {
        getMessages().addMessage("Game won, good job chaps!");
    }
}
