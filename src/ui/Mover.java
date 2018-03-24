package ui;

import control.TurnHandler;

import java.awt.event.*;
import enums.Direction;

public class Mover implements KeyListener
{
	public TurnHandler turnHandler;

	public Mover(TurnHandler turnHandler)
	{
		this.turnHandler = turnHandler;
	}

	public void keyPressed(KeyEvent ke)
	{
		Direction direction = Direction.LEFT;
		int code = ke.getKeyCode();

		if(code == KeyEvent.VK_NUMPAD4)
		{
			direction = Direction.LEFT;
		}
		else if(code == KeyEvent.VK_NUMPAD6)
		{
			direction = Direction.RIGHT;
		}
		else if(code == KeyEvent.VK_NUMPAD8)
		{
			direction = Direction.UP;
		}
		else if(code == KeyEvent.VK_NUMPAD2)
		{
			direction = Direction.DOWN;
		}
		else if(code == KeyEvent.VK_NUMPAD7)
		{
			direction = Direction.UPLEFT;
		}
		else if(code == KeyEvent.VK_NUMPAD9)
		{
			direction = Direction.UPRIGHT;
		}
		else if(code == KeyEvent.VK_NUMPAD1)
		{
			direction = Direction.DOWNLEFT;
		}
		else if(code == KeyEvent.VK_NUMPAD3)
		{
			direction = Direction.DOWNRIGHT;
		}
		else
		{
			// Do not move.
			return;
		}
		turnHandler.movePlayer(direction);
	}
	
	public void keyReleased(KeyEvent ke)
	{
		;		
	}
	
	public void keyTyped(KeyEvent ke)
	{
		;		
	}
}
