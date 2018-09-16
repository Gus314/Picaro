package ui;

import control.PlayerInitialData;
import ui.mainwindow.MainWindow;

import java.applet.Applet;

public class RootApplet extends Applet implements IRoot
{
    public void init()
    {
        mainWindow = new MainWindow(this);
        titleScreen = new TitleScreen(this);
        characterCreation = new CharacterCreation(this);
        setVisible(true);
        add(titleScreen);
    }

    private MainWindow mainWindow;
    private TitleScreen titleScreen;
    private CharacterCreation characterCreation;

    public void changeToMainWindow(PlayerInitialData playerInitialData)
    {
        removeAll();
        mainWindow.start(playerInitialData);
        add(mainWindow);
        repaint();
    }

    public void changeToTitleScreen()
    {
        removeAll();
        add(titleScreen);
        repaint();
    }

    public void changeToCharacterCreation()
    {
        removeAll();
        add(characterCreation);
        repaint();
    }
}
