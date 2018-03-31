package ui.spells;

import ui.MapDisplay;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpellsButton extends JButton
{
    private MapDisplay mapDisplay;

    public SpellsButton(MapDisplay inMapDisplay)
    {
        this.setText("Spells");
        this.addActionListener(new SpellsListener(this));
        mapDisplay = inMapDisplay;
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
            System.out.println("Spells clicked.");
        }
    }

}
