package entities.ai.act;

import control.Controller;
import entities.creatures.Creature;
import entities.Entity;
import entities.Floor;
import entities.creatures.Monster;
import enums.Faction;
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
    public Collection<Entity> act(java.util.Map<Faction, List<Creature>> targets, Collection<Floor> floors)
    {
        List<Skill> defendSkills = new ArrayList<Skill>();
        defendSkills.addAll(getMonster().getSkills(SkillBehaviour.DEFEND));

        Skill chosenSkill = defendSkills.get(Controller.getGenerator().nextInt(defendSkills.size()));

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
                System.out.println("ActDefend::act - unexpected target type.");
                return new ArrayList<Entity>();
            }
        }
    }
}
