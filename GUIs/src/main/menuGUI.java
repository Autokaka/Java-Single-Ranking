package main;
import javax.swing.*;
import java.awt.event.*;
import java.time.chrono.JapaneseEra;
import java.awt.*;

public class menuGUI extends JFrame
{
	private JMenu menuFile()
	{
		JMenu mf = new JMenu("File");
		String fileItem[] = {"new", "open file...", "close", "close all", "save", "save as...", "save all"};
		for (int i = 0; i < fileItem.length; i++)
		{
			mf.add(new JMenuItem(fileItem[i]));
			if (i == 1 || i == 3) mf.addSeparator();
		}
		JMenuItem exit = new JMenuItem("exit");
		mf.add(exit);
		exit.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.exit(0);
				}
			}
		);
		return mf;
	}
	
	private JMenu menuEdit()
	{
		JMenu me = new JMenu("Edit");
		me.add(new JMenuItem("Undo Typing"));
		JMenuItem redo = new JMenuItem("Redo");
		redo.setEnabled(false);
		me.add(redo);
		me.addSeparator();
		JMenuItem cut = new JMenuItem("Cut");
		cut.setEnabled(false);
		me.add(cut);
		return me;
	}
	
	private JMenu menuSource()
	{
		JMenu ms = new JMenu("Source");
		String fileItem[] = {"Shift Right", "Shift Left", "Correct Indentation", "Format"};
		for (int i = 0; i < fileItem.length; i++) ms.add(new JMenuItem(fileItem[i]));
		return ms;
	}
	
	menuGUI()
	{
		JMenuBar mb = new JMenuBar();
		mb.add(menuFile());
		mb.add(menuEdit());
		mb.add(menuSource());
		this.setTitle("Eclipse 8012-10-27");
		this.setJMenuBar(mb);
		this.setLocation(500, 200);
		this.setSize(500, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(3);
	}
	
	public static void main(String args[])
	{
		new menuGUI();
	}
}
