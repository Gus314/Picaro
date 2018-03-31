package ui.spells;

import ui.Actions;
import ui.MainWindow;
import ui.MapDisplay;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpellsButton extends JButton
{
    private MainWindow mainWindow;

    public SpellsButton(MainWindow inMainWindow)
    {
        this.setText("Spells");
        this.addActionListener(new SpellsListener(this));
        mainWindow = inMainWindow;
    }


    public class SpellsListener implements ActionListener
    {
        private SpellsButton spellsButton;

        public SpellsListener(SpellsButton inSpellsButton)
        {
            spellsButton = inSpellsButton;
        }

        public void actionPerformed(ActionEvent ae)
        {
            mainWindow.showSpells();
        }
    }

}
