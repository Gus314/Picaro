package control;

import entities.equipment.Armour;
import entities.equipment.Weapon;
import enums.ArmourType;
import pclasses.Pclass;
import races.Race;
import java.util.Map;
import java.util.HashMap;

public class PlayerInitialData
{
    private String name;
    private Race race;
    private Pclass pclass;
    private Weapon weapon;
    Map<ArmourType, Armour> armours;

    public PlayerInitialData(String inName, Race inRace, Pclass inPclass)
    {
        name = inName;
        pclass = inPclass;
        race = inRace;
        weapon = new Weapon(10, 12, 0, 1, "Stick", 'w', 0, 0, 1, 1, 1);
        armours = new HashMap<ArmourType, Armour>();
        armours.put(ArmourType.CHEST, new Armour(ArmourType.CHEST, 0, 0, 2, 2, "rags", 'a', 0, 0, 1, 1));
        armours.put(ArmourType.FEET, new Armour(ArmourType.FEET, 0, 0, 2, 2, "sandals", 'a', 0, 0, 1, 1));
        armours.put(ArmourType.HANDS, new Armour(ArmourType.HANDS, 0, 0, 2, 2, "flimsy gloves", 'a', 0, 0, 1, 1));
        armours.put(ArmourType.HEAD, new Armour(ArmourType.HEAD, 0, 0, 2, 2, "bandana", 'a', 0, 0, 1, 1));
        armours.put(ArmourType.LEGS, new Armour(ArmourType.LEGS, 0, 0, 2, 2, "shorts", 'a', 0, 0, 1, 1));
    }

    public String getName()
    {
        return name;
    }

    public Race getRace()
    {
        return race;
    }

    public Pclass getPclass()
    {
        return pclass;
    }

    public Weapon getWeapon(){return weapon;}

    public Map<ArmourType, Armour> getArmours(){return armours;}
}
