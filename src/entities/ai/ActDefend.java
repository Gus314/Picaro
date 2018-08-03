package entities.ai;

import control.Controller;
import entities.Entity;
import entities.Monster;
import enums.SkillBehaviour;
import skills.SelfSkill;
import skills.Skill;
import ui.mainwindow.Messages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ActDefend extends Act
{
    public ActDefend(Monster inMonster, Messages inMessages)
    {
        super(inMonster, inMessages);
    }

    @Override
    public Collection<Entity> act(Entity entity)
    {
        List<Skill> defendSkills = new ArrayList<Skill>();
        defendSkills.addAll(getMonster().getSkills(SkillBehaviour.DEFEND));

        Skill chosenSkill = defendSkills.get(Controller.getGenerator().nextInt(defendSkills.size()));

        // TODO: Action skills other than self skills.
        switch(chosenSkill.getTargetType())
        {
            case SELF:
            {
                SelfSkill skill = (SelfSkill) chosenSkill;

                if(!(entity instanceof Monster))
                {
                    System.out.println("ActDefend::act - unexpected entity type.");
                    return new ArrayList<Entity>();
                }

                String message = skill.action(getMonster());
                getMessages().addMessage(message);
                return new ArrayList<Entity>();
            }
            default:
            {
                System.out.println("ActDefend::act - unexpected target type.");
                return new ArrayList<Entity>();
            }
        }
    }
}
