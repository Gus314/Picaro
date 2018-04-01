package ui.inventory;

import entities.equipment.Relic;

import javax.swing.*;

public class RelicPanel extends JPanel
{
    private JLabel name;
    private JLabel effect;
    private JLabel amount;

   public RelicPanel(Relic relic)
   {
       add(new JLabel("|Relic:"));
       name = new JLabel();
       add(name);
       add(new JLabel("|Effect:"));
       effect = new JLabel();
       add(effect);
       add(new JLabel("|Amount:"));
       amount = new JLabel();
       add(amount);
   }

   public void refresh(Relic relic)
   {
       if(relic!=null)
       {
           name.setText(relic.getName());
           effect.setText((relic.getEffect()).toString());
           amount.setText(((Integer)(relic.getAmount())).toString());
       }
       else
       {
           name.setText("None");
           effect.setText("None");
           amount.setText("None");
       }
   }
}
