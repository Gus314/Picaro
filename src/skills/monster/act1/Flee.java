package skills.monster.act1;

import control.Controller;
import control.Map;
import entities.creatures.Creature;
import enums.SkillBehaviour;
import enums.SkillType;
import skills.SelfSkill;

import java.io.Serializable;

public class Flee extends SelfSkill implements Serializable
{
    @Override
    public String getDescription()
    {
        return "Monster skill";
    }


    @Override
    public String action(Creature source)
    {
        Map map = source.getMap();
        boolean found = false;
        int minimumChange = 3;
        int maximumChange = 10;
        int maxAttempts = 100; // If 100 attempts are not enough, fail to flee.
        int attempts = 0;

        while(!found)
        {
            int rowChange = Controller.getGenerator().nextInt(maximumChange*2)-(maximumChange-1);
            int columnChange = Controller.getGenerator().nextInt(maximumChange*2)-(maximumChange-1);
            int newRow = source.getRow() + rowChange;
            int newColumn = source.getColumn() + columnChange;
            int totalChange = rowChange + columnChange;
            if (rowChange == 0 && columnChange == 0)
            {
                continue;
            }
            boolean sufficientChange = totalChange >= minimumChange;
            boolean spaceFree = map.atPosition(newRow, newColumn).size() == 0;
            if(sufficientChange && spaceFree)
            {
                source.setRow(newRow);
                source.setColumn(newColumn);
                break;
            }

            attempts++;

            if(attempts > maxAttempts)
            {
                return source.getName() + " tried to flee but failed.";
            }
        }

        subtractCost(source);
        return source.getName() + " fled.";
    }

    @Override
    public int getCost()
    {
        return 5;
    }

    @Override
    public SkillType getSkillType()
    {
        return SkillType.PHYSICAL;
    }

    @Override
    public String getName()
    {
        return "Flee";
    }

    @Override
    public SkillBehaviour getSkillBehaviour()
    {
        return SkillBehaviour.RETREAT;
    }
}
