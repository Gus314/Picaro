package mapgeneration;

import entities.Player;
import entities.equipment.factories.ArmourFactory;
import entities.equipment.factories.ConsumableFactory;
import entities.equipment.factories.RelicFactory;
import entities.equipment.factories.WeaponFactory;
import entities.factories.MonsterFactory;
import mapgeneration.data.*;
import ui.mainwindow.Messages;

public class MazeFactories
{
    private ArmourProvider armourProvider;
    private ConsumableProvider consumableProvider;
    private MonsterProvider monsterProvider;
    private RelicProvider relicProvider;
    private WeaponProvider weaponProvider;

    public MazeFactories(control.Map map, Messages messages, Player player)
    {
        armourProvider = new ArmourProvider();
        consumableProvider = new ConsumableProvider(messages, player);
        monsterProvider = new MonsterProvider(messages, map);
        relicProvider = new RelicProvider();
        weaponProvider = new WeaponProvider();
    }

    public ArmourFactory chooseArmour(int level)
    {
        return armourProvider.choose(level);
    }

    public ConsumableFactory chooseConsumable(int level)
    {
        return consumableProvider.choose(level);
    }


    public MonsterFactory chooseMonster(int level)
    {
        return monsterProvider.choose(level);
    }


    public RelicFactory chooseRelic(int level)
    {
        return relicProvider.choose(level);
    }


    public WeaponFactory chooseWeapon(int level)
    {
        return weaponProvider.choose(level);
    }
}
