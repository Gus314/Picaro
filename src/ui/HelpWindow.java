package ui;

import javax.swing.*;
import java.awt.*;

public class HelpWindow extends JPanel
{
    public HelpWindow()
    {
        this.setLayout(new GridLayout(2,1));
        displayHelp();
    }

    private void displayHelp()
    {
        JTextArea controls = new JTextArea();
        controls.setText("Your character is represented by the @ symbol. To move, use the number pad. Select actions with a left-click and cancel actions with a right-click.");
        controls.setEditable(false);
        add(controls);

        JTextArea about = new JTextArea();
        about.setText("Picaro: Version 0.1. Created by Fergus Reid.");
        about.setEditable(false);
        add(about);
    }
}
