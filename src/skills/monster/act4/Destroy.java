package skills.monster.act4;

import control.Controller;
import control.Coordinate;
import entities.Floor;
import entities.creatures.Creature;
import entities.creatures.Player;
import entities.equipment.Item;
import enums.SkillBehaviour;
import enums.StatType;
import skills.TargetSkill;
import enums.SkillType;
import statuses.Bleed;
import statuses.Recklessness;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Destroy extends TargetSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }

    private static final int cost = 40;
    private static final SkillType skillType = SkillType.PHYSICAL;
    private static final String name = "Destroy";
    private static final int range = 1;

    @Override
    public int getRange()
    {
        return range;
    }

    @Override
    public String action(Creature source, Creature target)
    {
        String failureMessage = source.getName() + " failed to destroy any items belonging to " + target.getName() + ".";

        if(target instanceof Player)
        {
            Player player = (Player) target;
            List<Item> items = player.getItems().stream().collect(Collectors.toList());

            if(items.isEmpty())
            {
               return failureMessage;
            }

            Item item = items.get(Controller.getGenerator().nextInt(items.size()));
            player.getItems().remove(item);
            return source.getName() + " destroyed the " + item.getName() + " belonging to " + target.getName() + ".";

        }
        subtractCost(source);
        return failureMessage;
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
        return SkillBehaviour.ATTACK;
    }
}
