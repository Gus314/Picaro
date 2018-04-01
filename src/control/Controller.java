package control;

import ui.MainWindow;

import javax.swing.*;
import java.util.Random;

public class Controller 
{
	private static Random generator = new Random();

	public static Random getGenerator(){return generator;}

	public static void main(String args[])
	{
		MainWindow mw = new MainWindow();
		JFrame frame = new JFrame();
		frame.setContentPane(mw);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
