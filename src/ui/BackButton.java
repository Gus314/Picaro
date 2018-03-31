package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackButton extends JButton
{
    private MainWindow mainWindow;

    public BackButton(MainWindow inMainWindow)
    {
        this.setText("Back");
        this.addActionListener(new BackButtonListener(this));
        mainWindow = inMainWindow;
    }

    public class BackButtonListener implements ActionListener
    {
        private BackButton backButton;

        public BackButtonListener(BackButton inBackButton)
        {
            backButton = inBackButton;
        }

        public void actionPerformed(ActionEvent ae) {
            mainWindow.showActions();
        }
    }
}
