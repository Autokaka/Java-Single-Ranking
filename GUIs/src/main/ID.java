package main;
import java.awt.*;
import javax.swing.*;

import javafx.scene.control.PasswordField;

public class ID
{
	public static void main(String[] args)
	{
		int curx = 0, cury = 0;
		JFrame jf = new JFrame("个人信息表");
		jf.setLayout(new GridBagLayout());
		
		//姓名
		JPanel jpl1 = new JPanel();
		jpl1.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpl1.add(new JLabel("姓名        "));
	    jpl1.add(new JTextField(12));
	    jf.add(jpl1, new GBC(curx++, cury).setFill(GBC.BOTH).setInsets(0, 0, 5, 50));
		
		//生日
	    JPanel jpl2 = new JPanel();
	    jpl2.setLayout(new FlowLayout(FlowLayout.LEFT));
		JComboBox birthy = new JComboBox();
		JComboBox birthm = new JComboBox();
		JComboBox birthd = new JComboBox();
		for (int y = 1990; y <= 2020; y++) birthy.addItem(y);
		for (int m = 1; m <= 12; m++) birthm.addItem(m);
		for (int d = 1; d <= 30; d++) birthd.addItem(d);
		jpl2.add(new JLabel("生日"));
		jpl2.add(birthy); 
		jpl2.add(new JLabel("年"));
		jpl2.add(birthm);
		jpl2.add(new JLabel("月"));
		jpl2.add(birthd);
		jpl2.add(new JLabel("日"));
		jf.add(jpl2, new GBC(curx++, cury++).setFill(GBC.BOTH).setInsets(0, 0, 5, 0));
		
		//密码
		curx = 0;
		JPanel jpl3 = new JPanel();
		jpl3.setLayout(new GridLayout(2, 1));
		JPanel mima = new JPanel();
		mima.setLayout(new FlowLayout(FlowLayout.LEFT));
		mima.add(new JLabel("密码        "));
		mima.add(new JPasswordField(12));
		jpl3.add(mima);
		JPanel queren = new JPanel();
		queren.setLayout(new FlowLayout(FlowLayout.LEFT));
		queren.add(new JLabel("确认密码"));
		queren.add(new JPasswordField(12));
		jpl3.add(queren);
		jf.add(jpl3, new GBC(curx++, cury).setFill(GBC.BOTH).setInsets(0, 0, 5, 50));
		
		//兴趣
		JPanel jpl4 = new JPanel();
		jpl4.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpl4.add(new JLabel("兴趣"));
		JPanel intr = new JPanel();
		intr.setBorder(BorderFactory.createTitledBorder(""));
		intr.setLayout(new GridLayout(3, 1));
		intr.add(new JCheckBox("阅读"));
		intr.add(new JCheckBox("唱歌"));
		intr.add(new JCheckBox("跳舞"));
		jpl4.add(intr);
		jf.add(jpl4, new GBC(curx++, cury++).setFill(GBC.BOTH).setInsets(0, 0, 5, 0));
		
		//性别
		curx = 0;
		JPanel jpl5 = new JPanel();
		jpl5.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpl5.add(new JLabel("性别        "));
		JRadioButton male = new JRadioButton("男");
		JRadioButton female = new JRadioButton("女");
		ButtonGroup sexchoice = new ButtonGroup();
		sexchoice.add(male);
		sexchoice.add(female);//用ButtonGroup变成单选模式
		JPanel sex = new JPanel();
		sex.setBorder(BorderFactory.createTitledBorder(""));
		sex.setLayout(new GridLayout(2, 1));
		sex.add(male);
		sex.add(female);
		jpl5.add(sex);
		jf.add(jpl5, new GBC(curx++, cury).setFill(GBC.BOTH).setInsets(0, 0, 5, 50));
		
		//备注
		JPanel jpl6 = new JPanel();
		JTextArea jta = new JTextArea(3, 20);
		JScrollPane jsp = new JScrollPane(jta);
		jpl6.add(jsp);
		jpl6.setLayout(new FlowLayout(FlowLayout.LEFT));
		jf.add(jpl6, new GBC(curx, cury++).setFill(GBC.BOTH).setInsets(0, 0, 5, 0));
		
		//注册
		curx = 0;
		JPanel jpl7 = new JPanel();
		jpl7.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jpl7.add(new JButton("注册"));
		jf.add(jpl7, new GBC(curx++, cury).setFill(GBC.BOTH).setInsets(10, 0, 0, 20));
		
		//重置
		JPanel jpl8 = new JPanel();
		jpl8.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpl8.add(new JButton("重置"));
		jf.add(jpl8, new GBC(curx, cury).setFill(GBC.BOTH).setInsets(10, 0, 0, 0));
		
		
		//应用该框架
		jf.setSize(500, 320);
		jf.setLocation(500, 200);
		jf.setVisible(true);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(3);
	}
}
