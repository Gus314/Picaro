package ui.actions;

import control.TurnHandler;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AscendDescendButton extends JButton
{
    private TurnHandler turnHandler;

    public AscendDescendButton(TurnHandler inTurnHandler)
    {
        this.setText("Ascend / Descend");
        this.addActionListener(new AscendDescendButton.AscendDescendListener());
        turnHandler = inTurnHandler;
    }


    public class AscendDescendListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            turnHandler.changeLevel();
            turnHandler.update();
            getParent().getParent().getParent().requestFocus();
        }
    }
}
