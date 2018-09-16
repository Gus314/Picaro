package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreen extends JPanel
{
    private IRoot root;

    public TitleScreen(IRoot inRoot)
    {
        root = inRoot;

        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new NewGameListener());
        add(newGameButton);

        JButton loadGameButton = new JButton("Load Game");
        loadGameButton.setEnabled(false);
        add(loadGameButton);

        JButton highScoresButton = new JButton("High Scores");
        highScoresButton.setEnabled(false);
        add(highScoresButton);

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new QuitListener());
        add(quitButton);
    }

    class NewGameListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            root.changeToCharacterCreation();
        }
    }

    class QuitListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }
}
