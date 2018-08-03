package skills;

import entities.Entity;
import entities.factories.AbstractEntityFactory;

import java.util.Collection;

public abstract class SummonSkill extends FloorSkill
{
    private AbstractEntityFactory summon;

    public AbstractEntityFactory getSummon()
    {
        return summon;
    }

    public void setSummon(AbstractEntityFactory inSummon)
    {
        summon = inSummon;
    }
}
