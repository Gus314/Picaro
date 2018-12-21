package ui;

import control.Grave;
import control.PlayerInitialData;
import ui.mainwindow.MainWindow;

import javax.swing.*;
import java.awt.*;

public class RootFrame extends JFrame implements IRoot
{
    private MainWindow mainWindow;
    private TitleScreen titleScreen;
    private CharacterCreation characterCreation;
    private GameOver gameOver;
    private Graveyard graveyard;

    public RootFrame()
    {
        mainWindow = new MainWindow(this);
        titleScreen = new TitleScreen(this);
        characterCreation = new CharacterCreation(this);
        gameOver = new GameOver(this);
        graveyard = new Graveyard(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void maximise()
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        setSize(toolkit.getScreenSize());
    }

    public void changeToMainWindow(PlayerInitialData playerInitialData)
    {
        mainWindow.start(playerInitialData);
        setContentPane(mainWindow);
        pack();
        maximise();
    }

    public void changeToMainWindow()
    {
        mainWindow.start();
        setContentPane(mainWindow);
        pack();
        maximise();
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

    @Override
    public void changeToGameOver(Grave grave)
    {
        gameOver.setGrave(grave);
        setContentPane(gameOver);
        pack();
    }

    @Override
    public void changeToGraveyard()
    {
        graveyard.refresh();
        setContentPane(graveyard);
        pack();
    }
}
