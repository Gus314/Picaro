package enums;

public class EnumConversions
{
    public static MapDisplayMode toMapDisplayMode(TargetType targetType)
    {
        switch(targetType)
        {
            case AREA:
            {
                return MapDisplayMode.AREA;
            }
            case TARGET:
            {
                return MapDisplayMode.TARGET;
            }
            case SELF:
            {
                return MapDisplayMode.NORMAL;
            }
            case FLOOR:
            {
                return MapDisplayMode.FLOOR;
            }
            default:
            {
                System.out.println("EnumConversions::toMapDisplayMode - unexpected TargetType.");
                return MapDisplayMode.NORMAL;
            }
        }
    }
}
