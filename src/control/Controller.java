package control;

import ui.RootFrame;

import java.util.Random;

public class Controller 
{
	private static Random generator = new Random();

	public static Random getGenerator(){return generator;}

	public static void main(String args[])
	{
		RootFrame rootFrame = new RootFrame();
        rootFrame.changeToTitleScreen();
	}
}
