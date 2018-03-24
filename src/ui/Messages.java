package ui;

import java.awt.GridLayout;
import java.util.Vector;
import javax.swing.*;

public class Messages extends JPanel
{
	private Vector<JLabel> messages;
	
	public Messages()
	{
		super();
		GridLayout grid = new GridLayout(5, 1);
		this.setLayout(grid);
		messages = new Vector<JLabel>();
	}
	
	public void addMessage(String s)
	{
		JLabel l = new JLabel(s);
		this.add(l);
		messages.add(l);
		if(messages.size() > 5)
		{

			this.remove(messages.remove(0));
		}
	}
}
