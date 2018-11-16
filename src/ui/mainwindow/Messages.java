package ui.mainwindow;

import java.awt.GridLayout;
import java.util.Vector;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class Messages extends JPanel
{
	private JTextArea text;
	private JScrollPane scrollPane;

	public Messages()
	{
		super();
		text = new JTextArea();
		text.setColumns(200);
		text.setRows(5);
		scrollPane = new JScrollPane(text);
		add(scrollPane);

		// Ensure text always scrolls to bottom.
		DefaultCaret caret = (DefaultCaret)text.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	}
	
	public void addMessage(String s)
	{
		text.append(s + '\n');
	}
}
