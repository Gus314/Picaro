package ui;

import control.Grave;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver extends JPanel
{
    private IRoot root;
    private JTable graveTable;
    private final String[] columnNames = {"Name", "Class", "Race", "Level", "Cause of Death"};

    public GameOver(IRoot inRoot)
    {
        root = inRoot;

        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 1;
        constraints.weighty = 2;
        constraints.gridx = 1;
        constraints.gridy = 1;

        JLabel gameOverLabel = new JLabel("Game Over");
        add(gameOverLabel, constraints);

        graveTable = new JTable();
        DefaultTableModel model = new DefaultTableModel(columnNames, 1);
        graveTable.setModel(model);
        JScrollPane graveTablePane = new JScrollPane(graveTable);

        constraints.weightx = 1;
        constraints.weighty = 10;
        constraints.gridx = 1;
        constraints.gridy = 2;

        add(graveTablePane, constraints);

        JButton okButton = new JButton("Ok");
        okButton.addActionListener(new OkListener());

        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridx = 1;
        constraints.gridy = 3;
        add(okButton,constraints);

    }

    public void setGrave(Grave grave)
    {
        graveTable.setValueAt(grave.getName(), 0, 0);
        graveTable.setValueAt(grave.getPclass(), 0, 1);
        graveTable.setValueAt(grave.getRace(), 0, 2);
        graveTable.setValueAt(grave.getLevel(), 0, 3);
        graveTable.setValueAt(grave.getCauseOfDeath(), 0, 4);
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
