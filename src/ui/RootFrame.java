package ui;

import control.PlayerInitialData;
import entities.Player;
import ui.mainwindow.MainWindow;

import javax.swing.*;

public class RootFrame extends JFrame
{
    private MainWindow mainWindow;
    private TitleScreen titleScreen;
    private CharacterCreation characterCreation;

    public RootFrame()
    {
        mainWindow = new MainWindow(this);
        titleScreen = new TitleScreen(this);
        characterCreation = new CharacterCreation(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void changeToMainWindow(PlayerInitialData playerInitialData)
    {
        mainWindow.start(playerInitialData);
        setContentPane(mainWindow);
        pack();
    }

    public void changeToTitleScreen()
    {
        setContentPane(titleScreen);
        pack();
    }

    public void changeToCharacterCreation()
    {
        setContentPane(characterCreation);
        pack();
    }
}
