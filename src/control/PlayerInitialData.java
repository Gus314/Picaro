package control;

import pclasses.Pclass;
import races.Race;

public class PlayerInitialData
{
    private String name;
    private Race race;
    private Pclass pclass;

    public PlayerInitialData(String inName, Race inRace, Pclass inPclass)
    {
        name = inName;
        pclass = inPclass;
        race = inRace;
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
}
