package ui.spells;

import entities.Player;
import entities.skills.Skill;
import enums.SkillType;
import ui.BackButton;
import ui.MainWindow;

import javax.swing.*;
import java.awt.*;

public class SpellsDialog extends JPanel
{
    private Player player;
    private JPanel spellsPanel;

    public SpellsDialog(MainWindow mainWindow, Player inPlayer)
    {
        player = inPlayer;
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
            spellsPanel.add(new JButton("Cast"));
        }
    }
}
