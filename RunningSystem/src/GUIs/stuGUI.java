package GUIs;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import RunningRecordOperator.*;


public class stuGUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private RunningRecordOperator record = new RunningRecordOperator();
	private Account ac = new Account();
	private StuInfo stu = new StuInfo();
	private JLabel cp3=new JLabel("2");
	private JLabel cp4=new JLabel("1");
	private JPanel center=new JPanel();
	
	public final String sno;
	
	//������Ϣ���ֵ���ʾ
	public JPanel createpn() throws Exception {
		JPanel jp=new JPanel();//��һ�е���ʾ
		String [] info= stu.numberSelect(sno);
		
		jp.setLayout(new BorderLayout());
		JLabel jlb1  =new JLabel("ѧУ:");
		JLabel jlb1p =new JLabel(info[0]);
		JLabel jlb2  =new JLabel(" ѧԺ:");
		JLabel jlb2p =new JLabel(info[3]);
		JLabel jlb3  =new JLabel(" �༶:");
		JLabel jlb3p =new JLabel(info[4]);
		JPanel jp1=new JPanel();
		jp1.add(jlb1);
		jp1.add(jlb1p);
		jp1.add(jlb2);
		jp1.add(jlb2p);
		jp1.add(jlb3);
		jp1.add(jlb3p);
		jp.add(jp1,BorderLayout.NORTH);
		
		
		JPanel jp2=new JPanel();//�ڶ��е���ʾ
		JLabel jlb4  =new JLabel("ѧ��:");
		JLabel jlb4p =new JLabel(sno);
		JLabel jlb5  =new JLabel(" ����:");
		JLabel jlb5p =new JLabel(info[2]);
		jp2.add(jlb4);
		jp2.add(jlb4p);
		jp2.add(jlb5);
		jp2.add(jlb5p);
		jp.add(jp2,BorderLayout.CENTER);


		FlowLayout flowlayout2=new FlowLayout(FlowLayout.RIGHT);
		JPanel jp3=new JPanel();//���������У����ó��ܴ����ͳ��ܴ���
		jp3.setLayout(new GridLayout(2,2));
		JLabel cp1=new JLabel("���ܴ���");
		JLabel cp2=new JLabel("��Ч��¼");
		JPanel jp4=new JPanel();
		jp4.setLayout(flowlayout2);
		jp4.add(cp2);

		JPanel jp5=new JPanel();
		jp5.setLayout(flowlayout2);
		jp5.add(cp4);
		
		jp3.add(cp1);
		jp3.add(jp4);
		jp3.add(cp3);
		jp3.add(jp5);
		
		jp.add(jp3,BorderLayout.SOUTH);

		return jp;
	}
	
	
	private JTable table;
	private Object [][] obj;
	
	public JPanel createpc() throws SQLException {
		JPanel jp=new JPanel();
		String[] columnNames= {"����","��Ч·��","��ʱ","�ٶ�","��Ч��"};
		obj=data();
		table=new JTable(obj,columnNames);
		table.setEnabled(false);
		JScrollPane scrollTable=new JScrollPane(table);
		jp.add(scrollTable);
		return jp;
	}
	
	//��ʼ�ܲ� ���ڵĴ���
	public void createRunDialog() {
		JDialog result = new JDialog(this,"���ܼ�¼",true);
		result.setSize(350,185);
		result.setLayout(new GridLayout(4,2,5,5));
		result.setLocationRelativeTo(null);
		
		JPanel pnofDate = new JPanel();
		pnofDate.add(new JLabel("���ڣ�"));
		JTextField tfofDate = new JTextField();
		JPanel pnofDistance = new JPanel();
		pnofDistance.add(new JLabel("��Ч���룺"));
		JTextField tfofDistance = new JTextField();
		tfofDistance.setText("2000.0");
		JPanel pnoftime = new JPanel();
		pnoftime.add(new JLabel("��ʱ��"));
		JPanel pnofTime = new JPanel();
		JLabel sperater1 = new JLabel(":");
		JLabel sperater2 = new JLabel(":");
		JComboBox<Object> hor = new JComboBox<Object>();
		JComboBox<Object> min = new JComboBox<Object>();
		JComboBox<Object> sec = new JComboBox<Object>();
		JButton OK = new JButton("ȷ��");
		JButton Cancel = new JButton("ȡ��");
		
		//����������������panel�ĳ�ʼ��
		for(int i=0; i<=59; i++) {
			hor.addItem((i<10)?("0"+i):(""+i));
			min.addItem((i<10)?("0"+i):(""+i));
			sec.addItem((i<10)?("0"+i):(""+i));
		}
		hor.addItem("60");
		
		pnofTime.add(hor);
		pnofTime.add(sperater1);
		pnofTime.add(min);
		pnofTime.add(sperater2);
		pnofTime.add(sec);
		
		//��tfofDate�ĳ�ʼ��
		int y,m,d;
		Calendar cal = Calendar.getInstance();
		y = cal.get(Calendar.YEAR);
		m = cal.get(Calendar.MONTH) + 1;
		d = cal.get(Calendar.DATE);
		String date = y + "-" + ((m<10)?("0"+m):(""+m)) + "-" + ((d<10)?("0"+d):(""+d));
		tfofDate.setText(date);
		tfofDate.setEditable(false);
		
		//��ť�¼�����
		OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				String date,distance,time;
				Object[] a = new Object[4];
				a[0] = sno;
				a[1] = tfofDate.getText();
				a[2] = tfofDistance.getText();
				a[3] = hor.getSelectedItem() + ":" + min.getSelectedItem() + ":" + (Integer.parseInt(sec.getSelectedItem().toString()) - 1);
				
				try {
						record.InsertRecord(a);
						JOptionPane.showMessageDialog(null,"�ɹ���","��ʾ", JOptionPane.INFORMATION_MESSAGE);
						result.setVisible(false);
						result.dispose();
						setVisible(true);
				} catch (HeadlessException e1) {
					JOptionPane.showMessageDialog(null,"ʧ�ܣ�"+e1.getMessage(),"��ʾ", JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,"ʧ�ܣ�"+e1.getMessage(),"��ʾ", JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		
		Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result.setVisible(false);
				result.dispose();
			}
		});
		
		result.add(pnofDate);
		result.add(tfofDate);
		result.add(pnofDistance);
		result.add(tfofDistance);
		result.add(pnoftime);
		result.add(pnofTime);
		result.add(OK);
		result.add(Cancel);

		result.setVisible(true);
	}
	
	//������������
	public void createFbDialog() {
		JDialog result = new JDialog(this,"����",true);
		result.setLayout(new BorderLayout(5,5));
		result.setSize(300,200);
		JLabel mainLabel = new JLabel("��ӭ�������ǵ�ϵͳ�������������");
		JPanel pn = new JPanel();
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pn.add(mainLabel);
		JTextArea feedback = new JTextArea();
		JPanel button = new JPanel();
		JButton submit = new JButton("�ύ");
//		JButton cancel = new JButton("ȡ��");
		JRadioButton[] star = new JRadioButton[5];
		JPanel stars = new JPanel();
		JLabel loStars = new JLabel("ѡ���Ǽ���");
		
		//�Ǽ�
		stars.add(loStars);
		ActionListener starSelected = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = 4;
				for(int i = 0; i<5; i++) {
					if(e.getSource() == star[i]) {
						for(int j=0; j<5; j++) {	
							star[j].setSelected(false);
						}
						
						for(int j=0; j<=i; j++) {
							star[j].setSelected(true);
						}
						n = i;
					}
				}
				
				switch(n) {
				case 0:
					feedback.setText("��̫����ˡ���");
					break;
				case 1:
					feedback.setText("����ǿǿ��");
					break;
				case 2:
					feedback.setText("������ԣ�����");
					break;
				case 3:
					feedback.setText("�治��");
					break;
				case 4:
					feedback.setText("���ϵͳ����ʵ����̫����������");
					break;
				}
			}
		};
		
		int i;
		for(i = 0; i<5; i++) {
			star[i] = new JRadioButton("");
			stars.add(star[i]);
			star[i].setSelected(true);
			star[i].addActionListener(starSelected);
		}
		
		JPanel pc = new JPanel();
		pc.setLayout(new BorderLayout(5,5));
		pc.add(stars,BorderLayout.NORTH);
		pc.add(feedback,BorderLayout.CENTER);

		button.add(submit);
		
		feedback.setText("���ϵͳ����ʵ����̫����������");
		
		feedback.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if(feedback.getText().equals("���ϵͳ����ʵ����̫����������") 
				 ||feedback.getText().equals("�治��")
				 ||feedback.getText().equals("������ԣ�����")
				 ||feedback.getText().equals("����ǿǿ��")
				 ||feedback.getText().equals("��̫����ˡ���")){
					feedback.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if(feedback.getText().equals("")) {
					feedback.setText("���ϵͳ����ʵ����̫����������");
				}
			}
			
		});
		
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fb = feedback.getText();
				try {
					int i;
					for(i=4; i>=0; i--) {
						if(star[i].isSelected())
							break;
					}
					i++;
					if(i==1) {
						int res1=JOptionPane.showConfirmDialog(null, "QAQ\n���Ҫ��һ�����𣿣�", "QAQ?!", JOptionPane.YES_NO_OPTION);
						if(res1==JOptionPane.YES_OPTION){ 
							ac.feedback(sno, i,fb);
							JOptionPane.showMessageDialog(null,"�ɹ�����QvQ","��ʾ", JOptionPane.INFORMATION_MESSAGE);
							setVisible(true);    //������ǡ���ִ����������
						}else{
							//������񡱺�ִ����������
							return;
						} 
					}else {
						ac.feedback(sno, i,fb);
						JOptionPane.showMessageDialog(null,"�ɹ���","��ʾ", JOptionPane.INFORMATION_MESSAGE);
						setVisible(true);
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,"�ϴ�ʧ�ܣ�"+e1.getMessage(),"��ʾ", JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		
		result.add(mainLabel,BorderLayout.NORTH);
		result.add(pc,BorderLayout.CENTER);
		result.add(button,BorderLayout.SOUTH);
		result.setLocationRelativeTo(null);
		result.setVisible(true);
	}
	
	//�������ڴ���
	public void createAboutDialog() {
		JDialog result = new JDialog(this,"����",true);
		
		result.setSize(375,230);
		
		JPanel west = new JPanel();
		ImageIcon img = new ImageIcon(this.getClass().getResource("/imgs/Shit.png"));
		JLabel Shit = new JLabel(img);
		west.add(Shit);
		
		JPanel east = new JPanel();
		east.setLayout(new GridLayout(5,1,5,5));
		
		JLabel[] labels = new JLabel[4];
		labels[0] = new JLabel("�汾��     Beta:1.1");
		labels[1] = new JLabel("�����飺��ʺ�ɱ��");
		labels[2] = new JLabel("GUIs��    ³�ġ����ƺơ���ϯ��");
		labels[3] = new JLabel("���ݿ⣺���Ϳ�������");
		
		for(int i=0; i<4; i++)
			east.add(labels[i]);
		
		JPanel south = new JPanel();
		JButton ok = new JButton("ȷ��");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result.setVisible(false);
				result.dispose();
			}
		});
		south.add(ok);
		
		result.add(west, BorderLayout.WEST);
		result.add(east, BorderLayout.EAST);
		result.add(south,BorderLayout.SOUTH);
		
		result.setLocationRelativeTo(null);
		result.setVisible(true);
	}
	
	//����Ͷʳ����
	public void createBegDialog() {
		JDialog result = new JDialog(this,"��ӭͶʳ����",true);
		
		result.setSize(325,300);
		result.setLocationRelativeTo(null);
		
		JLabel mainLabel = new JLabel("���˱���ˣ���֪�������������(��_���d");
		JPanel north = new JPanel();
		result.setLayout(new BorderLayout(5,5));
		north.add(mainLabel);
		ImageIcon img = new ImageIcon(this.getClass().getResource("/imgs/myQRCode.png"));
		JLabel QRC = new JLabel(img);

		result.add(north,BorderLayout.NORTH);
		result.add(QRC,BorderLayout.CENTER);
		result.setVisible(true);
	}
	
	public JPanel createps() {
		JPanel jp=new JPanel();
		FlowLayout flowlayout=new FlowLayout(FlowLayout.LEFT);
		FlowLayout flowlayout2=new FlowLayout(FlowLayout.RIGHT);
		jp.setLayout(new GridLayout(1,2));
		
		JButton jbt1=new JButton("��ʼ�ܲ�");
		jbt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createRunDialog();
			}
		});
		
		JButton jbt4=new JButton("ˢ��");
		jbt4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				center.removeAll();
				try {
					center.add(createpc());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton jbt2=new JButton("����");
		jbt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createFbDialog();
				
			}
		});
		
		JButton jbt3=new JButton("����");
		jbt3.addActionListener(new ActionListener() {
			int cnt=0;
			public void actionPerformed(ActionEvent e) {
				if(cnt<3) {
					cnt++;
					createAboutDialog();
				}
				else if(cnt>=3) {
					cnt++;
					createBegDialog();
				}
			}
		});
		
		JButton jbt5=new JButton("�˳���¼");
		jbt5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int res1=JOptionPane.showConfirmDialog(null, "Ҫ�˳���¼��", "��ʾ", JOptionPane.YES_NO_OPTION);
				if(res1==JOptionPane.YES_OPTION){ 
					setVisible(false);
					dispose();
					new Connection();
				}else{
					//������񡱺�ִ����������
					return;
				} 
				
			}
		});
		
		JPanel jp1=new JPanel();
		jp1.setLayout(flowlayout);
		jp1.add(jbt1);
		jp1.add(jbt4);
		jp.add(jp1);
		
		
		JPanel jp2=new JPanel();
		jp2.setLayout(flowlayout2);
		jp2.add(jbt2);
		jp2.add(jbt3);
		jp2.add(jbt5);
		jp.add(jp2);
		return jp;
	}
	
	public Object[][] data() throws SQLException{
		Object obj[][] = record.getRecord(sno);
		int cnt = 0;
		for(int i = 0;i<obj.length;i++) {
			if(obj[i][4]=="��Ч") {
				cnt++;
			}
		}
		cp3.setText(""+obj.length);
		cp4.setText("" + cnt);
		return obj;
	}
	
	

	public stuGUI(String sno) throws Exception{//��ʼ����ʱ������ݿ������ݲ��ҳ�ʼ���ý���
		this.sno = sno;
		this.setTitle("ѧ������");
		this.setLayout(new BorderLayout());
		this.add(createpn(),BorderLayout.NORTH);
		center.add(createpc());
		this.add(center,BorderLayout.CENTER);
		this.add(createps(),BorderLayout.SOUTH);
		this.setSize(470, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	
	public static void main(String[] args) throws Exception {
		new stuGUI("1405170118");
	}
}
