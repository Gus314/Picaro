package ui.actions;

import enums.MapDisplayMode;
import ui.MapDisplay;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExamineButton extends JButton
{
    private MapDisplay mapDisplay;

    public ExamineButton(MapDisplay inMapDisplay)
    {
        this.setText("Examine");
        this.addActionListener(new ExamineListener(this));
        mapDisplay = inMapDisplay;
    }


    public class ExamineListener implements ActionListener
    {
        private ExamineButton examineButton;

        public ExamineListener(ExamineButton inExamineButton)
        {
            examineButton = inExamineButton;
        }

        public void actionPerformed(ActionEvent ae)
        {
            mapDisplay.changeMode(MapDisplayMode.EXAMINE);
        }
    }

}
