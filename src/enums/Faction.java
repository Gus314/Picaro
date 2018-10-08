package enums;

public enum Faction
{
    PLAYER, FOE;

    public Faction opposing()
    {
        return (this == PLAYER) ? FOE : PLAYER;
    }
}
