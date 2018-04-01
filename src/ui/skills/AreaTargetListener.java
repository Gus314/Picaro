package ui.skills;

import skills.AreaSkill;
import enums.MapDisplayMode;
import ui.MapDisplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AreaTargetListener implements ActionListener
{
    private AreaSkill skill;
    private MapDisplay mapDisplay;

    public AreaTargetListener(AreaSkill inSkill, MapDisplay inMapDisplay)
    {
        skill = inSkill;
        mapDisplay = inMapDisplay;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        mapDisplay.setSelectedSkill(skill);
        mapDisplay.changeMode(MapDisplayMode.AREA);
    }
}
