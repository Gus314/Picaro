package ui;

import control.PlayerInitialData;
import pclasses.PClassProvider;
import pclasses.Pclass;
import races.Race;
import races.RaceProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharacterCreation extends JPanel
{
    private IRoot root;
    private JTextField nameField;
    private JComboBox raceBox;
    private JComboBox classBox;

    public CharacterCreation(IRoot inRoot)
    {
        root = inRoot;
        add(new JLabel("Name:"));
        nameField = new JTextField("Sampson");
        add(nameField);

        add(new JLabel("Race"));
        raceBox = new JComboBox();
        RaceProvider raceProvider = new RaceProvider();
        for(Race race: raceProvider.getRaces())
        {
            raceBox.addItem(race);
        }
        add(raceBox);

        add(new JLabel("Class"));
        classBox = new JComboBox();
        PClassProvider pclassProvider = new PClassProvider();
        for(Pclass pclass: pclassProvider.getPClasses())
        {
            classBox.addItem(pclass);
        }
        add(classBox);

        JButton startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(new StartGameListener());
        add(startGameButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new CancelListener());
        add(cancelButton);

        setLayout(new GridLayout(4, 2));
    }

    class StartGameListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            int choice = JOptionPane.showConfirmDialog(getParent(), "Are you sure?", "Create character", JOptionPane.YES_NO_OPTION);

            if(choice == JOptionPane.YES_OPTION)
            {
                Race race = (Race) raceBox.getSelectedItem();
                Pclass pclass = (Pclass) classBox.getSelectedItem();
                PlayerInitialData playerInitialData = new PlayerInitialData(nameField.getText(), race, pclass);
                root.changeToMainWindow(playerInitialData);
            }
        }
    }

    class CancelListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            root.changeToTitleScreen();
        }
    }
}
