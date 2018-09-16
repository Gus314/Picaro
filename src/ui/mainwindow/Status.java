package ui.mainwindow;

import entities.Player;
import statuses.IntensityStatusEffect;
import statuses.StatusEffect;
import statuses.TemporaryStatusEffect;

import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Status extends JPanel
{
    private JScrollPane scrollPane;
    private Player player;
    private JTable statusTable;
    private String[] columnNames = {"Status Effect", "Description", "Intensity", "Remaining Turns"};

    public Status(Player inPlayer)
    {
        super();
        GridLayout grid = new GridLayout(1, 1);
        this.setLayout(grid);

        player = inPlayer;

        statusTable = new JTable();
        DefaultTableModel model = new DefaultTableModel(columnNames, player.getStatusEffects().size()+1);
        statusTable.setModel(model);

        scrollPane = new JScrollPane(statusTable);
        add(scrollPane);
    }

    public void refresh()
    {
        DefaultTableModel model = new DefaultTableModel(columnNames, player.getStatusEffects().size());
        statusTable.setModel(model);

        int row = 0;
        for(StatusEffect effect: player.getStatusEffects())
        {
            statusTable.setValueAt(effect.getName(), row, 0);
            statusTable.setValueAt(effect.getDescription(), row, 1);

            String intensityText = "";
            if(effect instanceof IntensityStatusEffect)
            {
                Integer intensity = ((IntensityStatusEffect) effect).getIntensity();
                intensityText = intensity.toString();
            }

            String remainingTurnsText = "";
            if(effect instanceof TemporaryStatusEffect)
            {
                Integer remainingTurns = ((TemporaryStatusEffect) effect).getRemainingTurns();
                remainingTurnsText = remainingTurns.toString();
            }
            else
            {
                remainingTurnsText = "Permanent";
            }

            statusTable.setValueAt(intensityText, row, 2);
            statusTable.setValueAt(remainingTurnsText, row, 3);

            row++;
        }
    }
}
