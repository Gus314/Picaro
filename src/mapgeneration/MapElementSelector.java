package mapgeneration;

import control.Controller;
import enums.MapElementType;

import java.util.HashMap;

public class MapElementSelector
{
    private static final int armourChance = 10;
    private static final int monsterChance = 30;
    private static final int relicChance = 10;
    private static final int weaponChance = 10;
    private static final int consumableChance = 10;
    private static final int furnitureChance = 3;
    private static final int totalChance = armourChance + monsterChance + relicChance + weaponChance + consumableChance + furnitureChance;

    public static MapElementType chooseElementType()
    {
        java.util.Map<MapElementType, Integer> chances = new HashMap<>();
        chances.put(MapElementType.ARMOUR, armourChance);
        chances.put(MapElementType.MONSTER, monsterChance);
        chances.put(MapElementType.RELIC, relicChance);
        chances.put(MapElementType.WEAPON, weaponChance);
        chances.put(MapElementType.CONSUMABLE, consumableChance);
        chances.put(MapElementType.FURNITURE, furnitureChance);

        int choice = Controller.getGenerator().nextInt(totalChance);
        int elementStart = 0;
        for(MapElementType elementType: MapElementType.values())
        {
            int elementEnd = elementStart + chances.get(elementType);
            if(choice < elementEnd && choice >= elementStart)
            {
                return elementType;
            }
            elementStart = elementEnd;
        }

        System.out.println("MapElementSelector::chooseElementType - failed to choose element type. Defaulting to monster.");
        return MapElementType.MONSTER;
    }
}
