package control;

import entities.Player;

public class Grave
{
    private String name;
    private String pclass;
    private String race;
    private int level;
    private String causeOfDeath;

    public String toString()
    {
        return name + "," + pclass + "," + race + "," +  level + "," + causeOfDeath;
    }

    private static String moveToNextToken(String str)
    {
        return str.substring(str.indexOf(',')+1);
    }

    private static String nextToken(String str)
    {
        return str.substring(0, str.indexOf(','));
    }

    public Grave(String str)
    {
        String remainder = str;
        name = nextToken(remainder);
        remainder = moveToNextToken(remainder);
        pclass = nextToken(remainder);
        remainder = moveToNextToken(remainder);
        race = nextToken(remainder);
        remainder = moveToNextToken(remainder);
        level = Integer.parseInt(nextToken(remainder));
        remainder = moveToNextToken(remainder);
        causeOfDeath = remainder;
    }

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

    public Grave(Player player)
    {
        name = player.getName();
        pclass = player.getPclass().getName();
        race = player.getRace().getName();
        level = player.getLevel();
        causeOfDeath = player.getCauseOfDeath();
    }
}
