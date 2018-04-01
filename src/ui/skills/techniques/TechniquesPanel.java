package ui.skills.techniques;

import entities.Player;
import skills.AreaSkill;
import skills.SelfSkill;
import skills.Skill;
import skills.TargetSkill;
import enums.SkillType;
import ui.BackButton;
import ui.MainWindow;
import ui.MapDisplay;
import ui.Messages;
import ui.skills.AreaTargetListener;
import ui.skills.SelfTargetListener;
import ui.skills.SingleTargetListener;

import javax.swing.*;
import java.awt.*;

public class TechniquesPanel extends JPanel
{
    private Player player;
    private JPanel techniquesPanel;
    private MapDisplay mapDisplay;
    private Messages messages;

    public TechniquesPanel(MainWindow mainWindow, Player inPlayer, MapDisplay inMapDisplay, Messages inMessages)
    {
        player = inPlayer;
        mapDisplay = inMapDisplay;
        messages = inMessages;
        this.setLayout(new GridLayout(2,1));
        techniquesPanel = new JPanel();
        this.add(techniquesPanel);
        this.add(new BackButton(mainWindow));
    }

    public void refresh()
    {
        techniquesPanel.removeAll();
        techniquesPanel.setLayout(new GridLayout(player.getTechniquesCount() + 1, 4));

        techniquesPanel.add(new JLabel("Name"));
        techniquesPanel.add(new JLabel("Cost"));
        techniquesPanel.add(new JLabel("Target"));
        techniquesPanel.add(new JLabel(" "));

        for (Skill skill : player.getSkills())
        {
            if(skill.getSkillType() != SkillType.PHYSICAL)
            {
                continue;
            }
            techniquesPanel.add(new JLabel(skill.getName()));
            Integer boxedCost = skill.getCost();
            techniquesPanel.add(new JLabel(boxedCost.toString()));
            techniquesPanel.add(new JLabel(skill.getTargetType().toString()));

            JButton actButton = new JButton("Act");
            if(skill.getCost() > player.getPhysicalPoints())
            {
                actButton.setEnabled(false);
            }
            techniquesPanel.add(actButton);

            if(skill instanceof SelfSkill)
            {
                actButton.addActionListener(new SelfTargetListener(player, (SelfSkill)skill, messages));
            }
            else if(skill instanceof TargetSkill)
            {
                actButton.addActionListener(new SingleTargetListener((TargetSkill) skill, mapDisplay));
            }
            else if(skill instanceof AreaSkill)
            {
                actButton.addActionListener(new AreaTargetListener((AreaSkill) skill, mapDisplay));
            }
            else
            {
                // TODO: Throw an exception.
                System.out.println("TechniquesPanel::unexpected skill type.");
            }
        }
    }
}
