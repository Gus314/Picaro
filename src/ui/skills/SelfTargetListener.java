package ui.skills;

import entities.Player;
import enums.MapDisplayMode;
import skills.SelfSkill;
import ui.mainwindow.MapDisplay;
import ui.mainwindow.Messages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelfTargetListener implements ActionListener
{
    private Player player;
    private SelfSkill skill;
    private Messages messages;
    private MapDisplay mapDisplay;

    public SelfTargetListener(Player inPlayer, SelfSkill inSkill, Messages inMessages, MapDisplay inMapDisplay)
    {
        player = inPlayer;
        skill = inSkill;
        messages = inMessages;
        mapDisplay = inMapDisplay;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        messages.addMessage(skill.action(player));
        mapDisplay.changeMode(MapDisplayMode.NORMAL);
        mapDisplay.getMap().takeTurns();
    }
}

