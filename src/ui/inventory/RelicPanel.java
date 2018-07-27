package ui.inventory;

import entities.equipment.Relic;

import javax.swing.*;

public class RelicPanel extends JPanel
{
    private JLabel name;
    private JLabel statusEffect;

   public RelicPanel(Relic relic)
   {
       add(new JLabel("|Relic:"));
       name = new JLabel();
       add(name);
       add(new JLabel("|Status Effect:"));
       statusEffect = new JLabel();
       add(statusEffect);
   }

   public void refresh(Relic relic)
   {
       if(relic!=null)
       {
           name.setText(relic.getName());
           statusEffect.setText((relic.getStatusEffect()).getName());
       }
       else
       {
           name.setText("None");
           statusEffect.setText("None");
       }
   }
}
