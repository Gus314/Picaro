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
        revalidate();
        repaint();
    }

    private MainWindow mainWindow;
    private TitleScreen titleScreen;
    private CharacterCreation characterCreation;

    public void changeToMainWindow(PlayerInitialData playerInitialData)
    {
        removeAll();
        mainWindow.start(playerInitialData);
        add(mainWindow);
        revalidate();
        repaint();
    }

    public void changeToTitleScreen()
    {
        removeAll();
        add(titleScreen);
        revalidate();
        repaint();
    }

    public void changeToCharacterCreation()
    {
        removeAll();
        add(characterCreation);
        revalidate();
        repaint();
    }
}
