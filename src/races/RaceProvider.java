package races;

import java.util.ArrayList;
import java.util.List;

public class RaceProvider
{
    public List<Race> getRaces()
    {
        List<Race> result = new ArrayList<Race>();
        
        result.add(constructHuman());
        //result.add(constructElf());
        //result.add(constructOrc());

        return result;
    }

    private Race constructHuman()
    {
        int initialLife = 20;
        int initialDefense = 7;
        int initialMinDamage = 2;
        int initialMaxDamage = 5;
        int initialCritChance = 0;
        int initialBlockChance = 0;
        int initialAbsorbChance = 0;
        int initialMaxPhysicalPoints = 10;
        int initialMaxMagicPoints = 10;
        int initialIntelligence = 5;
        int initialMagicDefence = 5;
        int changeLife = 5;
        int changeDefense = 5;
        int changeMinDamage = 0;
        int changeMaxDamage = 0;
        int changeCritChance = 0;
        int changeBlockChance = 0;
        int changeAbsorbChance = 0;
        int changeMaxPhysicalPoints = 5;
        int changeMaxMagicPoints = 5;
        int changeIntelligence = 5;
        int changeMagicDefence = 5;
        Race human = new Race("Human", initialLife,  initialDefense,  initialMinDamage,  initialMaxDamage, initialCritChance,  initialBlockChance,  initialAbsorbChance,
                initialMaxPhysicalPoints,  initialMaxMagicPoints,  initialIntelligence,  initialMagicDefence,  changeLife,  changeDefense,  changeMinDamage,  changeMaxDamage,
                changeCritChance,  changeBlockChance,  changeAbsorbChance,  changeMaxPhysicalPoints,  changeMaxMagicPoints,  changeIntelligence,  changeMagicDefence);
        return human;
    }

    private Race constructElf()
    {
        int initialLife = 16;
        int initialDefense = 3;
        int initialMinDamage = 1;
        int initialMaxDamage = 4;
        int initialCritChance = 0;
        int initialBlockChance = 0;
        int initialAbsorbChance = 0;
        int initialMaxPhysicalPoints = 5;
        int initialMaxMagicPoints = 15;
        int initialIntelligence = 8;
        int initialMagicDefence = 8;
        int changeLife = 3;
        int changeDefense = 3;
        int changeMinDamage = 0;
        int changeMaxDamage = 0;
        int changeCritChance = 0;
        int changeBlockChance = 0;
        int changeAbsorbChance = 0;
        int changeMaxPhysicalPoints = 3;
        int changeMaxMagicPoints = 7;
        int changeIntelligence = 7;
        int changeMagicDefence = 7;
        Race human = new Race("Elf", initialLife,  initialDefense,  initialMinDamage,  initialMaxDamage, initialCritChance,  initialBlockChance,  initialAbsorbChance,
                initialMaxPhysicalPoints,  initialMaxMagicPoints,  initialIntelligence,  initialMagicDefence,  changeLife,  changeDefense,  changeMinDamage,  changeMaxDamage,
                changeCritChance,  changeBlockChance,  changeAbsorbChance,  changeMaxPhysicalPoints,  changeMaxMagicPoints,  changeIntelligence,  changeMagicDefence);
        return human;
    }

    private Race constructOrc()
    {
        int initialLife = 25;
        int initialDefense = 10;
        int initialMinDamage = 3;
        int initialMaxDamage = 6;
        int initialCritChance = 0;
        int initialBlockChance = 0;
        int initialAbsorbChance = 0;
        int initialMaxPhysicalPoints = 15;
        int initialMaxMagicPoints = 5;
        int initialIntelligence = 3;
        int initialMagicDefence = 3;
        int changeLife = 8;
        int changeDefense = 7;
        int changeMinDamage = 0;
        int changeMaxDamage = 0;
        int changeCritChance = 0;
        int changeBlockChance = 0;
        int changeAbsorbChance = 0;
        int changeMaxPhysicalPoints = 7;
        int changeMaxMagicPoints = 3;
        int changeIntelligence = 3;
        int changeMagicDefence = 3;
        Race human = new Race("Orc", initialLife,  initialDefense,  initialMinDamage,  initialMaxDamage, initialCritChance,  initialBlockChance,  initialAbsorbChance,
                initialMaxPhysicalPoints,  initialMaxMagicPoints,  initialIntelligence,  initialMagicDefence,  changeLife,  changeDefense,  changeMinDamage,  changeMaxDamage,
                changeCritChance,  changeBlockChance,  changeAbsorbChance,  changeMaxPhysicalPoints,  changeMaxMagicPoints,  changeIntelligence,  changeMagicDefence);
        return human;
    }
}
