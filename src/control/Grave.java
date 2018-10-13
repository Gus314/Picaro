package control;

import entities.Player;

public class Grave
{
    private String name;
    private String pclass;
    private String race;
    private int level;
    private String causeOfDeath;

    public String getName()
    {
        return name;
    }

    public String getPclass()
    {
        return pclass;
    }

    public String getRace()
    {
        return race;
    }

    public int getLevel()
    {
        return level;
    }

    public String getCauseOfDeath()
    {
        return causeOfDeath;
    }

    public Grave(String inName, String inPclass, String inRace, int inLevel, String inCauseOfDeath)
    {
        name = inName;
        pclass = inPclass;
        race = inRace;
        level = inLevel;
        causeOfDeath = inCauseOfDeath;
    }

    public Grave(Player player)
    {
        name = player.getName();
        pclass = player.getPclass().getName();
        race = player.getRace().getName();
        level = player.getLevel();
        causeOfDeath = player.getCauseOfDeath();
    }
}
