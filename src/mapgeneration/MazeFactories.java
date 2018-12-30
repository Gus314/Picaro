package mapgeneration;

import entities.creatures.Player;
import entities.equipment.factories.ArmourFactory;
import entities.equipment.factories.ConsumableFactory;
import entities.equipment.factories.RelicFactory;
import entities.equipment.factories.WeaponFactory;
import entities.factories.TotemFactory;
import entities.furniture.Furniture;
import entities.furniture.factories.FurnitureFactory;
import entities.factories.MonsterFactory;
import mapgeneration.data.loading.*;
import mapgeneration.data.providers.*;
import ui.mainwindow.Messages;

import java.util.ArrayList;
import java.util.Collection;

public class MazeFactories
{
    private EntityProvider<ArmourFactory> armourProvider;
    private EntityProvider<ConsumableFactory> consumableProvider;
    private EntityProvider<MonsterFactory> monsterProvider;
    private EntityProvider<RelicFactory> relicProvider;
    private EntityProvider<WeaponFactory> weaponProvider;
    private EntityProvider<FurnitureFactory> furnitureProvider;
    private EntityProvider<TotemFactory> totemProvider;

    public MazeFactories(control.Map map, Messages messages, Player player)
    {
        armourProvider = ArmourLoader.load();

        ConsumableLoader consumableLoader = new ConsumableLoader(messages, player);
        consumableProvider = consumableLoader.load();

        weaponProvider = WeaponLoader.load();

        StatusProvider statusProvider = new StatusProvider();
        TotemLoader totemLoader = new TotemLoader(messages, map, statusProvider);
        totemProvider = totemLoader.load();

        RelicLoader relicLoader = new RelicLoader(statusProvider);
        relicProvider = relicLoader.load();

        SkillProvider skillProvider = new SkillProvider();

        MonsterLoader monsterLoader = new MonsterLoader(messages, map, skillProvider);
        monsterProvider = monsterLoader.load();

        FurnitureLoader furnitureLoader = new FurnitureLoader(weaponProvider, monsterProvider, statusProvider);
        furnitureProvider = furnitureLoader.load();

        Collection<EntityProvider> entityProviders = new ArrayList<EntityProvider>();
        entityProviders.add(monsterProvider);
        entityProviders.add(totemProvider);
        skillProvider.populate(entityProviders); // Resolve circular dependency.
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
