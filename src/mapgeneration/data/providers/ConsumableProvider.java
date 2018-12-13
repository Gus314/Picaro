package mapgeneration.data.providers;

import control.Controller;
import entities.creatures.Player;
import entities.equipment.factories.ConsumableFactory;
import enums.ConsumableType;
import ui.mainwindow.Messages;

import java.util.Vector;

public class ConsumableProvider
{
    private Vector<ConsumableFactory> consumableFactories;

    public ConsumableProvider(Messages messages, Player player)
    {
        consumableFactories = new Vector<ConsumableFactory>();
        consumableFactories.add(new ConsumableFactory('p',5,ConsumableType.RestoreHP, messages,"tiny hp potion", player, 1,2));
        consumableFactories.add(new ConsumableFactory('p',5,ConsumableType.RestorePP,messages,"tiny pp potion",player,1,2));
        consumableFactories.add(new ConsumableFactory('p',5,ConsumableType.RestoreMP,messages,"tiny mp potion",player,1,2));
        consumableFactories.add(new ConsumableFactory('p',10,ConsumableType.RestoreHP,messages,"small hp potion",player,2,3));
        consumableFactories.add(new ConsumableFactory('p',10,ConsumableType.RestorePP,messages,"small pp potion",player,2,3));
        consumableFactories.add(new ConsumableFactory('p',10,ConsumableType.RestoreMP,messages,"small mp potion",player,2,3));
        consumableFactories.add(new ConsumableFactory('p',15,ConsumableType.RestoreHP,messages,"hp potion",player,3,4));
        consumableFactories.add(new ConsumableFactory('p',15,ConsumableType.RestorePP,messages,"pp potion",player,3,4));
        consumableFactories.add(new ConsumableFactory('p',15,ConsumableType.RestoreMP,messages,"mp potion",player,3,4));
        consumableFactories.add(new ConsumableFactory('p',20,ConsumableType.RestoreHP,messages,"large hp potion",player,4,4));
        consumableFactories.add(new ConsumableFactory('p',20,ConsumableType.RestorePP,messages,"large pp potion",player,4,4));
        consumableFactories.add(new ConsumableFactory('p',20,ConsumableType.RestoreMP,messages,"large mp potion",player,4,4));
    }

    public ConsumableFactory choose(int level)
    {
        Vector<ConsumableFactory> filteredFactories = new Vector<ConsumableFactory>();
        for(ConsumableFactory factory: consumableFactories)
        {
            if(level >= factory.getMinLevel() && level <= factory.getMaxLevel())
            {
                filteredFactories.add(factory);
            }
        }

        return filteredFactories.get(Controller.getGenerator().nextInt(filteredFactories.size()));
    }
}