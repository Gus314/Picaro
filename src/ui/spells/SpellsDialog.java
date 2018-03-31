package ui.spells;

import entities.Player;
import entities.skills.AreaSkill;
import entities.skills.SelfSkill;
import entities.skills.Skill;
import entities.skills.TargetSkill;
import enums.SkillType;
import ui.BackButton;
import ui.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public class SelfTargetListener implements ActionListener
    {
        private Player player;
        private SelfSkill skill;

        public SelfTargetListener(Player inPlayer, SelfSkill inSkill)
        {
           player = inPlayer;
           skill = inSkill;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
           System.out.println(skill.action(player));
        }
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
            spellsPanel.add(castButton);

            if(skill instanceof SelfSkill)
            {
                castButton.addActionListener(new SelfTargetListener(player, (SelfSkill)skill));
            }
            else if(skill instanceof TargetSkill)
            {
                System.out.println("Todo: TargetSkill");
            }
            else if(skill instanceof AreaSkill)
            {
                System.out.println("Todo: AreaSkill");
            }
            else
            {
                // TODO: Throw an exception.
                System.out.println("SpellsDialog::unexpected skill type.");
            }
        }
    }
}
