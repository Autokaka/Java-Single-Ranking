package main;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InvestcalGUI extends JFrame
{
	InvestcalGUI()
	{
		double ia, y, air, fv;
		this.setLayout(new GridLayout(5, 2));
		String label[] = {"Investment Amount", "Years", "Annual Interest Rate", "Future Value"};
		JPanel jpl[] = new JPanel[8];
		JTextField jtf[] = new JTextField[4];
		for (int i = 0; i < 8; i++)
		{
			jpl[i] = new JPanel();
			if (i%2 == 0)
			{
				jpl[i].add(new JLabel(label[i/2]));
				jpl[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			}					
			else
			{
				jtf[(i-1)/2] = new JTextField(10);
				jtf[(i-1)/2].setHorizontalAlignment(SwingConstants.RIGHT);
				jpl[i].add(jtf[(i-1)/2]);
				jpl[i].setLayout(new FlowLayout(FlowLayout.RIGHT));
			}
			this.add(jpl[i]);
		}
		jtf[3].setEditable(false);
		//calculate
		JPanel blk = new JPanel();
		this.add(blk);
		JPanel cal = new JPanel();
		JButton b1 = new JButton("Calculate");
		cal.add(b1);
		cal.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.add(cal);
		b1.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						//getText
						double ia = Double.parseDouble(jtf[0].getText());
						double y = Double.parseDouble(jtf[1].getText());
						double air = Double.parseDouble(jtf[2].getText());
						double fv = ia+ia*air*y;
						String tmp = fv + "";
						jtf[3].setText(tmp);
					}
				}
		);
		//apply-this
		this.setLocation(500, 200);
		this.setSize(300, 250);
		this.setDefaultCloseOperation(3);
		this.setVisible(true);
		this.setResizable(false);
	}
	public static void main(String[] args)
	{
		new InvestcalGUI();
	}
}
