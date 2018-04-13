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
        this.setLayout(new GridLayout(10,2));
        this.add(new AttackButton(mapDisplay));
        this.add(new ExamineButton(mapDisplay));
        this.add(new AscendDescendButton(turnHandler));
        this.add(new TechniquesButton(mainWindow));
        this.add(new SpellsButton(mainWindow));
        for(int i = 0; i < 16; i++)
        {
            this.add(new JButton("action " + i));
        }
    }
}
