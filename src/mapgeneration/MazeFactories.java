package mapgeneration;

import entities.creatures.Player;
import entities.equipment.factories.ArmourFactory;
import entities.equipment.factories.ConsumableFactory;
import entities.equipment.factories.RelicFactory;
import entities.equipment.factories.WeaponFactory;
import entities.factories.TotemFactory;
import entities.furniture.factories.FurnitureFactory;
import entities.factories.MonsterFactory;
import mapgeneration.data.loading.ArmourLoader;
import mapgeneration.data.loading.WeaponLoader;
import mapgeneration.data.providers.*;
import ui.mainwindow.Messages;

public class MazeFactories
{
    private ArmourProvider armourProvider;
    private ConsumableProvider consumableProvider;
    private MonsterProvider monsterProvider;
    private RelicProvider relicProvider;
    private WeaponProvider weaponProvider;
    private FurnitureProvider furnitureProvider;
    private TotemProvider totemProvider;

    public MazeFactories(control.Map map, Messages messages, Player player)
    {
        armourProvider = ArmourLoader.load();
        consumableProvider = new ConsumableProvider(messages, player);
        monsterProvider = new MonsterProvider(messages, map);
        relicProvider = new RelicProvider(player);
        weaponProvider = WeaponLoader.load();
        furnitureProvider = new FurnitureProvider(messages, map, player);
        totemProvider = new TotemProvider(messages, map);
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

    public FurnitureFactory chooseFurniture(int level){return furnitureProvider.choose(level);}

    public TotemFactory chooseTotem(int level){return totemProvider.choose(level);}
}
