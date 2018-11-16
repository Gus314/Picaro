package entities.furniture;

import control.Coordinate;
import entities.creatures.Player;

import java.io.Serializable;
import java.util.Optional;

public class Teleporter extends Furniture implements Serializable
{
    public Teleporter(int inRow, int inColumn, String inName)
    {
        super('=', inRow, inColumn, inName);
    }

    @Override
    public void use(Player player)
    {
        if(!getUsed())
        {
            Optional<Coordinate> newPoint = player.getMap().findEmptyPoint();

            Coordinate destination = (newPoint.isPresent()) ? newPoint.get() : player.getPosition();

            player.setRow(destination.getRow());
            player.setColumn(destination.getColumn());
            player.getMessages().addMessage(player.getName() + " steps into the teleporter.");

            setUsed(true);
        }
        else
        {
            player.getMessages().addMessage("The teleporter is out of power.");
        }
    }
}