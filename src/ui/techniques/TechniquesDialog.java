package ui.techniques;

import ui.BackButton;
import ui.MainWindow;

import javax.swing.*;
import java.awt.*;

public class TechniquesDialog extends JPanel
{
    public TechniquesDialog(MainWindow mainWindow)
    {
        this.setLayout(new GridLayout(2,1));
        this.add(new JLabel("Techniques Dialog Contents."));
        this.add(new BackButton(mainWindow));
    }
}
