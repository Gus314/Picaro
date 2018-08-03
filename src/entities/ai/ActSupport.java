package entities.ai;

import control.Controller;
import entities.Entity;
import entities.Floor;
import entities.Monster;
import entities.Player;
import enums.SkillBehaviour;
import skills.FloorSkill;
import skills.Skill;
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

    @Override
    public Collection<Entity> act(Entity entity)
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

                if(!(entity instanceof Floor))
                {
                    System.out.println("ActSupport::act - unexpected entity type.");
                    return new ArrayList<Entity>();
                }

                Floor floor = (Floor)entity;
                Collection<Entity> additions = new ArrayList<Entity>();
                String message = skill.action(getMonster(), floor, additions);
                getMessages().addMessage(message);
                return additions;
            }
            default:
            {
                System.out.println("ActSupport::act - unexpected target type.");
                return new ArrayList<Entity>();
            }
        }

    }
}
