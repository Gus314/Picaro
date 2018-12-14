package mapgeneration.data.providers;

import control.Controller;
import control.Map;
import entities.creatures.Player;
import entities.equipment.factories.WeaponFactory;
import entities.factories.MonsterFactory;
import entities.furniture.Furniture;
import entities.furniture.factories.*;
import enums.ArmourModificationType;
import enums.Faction;
import enums.StatType;
import enums.WeaponModificationType;
import skills.Skill;
import skills.monster.Dodge;
import skills.monster.PoisonFang;
import statuses.Poison;
import statuses.Regen;
import ui.mainwindow.Messages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class FurnitureProvider
{
    private Vector<FurnitureFactory> furnitureFactories;

    public FurnitureProvider()
    {
        furnitureFactories = new Vector<FurnitureFactory>();
    }

    public void addAll(Collection<FurnitureFactory> newFurnitureFactories)
    {
        furnitureFactories.addAll(newFurnitureFactories);
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
