package skills;

import control.Controller;
import entities.creatures.Creature;

public class CombatHelper
{
    public static int magicDamage(Creature source, Creature target)
    {
        boolean absorbed = (Controller.getGenerator().nextInt(100 - target.getAbsorbChance()) == 0);
        int halfIntelligence = source.getIntelligence() / 2;
        int damage = halfIntelligence + Controller.getGenerator().nextInt(halfIntelligence) - target.getMagicDefense();
        return absorbed ? damage * -1 : damage;
    }
}
