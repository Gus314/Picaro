package entities.ai;

import control.Controller;
import entities.Creature;
import entities.Entity;
import entities.Monster;
import entities.Player;
import enums.TargetType;
import pclasses.PClassProvider;
import skills.AreaSkill;
import skills.Skill;
import skills.TargetSkill;
import ui.mainwindow.Messages;

import javax.swing.*;
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

    @Override
    public Collection<Entity> act(Entity entity)
    {
        Monster monster = getMonster();
        Collection<Skill> skills = monster.getAttackSkillsInRange();

        boolean acted = false;

        if(!(entity instanceof Player))
        {
            System.out.println("ActAttack::act - unexpected entity type.");
            return new ArrayList<Entity>();
        }

        Player player = (Player) entity;

        if(skills.size() > 0)
        {

            Skill skillToUse = chooseAttackSkill(skills);
            String message = "";
            if(skillToUse.getTargetType() == TargetType.TARGET)
            {
                message = ((TargetSkill) skillToUse).action(monster, player);
            }
            else if(skillToUse.getTargetType() == TargetType.AREA)
            {
                List<Creature> targets = new ArrayList<Creature>();
                targets.add(player); // TODO: Friendly Fire?
                message = ((AreaSkill) skillToUse).action(monster, targets);
            }
            else
            {
                // TODO: Tidy this.
                System.out.println("ActAttack::act - unexpected attack skill type.");
                System.exit(-1);
            }
            getMessages().addMessage(message);
            acted = true;
        }

        if(!acted)
        {
            getMonster().attack(player);
        }

        boolean killed = (player.getLife() <= 0);
        if(killed)
        {
            JOptionPane.showMessageDialog(getMessages().getTopLevelAncestor(), "You have died!");
            System.exit(0);
        }

        return new ArrayList<Entity>();
    }
}
