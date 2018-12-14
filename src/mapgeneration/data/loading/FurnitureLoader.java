package mapgeneration.data.loading;

import mapgeneration.data.loading.furnitureloaders.*;
import mapgeneration.data.providers.FurnitureProvider;
import mapgeneration.data.providers.MonsterProvider;
import mapgeneration.data.providers.StatusProvider;
import mapgeneration.data.providers.WeaponProvider;

public class FurnitureLoader
{
    private WeaponProvider weaponProvider;
    private MonsterProvider monsterProvider;
    private StatusProvider statusProvider;

    public FurnitureLoader(WeaponProvider inWeaponProvider, MonsterProvider inMonsterProvider, StatusProvider inStatusProvider)
    {
        weaponProvider = inWeaponProvider;
        monsterProvider = inMonsterProvider;
        statusProvider = inStatusProvider;
    }

    public FurnitureProvider load()
    {
        FurnitureProvider result = new FurnitureProvider();

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
