package control;

public class Options
{
    private boolean autoPickup;

    public Options(boolean inAutoPickup)
    {
        autoPickup = inAutoPickup;
    }

    public boolean getAutoPickup()
    {
        return autoPickup;
    }

    public void setAutoPickup(boolean inAutoPickup)
    {
        autoPickup = inAutoPickup;
    }
}
