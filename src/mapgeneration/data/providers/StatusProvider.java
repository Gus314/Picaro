package mapgeneration.data.providers;

import entities.creatures.Creature;
import enums.LoadableStatus;
import statuses.*;

import java.util.HashMap;
import java.util.Map;

public class StatusProvider
{
    private Map<LoadableStatus, StatusEffect> statuses;

    public StatusProvider()
    {
        statuses = new HashMap<LoadableStatus, StatusEffect>();
        statuses.put(LoadableStatus.REGEN, new Regen(null, 2));
        statuses.put(LoadableStatus.RECKLESS, new Recklessness());
        statuses.put(LoadableStatus.NAIVE, new Naive());
        statuses.put(LoadableStatus.GROGGY, new Groggy(null, 3, 5));
        statuses.put(LoadableStatus.POISON, new Poison(null, 3, 1));
        statuses.put(LoadableStatus.SHELL, new Shell(null, 3, 2));
        statuses.put(LoadableStatus.SHIELD, new Shield(null, 3, 2));
        statuses.put(LoadableStatus.BLEED, new Bleed(null, 3, 1));
        statuses.put(LoadableStatus.RAMPUP, new RampUp(null, 3, 1));
        statuses.put(LoadableStatus.COOLDOWN, new CoolDown(null, 3, 1));
        statuses.put(LoadableStatus.ZEN, new Zen(null, 3));
        statuses.put(LoadableStatus.STUDIED, new Studied(null, 3));
        statuses.put(LoadableStatus.MUTE, new Mute(null, 3));
        statuses.put(LoadableStatus.WEAK, new Weak(null, 3));
        statuses.put(LoadableStatus.EXPOSED, new Exposed(null, 3));
        statuses.put(LoadableStatus.CYNICAL, new Cynical(null, 3));
        statuses.put(LoadableStatus.STRONG, new Strong(null, 3, 2));
        statuses.put(LoadableStatus.WISE, new Wise(null, 3, 2));
    }

    public StatusEffect get(LoadableStatus status)
    {
        return statuses.get(status);
    }
}
