package ui.skills.techniques;

import ui.mainwindow.MainWindow;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TechniquesButton extends JButton
{
    private MainWindow mainWindow;

    public TechniquesButton(MainWindow inMainWindow)
    {
        this.setText("Techniques");
        this.addActionListener(new TechniquesListener(this));
        mainWindow = inMainWindow;
    }


    public class TechniquesListener implements ActionListener
    {
        private TechniquesButton techniquesButton;

        public TechniquesListener(TechniquesButton inTechniquesButton)
        {
            techniquesButton = inTechniquesButton;
        }

        public void actionPerformed(ActionEvent ae)
        {
            mainWindow.showTechniques();
        }
    }
}
