package entities;

import control.Map;
import enums.Faction;
import skills.Skill;
import statuses.StatusEffect;
import ui.mainwindow.Messages;

import java.util.ArrayList;
import java.util.Collection;

// TODO: Refactor so that Totem does not inherit unused attributes and methods of Monster.
public class Totem extends Monster
{
    private StatusEffect status;
    private static final Character cha = '0';

    public Totem(int inRow, int inColumn, Map inMap, Messages inMessages, Faction inFaction, int inDefense, String inName, int inMaxLife, int inLife, int inBlock, int inAbsorb, int inRange, int inExp, int inMagicDefense, int inMinLevel, int inMaxLevel, StatusEffect inStatus)
    {
        super(cha, inRow, inColumn, inMap, inMessages, inFaction, inDefense, inName, inMaxLife, inLife, 0, 0, 0, inBlock, inAbsorb, inRange, inExp, 0, 0, 0, 0, 0, inMagicDefense, new ArrayList<Skill>(), inMinLevel, inMaxLevel);
        status = inStatus;
    }

    @Override
    public Collection<Entity> takeTurn()
    {
        // Return a collection of entities to be added, e.g. summoned creatures.
        setPlayerSighted(isPlayerSighted() || getMap().isInLineOfSight(this, getMap().getPlayer(), getSightRadius()));

        // If the player has never been seen then do nothing as AI computations are expensive.
        if(!isPlayerSighted())
        {
            return new ArrayList<Entity>();
        }


        boolean hasOutputMessage = false;

        for(Entity ent: getMap().lineOfSight(this, getRange()))
        {
            if((ent instanceof Creature) && (!(ent instanceof Totem))) // Prevent totems from influencing other totems.
            {
                Creature creature = (Creature)ent;
                StatusEffect statusClone = null;
                try
                {
                    statusClone = (StatusEffect) status.clone();
                }
                catch (CloneNotSupportedException e)
                {
                    System.out.println("Totem::takeTurn() - unable to clone status.");
                    break;
                }
                statusClone.setTarget(creature);
                creature.addStatusEffect(statusClone);

                if(!hasOutputMessage)
                {
                    getMessages().addMessage(getName() + " inflicted " + status.getName() + " on nearby creatures.");
                    hasOutputMessage = true;
                }
            }
        }

        return new ArrayList<Entity>();
    }
}
