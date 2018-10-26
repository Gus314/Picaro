package entities.factories;

import control.Map;
import entities.Monster;
import entities.Totem;
import enums.Faction;
import skills.Skill;
import statuses.StatusEffect;
import ui.mainwindow.Messages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TotemFactory extends AbstractEntityFactory
{
    private Faction faction;
    private int life;
    private int defense;
    private int blockChance;
    private int absorbChance;
    private int range;
    private int minLevel;
    private int maxLevel;
    private Map map;
    private Messages messages;
    private String name;
    private int exp;
    private int magicDefense;
    private StatusEffect status;


    public TotemFactory(Faction inFaction, int inLife, int inDefense, int inBlockChance, int inAbsorbChance, int inRange, String inName,
                        Map inMap, Messages inMessages, int inExp, int inMagicDefense, int inMinLevel, int inMaxLevel, StatusEffect inStatus)
    {
        faction = inFaction;
        life = inLife;
        defense = inDefense;
        blockChance = inBlockChance;
        absorbChance = inAbsorbChance;
        range = inRange;
        map = inMap;
        messages = inMessages;
        name = inName;
        exp = inExp;
        minLevel = inMinLevel;
        maxLevel = inMaxLevel;
        magicDefense = inMagicDefense;
        status = inStatus;

    }

    public void setMessages(Messages inMessages){messages = inMessages;}

    public void setMap(Map inMap)
    {
        map = inMap;
    }

    public int getMinLevel(){return minLevel;}

    public int getMaxLevel(){return maxLevel;}

    public Totem construct(int inRow, int inColumn)
    {
        return new Totem(inRow, inColumn, map, messages, faction, defense, name, life, life, blockChance, absorbChance,
                range, exp, magicDefense, minLevel, maxLevel, status);
    }
}
