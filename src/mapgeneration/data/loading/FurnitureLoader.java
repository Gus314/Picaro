package mapgeneration.data.loading;

import entities.equipment.factories.WeaponFactory;
import entities.factories.MonsterFactory;
import entities.furniture.factories.FurnitureFactory;
import mapgeneration.data.loading.furnitureloaders.*;
import mapgeneration.data.providers.EntityProvider;
import mapgeneration.data.providers.StatusProvider;

public class FurnitureLoader
{
    private EntityProvider<WeaponFactory> weaponProvider;
    private EntityProvider<MonsterFactory> monsterProvider;
    private StatusProvider statusProvider;

    public FurnitureLoader(EntityProvider<WeaponFactory> inWeaponProvider, EntityProvider<MonsterFactory> inMonsterProvider, StatusProvider inStatusProvider)
    {
        weaponProvider = inWeaponProvider;
        monsterProvider = inMonsterProvider;
        statusProvider = inStatusProvider;
    }

    public EntityProvider<FurnitureFactory> load()
    {
        EntityProvider<FurnitureFactory> result = new EntityProvider<FurnitureFactory>();

        result.addAll(BedLoader.load());
        result.addAll(BoxLoader.load(weaponProvider, monsterProvider));
        result.addAll(ShrineLoader.load());
        result.addAll(FountainLoader.load(statusProvider));
        result.addAll(AnvilLoader.load());
        result.addAll(SewingMachineLoader.load());
        result.addAll(TeleporterLoader.load());

        return result;
    }
}
