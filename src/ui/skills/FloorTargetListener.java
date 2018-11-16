package ui.skills;

import enums.MapDisplayMode;
import skills.FloorSkill;
import ui.IRefreshable;
import ui.mainwindow.MapDisplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FloorTargetListener implements ActionListener
{
    private FloorSkill skill;
    private MapDisplay mapDisplay;
    private IRefreshable refreshable;

    public FloorTargetListener(FloorSkill inSkill, MapDisplay inMapDisplay)
    {
        skill = inSkill;
        mapDisplay = inMapDisplay;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        mapDisplay.setSelectedSkill(skill);
        mapDisplay.changeMode(MapDisplayMode.FLOOR);
    }
}

