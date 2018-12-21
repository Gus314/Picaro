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
        String helpText = "Your character is represented by the @ symbol.\n";
        helpText += "To move, use the number pad.\n";
        helpText += "Select actions with a left-click and cancel actions with a right-click.\n";
        helpText += "Monsters can be melee attacked simply by walking into them.\n";
        helpText += "The buttons numbered 1-9 can be right-clicked to set a shortcut and left-clicked to use it.\n";
        helpText += "The display is colour-coded. Red signifies enemies, green friends, cyan usable furniture and yellow items that can be picked up.\n";
        helpText += "> and < signify down and up stairs, respectively.\n";
        helpText += "Furniture and stairs can be used by standing on them and pressing use or ascend/descend as appropriate.\n";
        helpText += "Finally note that 1080p is currently the ideal resolution for playing.\n";
        helpText += "Thank you once again for playing, a proper tutorial will be added in the fullness of time.\n";

        controls.setText(helpText);
        controls.setEditable(false);
        add(controls);

        JTextArea about = new JTextArea();
        about.setText("Picaro: Version 0.1. Created by Gus314.");
        about.setEditable(false);
        add(about);
    }
}
