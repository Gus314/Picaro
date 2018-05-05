package ui.mainwindow;

import control.Options;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsPanel extends JPanel
{
    private Options options;
    private JCheckBox autoPickupBox;

    public OptionsPanel(Options inOptions)
    {
        super();
        options = inOptions;
        this.add(new JLabel("Options:"));
        autoPickupBox = new JCheckBox("Auto Pickup");
        autoPickupBox.addActionListener(new AutoPickupListener());
        this.add(autoPickupBox);
    }

    class AutoPickupListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
           // TODO: Could these fall out of sync on save/load?
           options.setAutoPickup(!options.getAutoPickup());
            getParent().getParent().getParent().requestFocus();
        }
    }
}
