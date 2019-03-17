package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class favPlaces extends JFrame
{
	private String retname = "李静", retfav = "____";
	private JLabel ret = new JLabel(retname + "最想去的地方是" + retfav);
	favPlaces()
	{
		this.setLayout(new GridLayout(2, 1));
		
		//姓名
		JPanel jpl1 = new JPanel();
		jpl1.add(new JLabel("姓名"));
		JComboBox name = new JComboBox();
		String names[] = {"李静", "张也", "童铭"};
		for (int i = 0; i < 3; i++) name.addItem(names[i]);
		jpl1.add(name);
		name.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					retname = names[name.getSelectedIndex()];
					ret.setText(retname + "最想去的地方是" + retfav);
				}
			}
		);
		
		//最想去的地方
		String list[] = {"最想去的地方", "丽江", "呼伦贝尔", "深圳", "北海"};
		JList fav = new JList(list);
		jpl1.add(fav);
		fav.addMouseListener
		(
			new MouseAdapter()
			{
				public void mousePressed(MouseEvent e)
				{
					if (fav.getSelectedIndex() == 0) retfav = "";
					else retfav = list[fav.getSelectedIndex()];
					ret.setText(retname + "最想去的地方是" + retfav);
				}
			}
		);
		this.add(jpl1);
		
		//反馈结果
		JPanel jpl2 = new JPanel();
		jpl2.add(ret);
		this.add(jpl2);
		
		//应用到框架
		this.setLocation(500, 200);
		this.setSize(250, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(3);
		this.setResizable(false);
	}
	
	public static void main(String[] args)
	{
		new favPlaces();
	}
}
