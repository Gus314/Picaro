package ui.shortcuts;

import entities.creatures.Player;
import enums.EnumConversions;
import enums.MapDisplayMode;
import skills.SelfSkill;
import skills.Skill;
import ui.mainwindow.MapDisplay;
import ui.mainwindow.Messages;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Shortcuts extends JPanel
{
    private Map<Integer, Skill> shortcuts;

    public Shortcuts(Player player, Messages messages, MapDisplay mapDisplay)
    {
        shortcuts = new HashMap<Integer, Skill>();

        for(int i = 1; i < 10; i++)
        {
            Integer number = i;
            JButton shortcutButton = new JButton(number.toString());
            shortcutButton.setToolTipText("Shortcut not set");
            shortcutButton.addMouseListener(new ShortcutListener(getRootPane(), player, number, shortcuts, messages, mapDisplay, shortcutButton));
            add(shortcutButton);
        }
    }

    public class ShortcutListener implements MouseInputListener
    {
        private Component parent;
        private Player player;
        private int number;
        private Map<Integer, Skill> shortcuts;
        private Messages messages;
        private MapDisplay mapDisplay;
        private JButton button;
        private static final String message = "Please select a skill to shortcut.";
        private static final String title = "Shortcut selection";

        public ShortcutListener(Component inParent, Player inPlayer, int inNumber, Map<Integer, Skill> inShortcuts, Messages inMessages, MapDisplay inMapDisplay, JButton inButton)
        {
            parent = inParent;
            player = inPlayer;
            number = inNumber;
            shortcuts = inShortcuts;
            messages = inMessages;
            mapDisplay = inMapDisplay;
            button = inButton;
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            if(e.getButton() == MouseEvent.BUTTON3)
            {
                selectShortcut();
            }
            else if(e.getButton() == MouseEvent.BUTTON1)
            {
                useShortcut();
            }
        }

        private void useShortcut()
        {
            Skill skill = shortcuts.get(number);

            if(skill == null)
            {
                // No skill selected.
                mapDisplay.getParent().getParent().requestFocus();
                return;
            }

            if(skill instanceof SelfSkill)
            {
                messages.addMessage(((SelfSkill)skill).action(player));
                mapDisplay.changeMode(MapDisplayMode.NORMAL);
                mapDisplay.getMap().takeTurns();
            }
            else
            {
                mapDisplay.setSelectedSkill(skill);
                mapDisplay.changeMode(EnumConversions.toMapDisplayMode(skill.getTargetType()));
            }
        }


        private void selectShortcut()
        {
            button.setToolTipText("Shortcut not set");

            Collection<String> skillNames = new ArrayList<String>();

            for(Skill skill: player.getSkills())
            {
                skillNames.add(skill.getName());
            }

            Object[] selectionValues = skillNames.toArray();
            Object selection =  JOptionPane.showInputDialog(parent, message, title, JOptionPane.PLAIN_MESSAGE, null, selectionValues, selectionValues[0]);

            if(selection != null)
            {
                String skillName = (String) selection;
                for(Skill skill: player.getSkills())
                {
                    if (skill.getName() == skillName)
                    {
                        shortcuts.put(number, skill);
                        button.setToolTipText(skill.getName());
                        break;
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

        @Override
        public void mouseDragged(MouseEvent e) {}
        @Override
        public void mouseMoved(MouseEvent e) {}
    }
}
