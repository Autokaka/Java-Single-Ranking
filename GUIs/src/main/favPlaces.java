package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class favPlaces extends JFrame
{
	private String retname = "�", retfav = "____";
	private JLabel ret = new JLabel(retname + "����ȥ�ĵط���" + retfav);
	favPlaces()
	{
		this.setLayout(new GridLayout(2, 1));
		
		//����
		JPanel jpl1 = new JPanel();
		jpl1.add(new JLabel("����"));
		JComboBox name = new JComboBox();
		String names[] = {"�", "��Ҳ", "ͯ��"};
		for (int i = 0; i < 3; i++) name.addItem(names[i]);
		jpl1.add(name);
		name.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					retname = names[name.getSelectedIndex()];
					ret.setText(retname + "����ȥ�ĵط���" + retfav);
				}
			}
		);
		
		//����ȥ�ĵط�
		String list[] = {"����ȥ�ĵط�", "����", "���ױ���", "����", "����"};
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
					ret.setText(retname + "����ȥ�ĵط���" + retfav);
				}
			}
		);
		this.add(jpl1);
		
		//�������
		JPanel jpl2 = new JPanel();
		jpl2.add(ret);
		this.add(jpl2);
		
		//Ӧ�õ����
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
