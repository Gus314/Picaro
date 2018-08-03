package entities.ai;

import control.Controller;
import entities.Entity;
import entities.Floor;
import entities.Monster;
import enums.SkillBehaviour;
import skills.FloorSkill;
import skills.SelfSkill;
import skills.Skill;
import ui.mainwindow.Messages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ActRetreat extends Act
{
    public ActRetreat(Monster inMonster, Messages inMessages)
    {
        super(inMonster, inMessages);
    }

    @Override
    public Collection<Entity> act(Entity entity)
    {
        List<Skill> retreatSkills = new ArrayList<Skill>();
        retreatSkills.addAll(getMonster().getSkills(SkillBehaviour.RETREAT));

        Skill chosenSkill = retreatSkills.get(Controller.getGenerator().nextInt(retreatSkills.size()));

        // TODO: Action skills other than self skills.
        switch(chosenSkill.getTargetType())
        {
            case SELF:
            {
                SelfSkill skill = (SelfSkill) chosenSkill;

                if(!(entity instanceof Monster))
                {
                    System.out.println("ActRetreat::act - unexpected entity type.");
                    return new ArrayList<Entity>();
                }

                String message = skill.action(getMonster());
                getMessages().addMessage(message);
                return new ArrayList<Entity>();
            }
            default:
            {
                System.out.println("ActRetreat::act - unexpected target type.");
                return new ArrayList<Entity>();
            }
        }
    }
}
