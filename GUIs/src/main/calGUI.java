package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class calGUI extends JFrame
{
	private JTextField jtfn = new JTextField();
	public JTextField createPN()
	{
		jtfn.setText("0.");
		jtfn.addMouseListener
		(
			new MouseAdapter()
			{
				public void mouseClicked(MouseEvent e)
				{
					jtfn.setText("");
				}
			}
		); 
		return jtfn;
	}

	private JPanel createPC()
	{
		JPanel pc = new JPanel();
		pc.setLayout(new BorderLayout());
		pc.add(createPCN(), BorderLayout.NORTH);
		pc.add(createPCC(), BorderLayout.CENTER);
		return pc;
	}
	
	private JPanel createPCN()
	{
		String bcn_label[] = {"BackSpace", "CE", "AC"};
		JPanel pcn = new JPanel();
		pcn.setLayout(new GridLayout(1, 3));
		JButton bcn[] = new JButton[3];
		for (int i = 0; i < 3; i++)
		{
			bcn[i] = new JButton(bcn_label[i]);
			bcn[i].setForeground(Color.red);
			pcn.add(bcn[i]);
		}
		bcn[0].addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String tmp = jtfn.getText();
					if (tmp.length() != 0) jtfn.setText(tmp.substring(0, tmp.length()-1));
				}
			}
		);
		bcn[1].addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					jtfn.setText("0.");
				}
			}
		);
		bcn[2].addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					jtfn.setText("0.");
				}
			}
		);
		return pcn;
	}
	private JPanel createPCC()
	{
		JPanel pcc = new JPanel();
		String bcc_label[][] = {{"0", "1", "2", "3"}, {"4", "5", "6", "7"}, {"8", "9", ".", "="}, {"+", "-", "*", "/"}};
		pcc.setLayout(new GridLayout(4, 4));
		JButton bcc[][] = new JButton[4][4];
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				String num = bcc_label[i][j];
				bcc[i][j] = new JButton(num);
				pcc.add(bcc[i][j]);
				bcc[i][j].addActionListener
				(
					new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							if (jtfn.getText().equals("0.")) jtfn.setText(num);
							else jtfn.setText(jtfn.getText() + num);
						}
					}
				);
			}
		}
		return pcc;
	}

	public calGUI()
	{
		this.setTitle("¼ÆËãÆ÷");
		this.setLayout(new BorderLayout());
		this.add(createPN(), BorderLayout.NORTH);
		this.add(createPC(), BorderLayout.CENTER);
		this.setLocation(500, 200);
		this.setSize(500, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(3);
		this.setResizable(false);
	}

	public static void main(String[] args)
	{
		new calGUI();
	}
}
