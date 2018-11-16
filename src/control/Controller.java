package control;

import ui.RootFrame;

import javax.swing.*;
import java.applet.Applet;
import java.util.Random;

public class Controller
{
	private static Random generator = new Random();

	public static Random getGenerator(){return generator;}

	public static final boolean bigFonts = true;

	public static void main(String args[])
	{
		try
		{
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
			{
				if ("Nimbus".equals(info.getName()))
				{
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}
		catch (Exception e) {;}

		RootFrame rootFrame = new RootFrame();
        rootFrame.changeToTitleScreen();
	}
}
