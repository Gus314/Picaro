package ui.skills;

import entities.Player;
import entities.skills.SelfSkill;
import ui.Messages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelfTargetListener implements ActionListener
{
    private Player player;
    private SelfSkill skill;
    private Messages messages;

    public SelfTargetListener(Player inPlayer, SelfSkill inSkill, Messages inMessages)
    {
        player = inPlayer;
        skill = inSkill;
        messages = inMessages;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        messages.addMessage(skill.action(player));
    }
}

