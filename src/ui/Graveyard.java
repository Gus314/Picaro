package ui;

import control.DungeonManager;
import control.Grave;
import control.GraveStore;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class Graveyard extends JPanel
{
    private IRoot root;
    private JTable graveTable;
    private final String[] columnNames = {"Name", "Class", "Race", "Level", "Cause of Death"};

    public Graveyard(IRoot inRoot)
    {
        root = inRoot;

        graveTable = new JTable();
        DefaultTableModel model = new DefaultTableModel(columnNames, 1);
        graveTable.setModel(model);
        JScrollPane graveTablePane = new JScrollPane(graveTable);

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weighty = 10;
        constraints.gridy = 0;
        add(graveTablePane, constraints);

        JButton okButton = new JButton("Ok");
        okButton.addActionListener(new OkListener());
        constraints.weighty = 1;
        constraints.gridy = 1;
        add(okButton, constraints);
    }

    public void refresh()
    {
        Collection<Grave> graves = GraveStore.getGraves();
        int numRows = (graves.size() != 0) ? graves.size() : 1;
        DefaultTableModel model = new DefaultTableModel(columnNames, numRows);
        graveTable.setModel(model);

        if(graves.size() > 0)
        {
            int i = 0;

            for(Grave grave: graves)
            {
                graveTable.setValueAt(grave.getName(), i, 0);
                graveTable.setValueAt(grave.getPclass(), i, 1);
                graveTable.setValueAt(grave.getRace(), i, 2);
                graveTable.setValueAt(grave.getLevel(), i, 3);
                graveTable.setValueAt(grave.getCauseOfDeath(), i, 4);
                i++;
            }
        }
        else
        {
            graveTable.setValueAt("None", 0, 0);
            graveTable.setValueAt("", 0, 1);
            graveTable.setValueAt("", 0, 2);
            graveTable.setValueAt("", 0, 3);
            graveTable.setValueAt("", 0, 4);
        }
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
