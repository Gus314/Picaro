package control;

import ui.MainWindow;

import javax.swing.*;

public class Controller 
{
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
