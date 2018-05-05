package ui.actions;

import control.TurnHandler;
import ui.MainWindow;
import ui.MapDisplay;
import ui.skills.spells.SpellsButton;
import ui.skills.techniques.TechniquesButton;

import javax.swing.*;
import java.awt.*;

public class Actions extends JPanel
{
    public Actions(MapDisplay mapDisplay, MainWindow mainWindow, TurnHandler turnHandler)
    {
        this.setLayout(new GridLayout(2,3));
        this.add(new AttackButton(mapDisplay));
        this.add(new ExamineButton(mapDisplay));
        this.add(new PickupButton(turnHandler));
        this.add(new AscendDescendButton(turnHandler));
        this.add(new TechniquesButton(mainWindow));
        this.add(new SpellsButton(mainWindow));
    }
}
