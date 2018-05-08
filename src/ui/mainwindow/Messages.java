package ui.mainwindow;

import java.awt.GridLayout;
import java.util.Vector;
import javax.swing.*;

public class Messages extends JPanel
{
	private JTextArea text;
	private JScrollPane scrollPane;

	public Messages()
	{
		super();
		text = new JTextArea();
		text.setColumns(200);
		text.setRows(6);
		scrollPane = new JScrollPane(text);
		add(scrollPane);
	}
	
	public void addMessage(String s)
	{
		text.append(s + '\n');
	}
}
