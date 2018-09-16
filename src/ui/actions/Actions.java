package ui.actions;

import control.TurnHandler;
import ui.mainwindow.MainWindow;
import ui.mainwindow.MapDisplay;
import ui.skills.spells.SpellsButton;
import ui.skills.techniques.TechniquesButton;

import javax.swing.*;
import java.awt.*;

public class Actions extends JPanel
{
    public Actions(MapDisplay mapDisplay, MainWindow mainWindow, TurnHandler turnHandler)
    {
        this.setLayout(new GridLayout(3,3));
        this.add(new AttackButton(mapDisplay));
        this.add(new ExamineButton(mapDisplay));
        this.add(new PickupButton(turnHandler));
        this.add(new AscendDescendButton(turnHandler));
        this.add(new TechniquesButton(mainWindow));
        this.add(new SpellsButton(mainWindow));
        this.add(new TitleScreenButton(mainWindow.getRoot()));
    }
}
