package entities.ai.act;

import control.Controller;
import entities.*;
import entities.creatures.Creature;
import entities.creatures.Monster;
import entities.creatures.Player;
import enums.Faction;
import enums.TargetType;
import skills.AreaSkill;
import skills.Skill;
import skills.TargetSkill;
import ui.mainwindow.Messages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ActAttack extends Act
{
    public ActAttack(Monster inMonster, Messages inMessages)
    {
        super(inMonster, inMessages);
    }

    private Skill chooseAttackSkill(Collection<Skill> skills)
    {
        // TODO: Improve AI for skill choice.
        int choice = Controller.getGenerator().nextInt(skills.size());
        return (Skill) (skills.toArray()[choice]);
    }

    private Creature selectTarget(List<Creature> foes)
    {
        // TODO: Improve this method.
        return foes.get(0);
    }

    @Override
    public Collection<Entity> act(java.util.Map<Faction, List<Creature>> targets, Collection<Floor> floors)
    {
        Monster monster = getMonster();
        Collection<Skill> skills = monster.getAttackSkillsInRange();
        List<Creature> foes = targets.get(monster.getFaction().opposing());

        boolean acted = false;

        if(foes.size() == 0)
        {
            System.out.println("ActAttack::act - no valid target.");
            return new ArrayList<Entity>();
        }


        Creature target = selectTarget(foes);

        if(skills.size() > 0)
        {

            Skill skillToUse = chooseAttackSkill(skills);
            String message = "";
            if(skillToUse.getTargetType() == TargetType.TARGET)
            {
                message = ((TargetSkill) skillToUse).action(monster, target);
            }
            else if(skillToUse.getTargetType() == TargetType.AREA)
            {
                List<Creature> areaTargets = new ArrayList<Creature>();
                areaTargets.add(target); // TODO: Impact multiple creatures.
                message = ((AreaSkill) skillToUse).action(monster, areaTargets);
            }
            else
            {
                // Default to a standard attack.
                System.out.println("ActAttack::act - unexpected attack skill type.");
            }
            getMessages().addMessage(message);
            acted = true;
        }

        if(!acted)
        {
            monster.attack(target);
        }

        boolean killed = (target.getLife() <= 0);
        if(killed && (target instanceof Player))
        {
            ((Player) target).setCauseOfDeath("killed by " + getMonster().getName() + ".");
        }

        return new ArrayList<Entity>();
    }
}
