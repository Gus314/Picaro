package entities.ai;

import entities.Monster;
import entities.Player;
import ui.mainwindow.Messages;

import javax.swing.*;

public class ActAttack extends Act
{
    public ActAttack(Monster inMonster, Messages inMessages)
    {
        super(inMonster, inMessages);
    }

    @Override
    public void act(Player player)
    {
        boolean killed = getMonster().attack(player);
        if(killed)
        {
            JOptionPane.showMessageDialog(getMessages().getTopLevelAncestor(), "You have died!");
            System.exit(0);
        }
    }
}
