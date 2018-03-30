package ui;

import javax.swing.*;
import java.awt.*;

public class Actions extends JPanel
{
    public Actions(MapDisplay mapDisplay)
    {
        this.setLayout(new GridLayout(10,2));
        this.add(new AttackButton(mapDisplay));
        for(int i = 0; i < 19; i++)
        {
            this.add(new JButton("action " + i));
        }
    }
}
