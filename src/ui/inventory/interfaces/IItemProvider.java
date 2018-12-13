package ui.inventory.interfaces;

import entities.equipment.Item;

import java.util.Optional;

public interface IItemProvider
{
    public Optional<Item> getItem();
}
