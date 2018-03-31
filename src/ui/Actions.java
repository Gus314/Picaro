package ui;

import ui.spells.SpellsButton;
import ui.techniques.TechniquesButton;

import javax.swing.*;
import java.awt.*;

public class Actions extends JPanel
{
    public Actions(MapDisplay mapDisplay)
    {
        this.setLayout(new GridLayout(10,2));
        this.add(new AttackButton(mapDisplay));
        this.add(new TechniquesButton(mapDisplay));
        this.add(new SpellsButton(mapDisplay));
        for(int i = 0; i < 17; i++)
        {
            this.add(new JButton("action " + i));
        }
    }
}
