package control;

import control.PlayerInitialData;
import entities.Player;
import ui.RootFrame;
import ui.mainwindow.MainWindow;

import javax.swing.*;
import java.applet.Applet;

public class RootApplet extends Applet
{
    public void init()
    {
        RootFrame rootFrame = new RootFrame();
        rootFrame.changeToTitleScreen();
        add(rootFrame);
    }
}
