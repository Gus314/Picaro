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

        setLayout(new GridLayout(2, 1));
        graveTable = new JTable();
        DefaultTableModel model = new DefaultTableModel(columnNames, 1);
        graveTable.setModel(model);
        JScrollPane graveTablePane = new JScrollPane(graveTable);
        add(graveTablePane);

        JButton okButton = new JButton("Ok");
        okButton.addActionListener(new OkListener());
        add(okButton);

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
