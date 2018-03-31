package ui.techniques;

import entities.Player;
import entities.skills.Skill;
import enums.SkillType;
import ui.BackButton;
import ui.MainWindow;

import javax.swing.*;
import java.awt.*;

public class TechniquesDialog extends JPanel
{
    private Player player;
    private JPanel techniquesPanel;

    public TechniquesDialog(MainWindow mainWindow, Player inPlayer)
    {
        player = inPlayer;
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
            techniquesPanel.add(new JButton("Act"));
        }
    }
}
