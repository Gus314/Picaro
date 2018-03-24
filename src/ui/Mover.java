package ui;

import control.TurnHandler;

import java.awt.event.*;

public class Mover implements KeyListener
{
	public TurnHandler turnHandler;

	public Mover(TurnHandler turnHandler)
	{
		this.turnHandler = turnHandler;
	}

	public void keyPressed(KeyEvent ke)
	{
		int code = ke.getKeyCode();

		if(code == KeyEvent.VK_A)
		{
			turnHandler.moveLeft();
		}
		else if(code == KeyEvent.VK_D)
		{
			turnHandler.moveRight();
		}
		else if(code == KeyEvent.VK_W)
		{
			turnHandler.moveUp();
		}
		else if(code == KeyEvent.VK_S)
		{
			turnHandler.moveDown();
		}
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
