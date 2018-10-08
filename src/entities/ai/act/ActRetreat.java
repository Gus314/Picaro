package entities.ai.act;

import control.Controller;
import entities.Creature;
import entities.Entity;
import entities.Floor;
import entities.Monster;
import enums.Faction;
import enums.SkillBehaviour;
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
    public Collection<Entity> act(java.util.Map<Faction, List<Creature>> targets, Collection<Floor> floors)
    {
        List<Skill> retreatSkills = new ArrayList<Skill>();
        retreatSkills.addAll(getMonster().getSkills(SkillBehaviour.RETREAT));

        Skill chosenSkill = retreatSkills.get(Controller.getGenerator().nextInt(retreatSkills.size()));

        switch(chosenSkill.getTargetType())
        {
            case SELF:
            {
                SelfSkill skill = (SelfSkill) chosenSkill;
                String message = skill.action(getMonster());
                getMessages().addMessage(message);
                return new ArrayList<Entity>();
            }
            case FLOOR:
            case AREA:
            case TARGET:
            default:
            {
                System.out.println("ActRetreat::act - unexpected target type.");
                return new ArrayList<Entity>();
            }
        }
    }
}
