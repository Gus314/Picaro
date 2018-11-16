package entities.ai.act;

import entities.*;
import entities.creatures.Creature;
import entities.creatures.Monster;
import enums.Faction;
import ui.mainwindow.Messages;

import java.util.Collection;
import java.util.List;

public abstract class Act
{
    private Monster monster;
    private Messages messages;

    protected Monster getMonster(){return monster;}

    protected Messages getMessages(){return messages;}

    public Act(Monster inMonster, Messages inMessages)
    {
        monster = inMonster;
        messages = inMessages;
    }

    public abstract Collection<Entity> act(java.util.Map<Faction, List<Creature>> targets, Collection<Floor> floors);
}
