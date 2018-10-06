package ui.actions;

import control.DungeonManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveButton extends JButton
{
    private DungeonManager dungeonManager;

    public SaveButton(DungeonManager inDungeonManager)
    {
        dungeonManager = inDungeonManager;
        setText("Save");
        addActionListener(new SaveButton.SaveListener());
    }

    class SaveListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            dungeonManager.save();
            JOptionPane.showMessageDialog(getParent(), "Game saved.");
        }
    }
}
