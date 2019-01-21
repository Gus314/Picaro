package skills.combat;

import entities.creatures.Creature;

public class CombatInfo
{
    private boolean absorbed;
    private boolean critical;
    private boolean blocked;
    private int damage;
    private String source;
    private String target;
    private String verb;

    public CombatInfo(boolean inAbsorbed, boolean inBlocked, boolean inCritical, int inDamage, String inSource, String inTarget, String inVerb)
    {
        absorbed = inAbsorbed;
        critical = inCritical;
        blocked = inBlocked;
        damage = inDamage;
        source = inSource;
        target = inTarget;
        verb = inVerb;
    }

    public int getLifeChange()
    {
        // Note that life change is positive after absorbed damage, negative after non-absorbed damage.
        return blocked ? 0 :
                absorbed ? damage : damage * -1;
    }

    public String getMessage()
    {
        String message = "";

        message = source + " " + verb + " " + target + " for " + damage + " damage";

        boolean effects = absorbed || blocked || critical;
        if(effects)
        {
            message += " (";
            message = absorbed ? message += " absorbed " : message;
            message = blocked ? message += " blocked " : message;
            message = critical ? message += " critical " : message;
            message += ")";
        }

        message += ".";
        return message;
    }
}
