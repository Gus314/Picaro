package mapgeneration.data;

import control.Controller;
import control.Map;
import entities.Player;
import entities.equipment.factories.WeaponFactory;
import entities.factories.MonsterFactory;
import entities.furniture.factories.*;
import enums.ArmourModificationType;
import enums.Faction;
import enums.StatType;
import enums.WeaponModificationType;
import skills.Skill;
import skills.monster.PoisonFang;
import statuses.Poison;
import statuses.Regen;
import statuses.StatusEffect;
import ui.mainwindow.Messages;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class FurnitureProvider
{
    private Vector<FurnitureFactory> furnitureFactories;

    public FurnitureProvider(Messages messages, Map map, Player player)
    {
        furnitureFactories = new Vector<FurnitureFactory>();
        furnitureFactories.add(new BedFactory(1, 4, "Wooden bed"));

        WeaponFactory weaponFactory = new WeaponFactory(12,	14	,10,	2	,"silver sword" 	, 1 	, 1, 	  1);
        furnitureFactories.add(new BoxFactory(1, 4, "Wooden box", weaponFactory));

        List<Skill> skills = new ArrayList<Skill>();
        skills.add(new PoisonFang());
        MonsterFactory monsterFactory = new MonsterFactory(Faction.FOE,'S',24,16,20,10,5,2,0,1,"spider",map,messages,10,0,10,16,16, skills,1,4);
        skills.clear();

        furnitureFactories.add(new BoxFactory(1, 4, "Wooden box", monsterFactory));

        // Note that map.getPlayer() is not yet valid, so use an independently passed Player object.
        // TODO: Improve object construction order.
        Regen regen = new Regen(player, 2);
        furnitureFactories.add(new FountainFactory(1, 4, "Wooden fountain", regen));
        Poison poison = new Poison(player, 3, 1);
        furnitureFactories.add(new FountainFactory(1, 4, "Wooden fountain", poison));

        furnitureFactories.add(new ShrineFactory(1, 4, "Wooden shrine", StatType.DEFENSE, 2));
        furnitureFactories.add(new ShrineFactory(1, 4, "Wooden shrine", StatType.DEFENSE, -2));

        furnitureFactories.add(new AnvilFactory(1, 4, "wooden anvil", WeaponModificationType.DAMAGE, 2));
        furnitureFactories.add(new AnvilFactory(1, 4, "wooden anvil", WeaponModificationType.INTELLIGENCE, 2));
        furnitureFactories.add(new AnvilFactory(1, 4, "wooden anvil", WeaponModificationType.CRITCHANCE, 1));
        furnitureFactories.add(new AnvilFactory(1, 4, "wooden anvil", WeaponModificationType.DAMAGE, -2));
        furnitureFactories.add(new AnvilFactory(1, 4, "wooden anvil", WeaponModificationType.INTELLIGENCE, -2));
        furnitureFactories.add(new AnvilFactory(1, 4, "wooden anvil", WeaponModificationType.CRITCHANCE, -1));

        furnitureFactories.add(new SewingMachineFactory(1, 4, "wooden sewing machine", ArmourModificationType.ABSORBCHANCE, 1));
        furnitureFactories.add(new SewingMachineFactory(1, 4, "wooden sewing machine", ArmourModificationType.ABSORBCHANCE, -1));

        furnitureFactories.add(new SewingMachineFactory(1, 4, "wooden sewing machine", ArmourModificationType.BLOCKCHANCE, 1));
        furnitureFactories.add(new SewingMachineFactory(1, 4, "wooden sewing machine", ArmourModificationType.BLOCKCHANCE, -1));

        furnitureFactories.add(new SewingMachineFactory(1, 4, "wooden sewing machine", ArmourModificationType.DEFENSE, 1));
        furnitureFactories.add(new SewingMachineFactory(1, 4, "wooden sewing machine", ArmourModificationType.DEFENSE, -1));

        furnitureFactories.add(new SewingMachineFactory(1, 4, "wooden sewing machine", ArmourModificationType.MAGICDEFENSE, 1));
        furnitureFactories.add(new SewingMachineFactory(1, 4, "wooden sewing machine", ArmourModificationType.MAGICDEFENSE, -1));
        furnitureFactories.add(new TeleporterFactory(1, 4, "wooden teleporter"));
    }

    public FurnitureFactory choose(int level)
    {
        Vector<FurnitureFactory> filteredFactories = new Vector<FurnitureFactory>();
        for(FurnitureFactory factory: furnitureFactories)
        {
            if(level >= factory.getMinLevel() && level <= factory.getMaxLevel())
            {
                filteredFactories.add(factory);
            }
        }

        return filteredFactories.get(Controller.getGenerator().nextInt(filteredFactories.size()));
    }
}
