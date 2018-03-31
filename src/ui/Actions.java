package ui;

import ui.skills.spells.SpellsButton;
import ui.skills.techniques.TechniquesButton;

import javax.swing.*;
import java.awt.*;

public class Actions extends JPanel
{
    public Actions(MapDisplay mapDisplay, MainWindow mainWindow)
    {
        this.setLayout(new GridLayout(10,2));
        this.add(new AttackButton(mapDisplay));
        this.add(new TechniquesButton(mainWindow));
        this.add(new SpellsButton(mainWindow));
        for(int i = 0; i < 17; i++)
        {
            this.add(new JButton("action " + i));
        }
    }
}
