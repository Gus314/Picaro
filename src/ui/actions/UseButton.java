package ui.actions;

import control.Map;
import control.TurnHandler;
import entities.Entity;
import entities.Player;
import entities.furniture.Furniture;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UseButton extends JButton
{
    public UseButton(Map inMap, TurnHandler inTurnHandler)
    {
        this.setText("Use");
        this.addActionListener(new UseButton.UseListener(inMap, inTurnHandler));
    }

    public class UseListener implements ActionListener
    {
        private Map map;
        private TurnHandler turnHandler;

        public UseListener(Map inMap, TurnHandler inTurnHandler)
        {
            map = inMap;
            turnHandler = inTurnHandler;
        }

        public void actionPerformed(ActionEvent ae)
        {
            Player player = map.getPlayer();
            List<Entity> here = map.atPosition(player.getRow(), player.getColumn());

            boolean used = false;
            for(Entity entity: here)
            {
                if(entity instanceof Furniture)
                {
                    // Allow for multiple items of furniture at one position.
                    Furniture furniture = (Furniture) entity;
                    furniture.use(player);
                    used = true;
                }
            }

            if(used)
            {
                map.takeTurns();
                turnHandler.update();
            }
            getParent().getParent().getParent().requestFocus();
        }
    }
}
