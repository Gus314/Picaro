package skills.monster.act4;

import control.Controller;
import control.Coordinate;
import entities.Floor;
import entities.creatures.Creature;
import entities.creatures.Monster;
import entities.creatures.Player;
import enums.SkillBehaviour;
import enums.StatType;
import skills.AreaSkill;
import skills.Skill;
import skills.TargetSkill;
import enums.SkillType;
import statuses.Bleed;
import statuses.Recklessness;

import java.awt.geom.Area;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Provide extends AreaSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    private static final int cost = 40;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Provide";
    private static final int range = 7;

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public int getRadius()
    {
        return 5;
    }

    @Override
    public String action(Creature source, List<Creature> targets)
    {
        List<Skill> skills = new ArrayList<Skill>();
        skills.add(new Tsunami());
        skills.add(new Hellfire());
        skills.add(new Rewind());
        skills.add(new Thunderbolt());
        skills.add(new Drink());

        for(Creature target: targets)
        {
            // Do not interfere with player or totem skills.
            if(target instanceof Monster)
            {
                Skill nextSkill = skills.get(Controller.getGenerator().nextInt(skills.size()));
                target.addSkill(nextSkill);
            }
        }

        return source.getName() + " provided talents.";
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
        return SkillBehaviour.SUPPORT;
    }
}
