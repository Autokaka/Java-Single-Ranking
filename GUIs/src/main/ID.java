package main;
import java.awt.*;
import javax.swing.*;

import javafx.scene.control.PasswordField;

public class ID
{
	public static void main(String[] args)
	{
		int curx = 0, cury = 0;
		JFrame jf = new JFrame("������Ϣ��");
		jf.setLayout(new GridBagLayout());
		
		//����
		JPanel jpl1 = new JPanel();
		jpl1.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpl1.add(new JLabel("����        "));
	    jpl1.add(new JTextField(12));
	    jf.add(jpl1, new GBC(curx++, cury).setFill(GBC.BOTH).setInsets(0, 0, 5, 50));
		
		//����
	    JPanel jpl2 = new JPanel();
	    jpl2.setLayout(new FlowLayout(FlowLayout.LEFT));
		JComboBox birthy = new JComboBox();
		JComboBox birthm = new JComboBox();
		JComboBox birthd = new JComboBox();
		for (int y = 1990; y <= 2020; y++) birthy.addItem(y);
		for (int m = 1; m <= 12; m++) birthm.addItem(m);
		for (int d = 1; d <= 30; d++) birthd.addItem(d);
		jpl2.add(new JLabel("����"));
		jpl2.add(birthy); 
		jpl2.add(new JLabel("��"));
		jpl2.add(birthm);
		jpl2.add(new JLabel("��"));
		jpl2.add(birthd);
		jpl2.add(new JLabel("��"));
		jf.add(jpl2, new GBC(curx++, cury++).setFill(GBC.BOTH).setInsets(0, 0, 5, 0));
		
		//����
		curx = 0;
		JPanel jpl3 = new JPanel();
		jpl3.setLayout(new GridLayout(2, 1));
		JPanel mima = new JPanel();
		mima.setLayout(new FlowLayout(FlowLayout.LEFT));
		mima.add(new JLabel("����        "));
		mima.add(new JPasswordField(12));
		jpl3.add(mima);
		JPanel queren = new JPanel();
		queren.setLayout(new FlowLayout(FlowLayout.LEFT));
		queren.add(new JLabel("ȷ������"));
		queren.add(new JPasswordField(12));
		jpl3.add(queren);
		jf.add(jpl3, new GBC(curx++, cury).setFill(GBC.BOTH).setInsets(0, 0, 5, 50));
		
		//��Ȥ
		JPanel jpl4 = new JPanel();
		jpl4.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpl4.add(new JLabel("��Ȥ"));
		JPanel intr = new JPanel();
		intr.setBorder(BorderFactory.createTitledBorder(""));
		intr.setLayout(new GridLayout(3, 1));
		intr.add(new JCheckBox("�Ķ�"));
		intr.add(new JCheckBox("����"));
		intr.add(new JCheckBox("����"));
		jpl4.add(intr);
		jf.add(jpl4, new GBC(curx++, cury++).setFill(GBC.BOTH).setInsets(0, 0, 5, 0));
		
		//�Ա�
		curx = 0;
		JPanel jpl5 = new JPanel();
		jpl5.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpl5.add(new JLabel("�Ա�        "));
		JRadioButton male = new JRadioButton("��");
		JRadioButton female = new JRadioButton("Ů");
		ButtonGroup sexchoice = new ButtonGroup();
		sexchoice.add(male);
		sexchoice.add(female);//��ButtonGroup��ɵ�ѡģʽ
		JPanel sex = new JPanel();
		sex.setBorder(BorderFactory.createTitledBorder(""));
		sex.setLayout(new GridLayout(2, 1));
		sex.add(male);
		sex.add(female);
		jpl5.add(sex);
		jf.add(jpl5, new GBC(curx++, cury).setFill(GBC.BOTH).setInsets(0, 0, 5, 50));
		
		//��ע
		JPanel jpl6 = new JPanel();
		JTextArea jta = new JTextArea(3, 20);
		JScrollPane jsp = new JScrollPane(jta);
		jpl6.add(jsp);
		jpl6.setLayout(new FlowLayout(FlowLayout.LEFT));
		jf.add(jpl6, new GBC(curx, cury++).setFill(GBC.BOTH).setInsets(0, 0, 5, 0));
		
		//ע��
		curx = 0;
		JPanel jpl7 = new JPanel();
		jpl7.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jpl7.add(new JButton("ע��"));
		jf.add(jpl7, new GBC(curx++, cury).setFill(GBC.BOTH).setInsets(10, 0, 0, 20));
		
		//����
		JPanel jpl8 = new JPanel();
		jpl8.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpl8.add(new JButton("����"));
		jf.add(jpl8, new GBC(curx, cury).setFill(GBC.BOTH).setInsets(10, 0, 0, 0));
		
		
		//Ӧ�øÿ��
		jf.setSize(500, 320);
		jf.setLocation(500, 200);
		jf.setVisible(true);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(3);
	}
}
