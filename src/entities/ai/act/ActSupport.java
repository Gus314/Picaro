package entities.ai.act;

import control.Controller;
import entities.Creature;
import entities.Entity;
import entities.Floor;
import entities.Monster;
import enums.Faction;
import enums.SkillBehaviour;
import skills.*;
import ui.mainwindow.Messages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ActSupport extends Act
{
    public ActSupport(Monster inMonster, Messages inMessages)
    {
        super(inMonster, inMessages);
    }

    // TODO: Improve this method.
    private Floor chooseFloor(Collection<Floor> floors)
    {
        return (Floor) floors.toArray()[0];
    }

    private Creature selectTarget(List<Creature> friends)
    {
        // TODO: Improve this method.
        return friends.get(0);
    }

    @Override
    public Collection<Entity> act(java.util.Map<Faction, List<Creature>> targets, Collection<Floor> floors)
    {
        List<Skill> supportSkills = new ArrayList<Skill>();
        supportSkills.addAll(getMonster().getSkills(SkillBehaviour.SUPPORT));

        Skill chosenSkill = supportSkills.get(Controller.getGenerator().nextInt(supportSkills.size()));

        // TODO: Action skills other than floor skills.
        switch(chosenSkill.getTargetType())
        {
            case FLOOR:
            {
                FloorSkill skill = (FloorSkill) chosenSkill;
                Collection<Entity> additions = new ArrayList<Entity>();

                if(floors.size() == 0)
                {
                    System.out.println("ActSupport::act - no valid floor.");
                    return additions;
                }


                Floor target = chooseFloor(floors);
                String message = skill.action(getMonster(), target, additions);
                getMessages().addMessage(message);
                return additions;
            }
            case SELF:
            {
                SelfSkill skill = (SelfSkill) chosenSkill;
                String message = skill.action(getMonster());
                getMessages().addMessage(message);
                return new ArrayList<Entity>();
            }
            case TARGET:
            {
                List<Creature> friends = targets.get(getMonster().getFaction());
                Creature target = selectTarget(friends);
                String message = ((TargetSkill) chosenSkill).action(getMonster(), target);
                getMessages().addMessage(message);
                return  new ArrayList<Entity>();
            }
            case AREA:
            {
                // TODO: Impact multiple creatures.
                List<Creature> friends = targets.get(getMonster().getFaction());
                Creature target = selectTarget(friends);
                List<Creature> areaTargets = new ArrayList<Creature>();
                areaTargets.add(target); // TODO: Impact multiple creatures.
                String message = ((AreaSkill) chosenSkill).action(getMonster(), areaTargets);
                getMessages().addMessage(message);
                return new ArrayList<>();
            }
            default:
            {
                System.out.println("ActSupport::act - unexpected target type.");
                return new ArrayList<Entity>();
            }
        }
    }
}
