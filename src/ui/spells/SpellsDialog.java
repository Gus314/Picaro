package ui.spells;

import ui.BackButton;
import ui.MainWindow;

import javax.swing.*;
import java.awt.*;

public class SpellsDialog extends JPanel
{
    public SpellsDialog(MainWindow mainWindow)
    {
        this.setLayout(new GridLayout(2,1));
        this.add(new JLabel("Spells Dialog Contents."));
        this.add(new BackButton(mainWindow));
    }
}
