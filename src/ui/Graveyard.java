package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graveyard extends JPanel
{
    private IRoot root;

    public Graveyard(IRoot inRoot)
    {
        root = inRoot;
        JLabel message = new JLabel("High scores go here.");
        add(message);
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(new OkListener());
        add(okButton);
    }

    class OkListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            root.changeToTitleScreen();
        }
    }
}
