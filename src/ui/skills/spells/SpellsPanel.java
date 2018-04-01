package ui.skills.spells;

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

public class SpellsPanel extends JPanel
{
    private Player player;
    private JPanel spellsPanel;
    private MapDisplay mapDisplay;
    private Messages messages;

    public SpellsPanel(MainWindow mainWindow, Player inPlayer, MapDisplay inMapDisplay, Messages inMessages)
    {
        player = inPlayer;
        mapDisplay = inMapDisplay;
        messages = inMessages;
        this.setLayout(new GridLayout(2,1));
        spellsPanel = new JPanel();
        this.add(spellsPanel);
        this.add(new BackButton(mainWindow));
    }


    public void refresh()
    {
        spellsPanel.removeAll();
        spellsPanel.setLayout(new GridLayout(player.getSpellsCount() + 1, 4));

        spellsPanel.add(new JLabel("Name"));
        spellsPanel.add(new JLabel("Cost"));
        spellsPanel.add(new JLabel("Target"));
        spellsPanel.add(new JLabel(" "));

        for (Skill skill : player.getSkills())
        {
            if(skill.getSkillType() != SkillType.MAGICAL)
            {
                continue;
            }
            spellsPanel.add(new JLabel(skill.getName()));
            Integer boxedCost = skill.getCost();
            spellsPanel.add(new JLabel(boxedCost.toString()));
            spellsPanel.add(new JLabel(skill.getTargetType().toString()));

            JButton castButton = new JButton("Cast");
            if(skill.getCost() > player.getMagicPoints())
            {
                castButton.setEnabled(false);
            }
            spellsPanel.add(castButton);

            if(skill instanceof SelfSkill)
            {
                castButton.addActionListener(new SelfTargetListener(player, (SelfSkill)skill, messages, mapDisplay));
            }
            else if(skill instanceof TargetSkill)
            {
                castButton.addActionListener(new SingleTargetListener((TargetSkill) skill, mapDisplay));
            }
            else if(skill instanceof AreaSkill)
            {
                castButton.addActionListener(new AreaTargetListener((AreaSkill) skill, mapDisplay));
            }
            else
            {
                // TODO: Throw an exception.
                System.out.println("SpellsPanel::unexpected skill type.");
            }
        }
    }
}
