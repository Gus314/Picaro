package ui.actions;

import ui.IRoot;
import ui.RootFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreenButton extends JButton
{
    private IRoot root;

    public TitleScreenButton(IRoot inRoot)
    {
        root = inRoot;
        setText("Return to Title Screen");
        addActionListener(new TitleScreenListener());
    }

    class TitleScreenListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
           int choice = JOptionPane.showConfirmDialog(getParent(), "Are you sure?", "Return to title screen", JOptionPane.YES_NO_OPTION);

           if(choice == JOptionPane.YES_OPTION)
           {
               root.changeToTitleScreen();
           }
        }
    }
}
