package ui;

import control.PlayerInitialData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharacterCreation extends JPanel
{
    private RootFrame rootFrame;
    private JTextField nameField;


    public CharacterCreation(RootFrame inRootFrame)
    {
        rootFrame = inRootFrame;
        add(new JLabel("Character Creation."));

        add(new JLabel("Name:"));
        nameField = new JTextField("Nameo");
        add(nameField);

        JButton startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(new StartGameListener());
        add(startGameButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new CancelListener());
        add(cancelButton);
    }

    class StartGameListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            int choice = JOptionPane.showConfirmDialog(getParent(), "Are you sure?", "Create character", JOptionPane.YES_NO_OPTION);

            if(choice == JOptionPane.YES_OPTION)
            {
                PlayerInitialData playerInitialData = new PlayerInitialData(nameField.getText());
                rootFrame.changeToMainWindow(playerInitialData);
            }
        }
    }

    class CancelListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            rootFrame.changeToTitleScreen();
        }
    }
}
