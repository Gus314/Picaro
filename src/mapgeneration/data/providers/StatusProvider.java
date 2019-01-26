package mapgeneration.data.providers;

import enums.LoadableStatus;
import skills.monster.act1.BuryHead;
import skills.monster.act1.PoisonFang;
import skills.player.hunter.*;
import skills.player.mage.*;
import skills.player.warrior.*;
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

        statuses.put(LoadableStatus.LEARNBASH, new Learn(null, new Bash()));
        statuses.put(LoadableStatus.LEARNTOUGHEN, new Learn(null, new Toughen()));
        statuses.put(LoadableStatus.LEARNDASH, new Learn(null, new Dash()));
        statuses.put(LoadableStatus.LEARNDAZE, new Learn(null, new Daze()));
        statuses.put(LoadableStatus.LEARNRELAX, new Learn(null, new Relax()));
        statuses.put(LoadableStatus.LEARNWHIRLWIND, new Learn(null, new Whirlwind()));
        statuses.put(LoadableStatus.LEARNBULWARK, new Learn(null, new Bulwark()));
        statuses.put(LoadableStatus.LEARNSUNDER, new Learn(null, new Sunder()));
        statuses.put(LoadableStatus.LEARNREADY, new Learn(null, new Ready()));
        statuses.put(LoadableStatus.LEARNCHARGE, new Learn(null, new Charge()));
        statuses.put(LoadableStatus.LEARNPOWERSURGE, new Learn(null, new PowerSurge()));
        statuses.put(LoadableStatus.LEARNHEAL, new Learn(null, new Heal()));
        statuses.put(LoadableStatus.LEARNFIREBALL, new Learn(null, new Fireball()));
        statuses.put(LoadableStatus.LEARNLIGHTNINGBOLT, new Learn(null, new LightningBolt()));
        statuses.put(LoadableStatus.LEARNWARD, new Learn(null, new Ward()));
        statuses.put(LoadableStatus.LEARNSWAP, new Learn(null, new Swap()));
        statuses.put(LoadableStatus.LEARNPEDAGOGUE, new Learn(null, new Pedagogue()));
        statuses.put(LoadableStatus.LEARNTORNADO, new Learn(null, new Tornado()));
        statuses.put(LoadableStatus.LEARNRESEARCH, new Learn(null, new Research()));
        statuses.put(LoadableStatus.LEARNSPIRITSHIFT, new Learn(null, new SpiritShift()));
        statuses.put(LoadableStatus.LEARNSKEPTIC, new Learn(null, new Skeptic()));
        statuses.put(LoadableStatus.LEARNTRANSFER, new Learn(null, new Transfer()));
        statuses.put(LoadableStatus.LEARNPOISONDART, new Learn(null, new PoisonDart()));
        statuses.put(LoadableStatus.LEARNSTAB, new Learn(null, new Stab()));
        statuses.put(LoadableStatus.LEARNMEDITATE, new Learn(null, new Meditate()));
        statuses.put(LoadableStatus.LEARNEYETHRUST, new Learn(null, new EyeThrust()));
        statuses.put(LoadableStatus.LEARNHUNDREDCUTS, new Learn(null, new HundredCuts()));
        statuses.put(LoadableStatus.LEARNIMMOBILIZE, new Learn(null, new Immobilize()));
        statuses.put(LoadableStatus.LEARNTRICKSHOT, new Learn(null, new TrickShot()));
        statuses.put(LoadableStatus.LEARNRISKTAKER, new Learn(null, new RiskTaker()));
        statuses.put(LoadableStatus.LEARNVANISH, new Learn(null, new Vanish()));
        statuses.put(LoadableStatus.LEARNFINDWEAKPOINT, new Learn(null, new FindWeakpoint()));
        statuses.put(LoadableStatus.LEARNDEATHLYDANCE, new Learn(null, new DeathlyDance()));

    }

    public StatusEffect get(LoadableStatus status)
    {
        return statuses.get(status);
    }
}
