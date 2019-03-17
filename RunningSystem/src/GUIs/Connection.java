package GUIs;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import RunningRecordOperator.*;

public class Connection{
	private Account ac = new Account();
	
	private JFrame frame = new JFrame("登录");
	private Container c = frame.getContentPane();
	private JTextField username = new JTextField();
	private JPasswordField password = new JPasswordField();
	private JButton ok = new JButton("确定");
	private JButton cancel = new JButton("取消");
	private JLabel mainLabel = new JLabel("欢迎来到阳光长跑系统！请登录！");
	private String sno;
	private String pw;
	
	public Connection(){
		frame.setSize(300,200);
		frame.setLocationRelativeTo(null);
		c.setLayout(new BorderLayout());
		initFrame();
		frame.setVisible(true);
	}
	
	private void initFrame(){
		//顶部
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(mainLabel);
		c.add(titlePanel,"North");

		//中部表单
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(null);
		JLabel a1 = new JLabel("用户名:");
		a1.setBounds(50,20,50,20);
		JLabel a2 = new JLabel("密  码:");
		a2.setBounds(50,60,50,20);
		fieldPanel.add(a1);
		fieldPanel.add(a2);
		username.setBounds(110,20,120,20);
		password.setBounds(110,60,120,20);
		fieldPanel.add(username);
		fieldPanel.add(password);
		c.add(fieldPanel,"Center");
		
		//底部按钮
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(ok);
		buttonPanel.add(cancel);
		c.add(buttonPanel,"South");
		
		password.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e)
			{
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
				{
					sno = username.getText();
					pw  = new String(password.getPassword());
					int identity;
					
					try {
						if(ac.getIdentity(sno,pw) != -1) {
							identity =ac.getIdentity(sno,pw);
							if(identity == 1) {
								frame.setVisible(false);
								frame.dispose();
								new adminGUI(sno);
								JOptionPane.showMessageDialog(null,"亲爱的管理员，欢迎来到本系统！","Welcome!", JOptionPane.INFORMATION_MESSAGE); 
							}
							else if(identity == 0) {
								frame.setVisible(false);
								frame.dispose();
								new stuGUI(sno);
								JOptionPane.showMessageDialog(null,"亲爱的同学，欢迎来到本系统！","Welcome!", JOptionPane.INFORMATION_MESSAGE);
							}
						}
						else mainLabel.setText("密码错误或账号不存在！");
					} catch (SQLException e1) {
						mainLabel.setText("账号不存在！");
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sno = username.getText();
				pw  = new String(password.getPassword());
				int identity;
				
				try {
					if(ac.getIdentity(sno,pw) != -1) {
						identity =ac.getIdentity(sno,pw);
						if(identity == 1) {
							frame.setVisible(false);
							frame.dispose();
							new adminGUI(sno);
							JOptionPane.showMessageDialog(null,"亲爱的管理员，欢迎来到本系统！","Welcome!", JOptionPane.INFORMATION_MESSAGE); 
						}
						else if(identity == 0) {
							frame.setVisible(false);
							frame.dispose();
							new stuGUI(sno);
							JOptionPane.showMessageDialog(null,"亲爱的同学，欢迎来到本系统！","Welcome!", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else mainLabel.setText("密码错误或账号不存在！");
				} catch (SQLException e1) {
					mainLabel.setText("账号不存在！");
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
			
		
	}
	
	public static void main(String[] args){
		new Connection();
	}
}
