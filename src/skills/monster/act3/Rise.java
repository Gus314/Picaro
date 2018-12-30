package skills.monster.act3;

import control.Controller;
import entities.Entity;
import entities.Floor;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.StatType;
import skills.FloorSkill;
import skills.SelfSkill;
import skills.TargetSkill;
import enums.SkillType;
import statuses.*;

import java.io.Serializable;
import java.util.Collection;

public class Rise extends FloorSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    private static final int cost = 40;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Rise";

    @Override
    public int getRange() {
        return 0;
    }

    @Override
    public String action(Creature source, Floor floor, Collection<Entity> additions)
    {
        source.setRow(floor.getRow());
        source.setColumn(floor.getColumn());
        source.setLife(source.getMaxLife());
        source.setPhysicalPoints(source.getMaxPhysicalPoints());
        source.setMagicPoints(source.getMaxMagicPoints());

        Mute mute = new Mute(source, 5);
        source.addStatusEffect(mute);

        subtractCost(source);
        return getName() + " rises from its remaining embers.";
    }

    @Override
    public int getCost()
    {
        return cost;
    }

    @Override
    public SkillType getSkillType()
    {
        return skillType;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.RETREAT;
    }
}
