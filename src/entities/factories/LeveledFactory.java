package entities.factories;

public abstract class LeveledFactory extends AbstractEntityFactory
{
    public abstract int getMinLevel();
    public abstract int getMaxLevel();
}
