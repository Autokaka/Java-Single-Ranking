package GUIs;

import java.util.*;
import java.util.Timer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.sql.*;
import RunningRecordOperator.*;

public class adminGUI extends JFrame
{
	private static final long serialVersionUID = 1L;
	RunningRecordOperator record;
	StuInfo student;
	Account account;
	
	//���캯��
	private final String sno;
	public adminGUI(String sno)
	{
		System.out.println("��ʼ����������...");
		record = new RunningRecordOperator();
		System.out.println("��ʼ��ѧ����...");
		student = new StuInfo();
		System.out.println("��ʼ���˻���Ϣ...");
		account = new Account();
		this.sno = sno;
		
		//BorderLayout.CENTER��JPanel(���)-���ݳ�ʼ��
		lt_name = "ȫ��";
		before = getData(lt_name);
		td = false;
		isrefreshed = false;
		isaddrow = false;
		isdelrow = false;
		
		//���Ϊ�����봢��·��(���Ϊ��ť��·��ѡ����ļ�������)-���ݳ�ʼ��
		isdefault = false;
		
		//���Ϊ�ĵ�λѡ��(��λ��ť����λ���������)-���ݳ�ʼ��
		expb_label = new String[]{"ѧУ", "ѧԺ", "�༶", "����"};
		
		//��λ���������-���ݳ�ʼ��
		expobj = null;
		expobj_name = "δ�������";
		pccjtf = new JTextField(20);
		
		this.setLayout(new BorderLayout());
		this.add(leftTable(), BorderLayout.CENTER);
		this.add(funcs(), BorderLayout.SOUTH);
		
		this.setTitle("��ǰ����Ա��" + sno);
		this.setSize(1000, 600);//������setsize
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(3);
	}
	
	//BorderLayout.CENTER��JPanel(���)
	private JTable lt;
	private String lt_name;//��ǰ��ʾ�ı�������ݿ��ڵ�����
	private boolean td, isrefreshed, isaddrow, isdelrow;
	private Object before[][];
	private JPanel leftTable()
	{
		JPanel ljpl = new JPanel();
		ljpl.setLayout(new BorderLayout());
		DefaultTableModel model = new DefaultTableModel(before, getDataColumn(lt_name));
		lt = new JTable(model);
		lt.getModel().addTableModelListener(new TableModelListener()
		{
			public void tableChanged(TableModelEvent e)
			{
				if (isrefreshed)
				{
					isrefreshed = false;
					return;
				}
				int type = e.getType();
				if (type == 0)//getType()��ȡ�༭״̬ ����> -1:ɾ���� 0:�༭ 1:������
				{
					if (td)
					{
						operateData("ȫ��");
						if (!isaddrow) refreshTable();
					}
					else
					{
						JDialog jd = new JDialog();
						jd.setTitle("ȷ���޸ģ�");
						JPanel content = new JPanel();
						content.setLayout(new BorderLayout());
						JLabel message = new JLabel("�����������޸ģ�������");
						content.add(message, BorderLayout.NORTH);
						JPanel btn = new JPanel();
						JButton yes = new JButton("�ǵ�û��");
						JButton no = new JButton("���ҿ���һ��");
						
						//yes��no�İ�ť�¼�
						yes.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e)
							{
								//����lc�����ݿ⺯��
								operateData("ȫ��");
								if (!isaddrow) refreshTable();
								jd.dispose();
							}
						});
						
						no.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e)
							{
								jd.dispose();
							}
						});
						
						btn.add(yes);
						btn.add(no);
						content.add(btn, BorderLayout.CENTER);
						JRadioButton again = new JRadioButton("������ʾ");
						again.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e)
							{
								td = true;
							}
						});
						jd.setModal(true);
						content.add(again, BorderLayout.SOUTH);
						jd.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						jd.setSize(300, 120);
						jd.setResizable(false);
						jd.setLocationRelativeTo(lt);
						jd.setContentPane(content);
						jd.setVisible(true);
					}
				}
				if (type == -1)
				{
					try {operateData("ȫ��");} catch(Exception e1) {refreshTable();}
				}
			}
		});
		JScrollPane scroll = new JScrollPane(lt);
		scroll.setSize(250, 250);
		ljpl.add(lt.getTableHeader(), BorderLayout.NORTH);
		ljpl.add(lt, BorderLayout.CENTER);
		return ljpl;
	}
	
	//BorederLayout.SOUTH��JPanel(���ַ���)
	private JPanel funcs()
	{
		JPanel fun = new JPanel();
		fun.setLayout(new BorderLayout());
		fun.add(funcSearch(), BorderLayout.WEST);
		fun.add(funcButton(), BorderLayout.CENTER);
		fun.add(info(), BorderLayout.EAST);
		return fun;
	}
	
	//��������(������������ť)
	private JPanel funcSearch()
	{
		JPanel sch = new JPanel();
		JTextField search = new JTextField(20);
		search.setText("�����뵥һ�ֶ���");
		sch.add(search);
		JButton sconf = new JButton("����");
		sch.add(sconf);
		search.addFocusListener(new FocusListener()
		{
			public void focusLost(FocusEvent e)
			{
				if (search.getText().equals("")) search.setText("�����뵥һ�ֶ���");
			}
			public void focusGained(FocusEvent e)
			{
				if (search.getText().equals("�����뵥һ�ֶ���")) search.setText("");
			}
		});
		search.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String target = search.getText();
				int lenx = lt.getRowCount(), leny = lt.getColumnCount();
				int res[] = new int[lenx];
				for (int i = 0; i < lenx; i++)
				{
					res[i] = 0;
					for (int j = 0; j < leny; j++)
					{
						if (lt.getValueAt(i, j).toString().equals(target))
						{
							res[i] = 1;
							break;
						}
					}
				}
				ListSelectionModel lsmodel = new DefaultListSelectionModel();    
				lsmodel.setSelectionInterval(0, lenx);
				lt.setSelectionModel(lsmodel);
				for (int i = 0; i < lenx; i++) if (res[i] == 0) lsmodel.removeSelectionInterval(i, i);
			}
		});
		sconf.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String target = search.getText();
				int lenx = lt.getRowCount(), leny = lt.getColumnCount();
				int res[] = new int[lenx];
				for (int i = 0; i < lenx; i++)
				{
					res[i] = 0;
					for (int j = 0; j < leny; j++)
					{
						if (lt.getValueAt(i, j).toString().equals(target))
						{
							res[i] = 1;
							break;
						}
					}
				}
				ListSelectionModel listSelectionModel = new DefaultListSelectionModel();    
				listSelectionModel.setSelectionInterval(0, lenx);
				lt.setSelectionModel(listSelectionModel);
				for (int i = 0; i < lenx; i++) if (res[i] == 0) listSelectionModel.removeSelectionInterval(i, i);
			}
		});
		return sch;
	}
	
	//���ܰ�ť(���ӣ�ɾ�����������ܼ�¼)
	private JPanel funcButton()
	{
		JPanel btn = new JPanel();
		String b_label[] = {"���ӳ��ܼ�¼", "ɾ�����ܼ�¼", "����������Ϣ..."};
		int len = b_label.length;
		JButton b[] = new JButton[len];
		for (int i = 0; i < len; i++)
		{
			b[i] = new JButton(b_label[i]);
			btn.add(b[i]);
		}
		b[0].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				newRow(lt);
			}
		});
		b[1].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				delRow(lt);
			}
		});
		b[2].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				exportOpt();
			}
		});
		
		return btn;
	}
	
	//���ܰ�ť(����������)
	private JPanel info()
	{
		JPanel info = new JPanel();
		String b_label[] = {"����", "����"};
		int len = b_label.length;
		JButton b[] = new JButton[len];
		for (int i = 0; i < len; i++)
		{
			b[i] = new JButton(b_label[i]);
			info.add(b[i]);
		}
		b[0].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				showFeedBack();
			}
		});
		b[1].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				showAbout();
			}
		});
		return info;
	}
	
	//��������
	private void showFeedBack()
	{
		JDialog feed = new JDialog();
		feed.setTitle("����");
		feed.setLayout(new BorderLayout());
		feed.add(feedPN(), BorderLayout.NORTH);
		feed.add(feedPC(), BorderLayout.CENTER);
		feed.setModal(true);
		feed.setSize(400, 300);
		feed.setLocationRelativeTo(this);
		feed.setDefaultCloseOperation(2);
		feed.setVisible(true);
		feed.setResizable(false);
	}
	private int mk;
	private JPanel feedPN()
	{
		JPanel pn = new JPanel();
		pn.setLayout(new GridLayout(1, 2));
		JPanel stargroup = new JPanel();
		JRadioButton star[] = new JRadioButton[5];
		int len = star.length;
		for (int i = 0; i < len; i++)
		{
			star[i] = new JRadioButton("");
			stargroup.add(star[i]);
		}
		JLabel star_comment = new JLabel("�������������ǰ�~");
		pn.add(stargroup);
		pn.add(star_comment);
		String star_label[] = {"��Ҳʺ���ף�ɶ�����Ӵ", "������ȥ�ɣ������۾�", "��Ӵ�����е㶫����", "������ã��������Ǯ��", "��������Ʒ����Ҫ�����Ǯ��"};
		for (int i = 0; i < len; i++)
		{
			star[i].addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					for (int j = 0; j < len; j++)
					{
						if (e.getSource() == star[j])
						{
							mk = j;
							for (int k = j; k >= 0; k--) star[k].setSelected(true);
							for (int k = j+1; k < len; k++) star[k].setSelected(false);
							star_comment.setText(star_label[mk]);
						}
					}
				}
			});
		}
		return pn;
	}
	private JPanel feedPC()
	{
		JPanel pc = new JPanel();
		pc.setLayout(new BorderLayout());
		JTextArea comment = new JTextArea();
		comment.setLineWrap(true);
		comment.setText("�ڴ˴�д����������");
		JScrollPane scroll = new JScrollPane(comment);
		JButton submit = new JButton("�ύ");
		pc.add(scroll, BorderLayout.CENTER);
		pc.add(submit, BorderLayout.SOUTH);
		comment.setRequestFocusEnabled(true);
		comment.addFocusListener(new FocusAdapter()
		{
			public void focusGained(FocusEvent e)
			{
				if (comment.getText().equals("�ڴ˴�д����������")) comment.setText("");
			}
		});
		submit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String commets = comment.getText();//�ȴ�lc�����ݿ�ӿ�
				if (!commets.equals(""))
				{
					System.out.println("�˺ţ�" + sno + " �Ǽ���" + (mk+1) + " ���ۣ�" + commets);
					try {account.feedback(sno + "", mk + 1, commets);} catch (SQLException e1) {e1.printStackTrace();}
					System.out.println("�����ϴ��ɹ���");
					comment.setEditable(false);
					submit.setText("�ϴ��ɹ�����л���ı�����");
	            	submit.setEnabled(false);
		            Timer timer = new Timer();
		            timer.schedule(new TimerTask()
		            {  
		                public void run()
		                {
		                	comment.setEditable(true);
		                	submit.setEnabled(true);
		                	submit.setText("�ύ");
		                }
		            }, 1500);
				}
				else JOptionPane.showMessageDialog(null, "����ʲô��û��������", "������", JOptionPane.ERROR_MESSAGE);
			}
		});
		return pc;
	}
	
	//���ڽ���
	private void showAbout()
	{
		JDialog about = new JDialog();
		about.setTitle("����");
		about.setLayout(new BorderLayout());
		JLabel codecontainer = new JLabel("");
		Icon qrcode = new ImageIcon(this.getClass().getResource("myQRCode.png"));
		codecontainer.setIcon(qrcode);
		about.add(codecontainer, BorderLayout.WEST);
		
		JTextArea intro = new JTextArea();
		about.add(intro, BorderLayout.CENTER);
		intro.setText("\n\n\tTeam\n                      ��ʺ�ɱ��\n\n                       adminGUI\n\t  ³��\n\n                        DataBase\n                     ���������Ϳ�");
		intro.setEditable(false);
		
		JTextArea ps = new JTextArea();
		ps.setText("\t��������κν����Խ���, ��ɨ�����ά��\n                û��ʲô��һ�����Ȳ��ܽ����(����У��Ǿ�����=v=)");
		ps.setEditable(false);
		about.add(ps, BorderLayout.SOUTH);
		
		about.setModal(true);
		about.getContentPane().setBackground(Color.white);
		about.getContentPane().setVisible(true);
		about.setSize(400, 300);
		about.setLocationRelativeTo(this);
		about.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		about.setVisible(true);
		about.setResizable(false);
	}
	
	//�������ܼ�¼(��������)
	private void exportOpt()
	{
		JDialog export = new JDialog();
		export.setTitle("����");
		export.setLayout(new BorderLayout());
		export.add(createExpPN(), BorderLayout.NORTH);
		export.add(createExpPC(), BorderLayout.CENTER);
		export.setModal(true);
		export.setSize(500, 120);
		export.setLocationRelativeTo(this);
		export.setDefaultCloseOperation(2);
		export.setVisible(true);
	}
	
	//���Ϊ�����봢��·��(���Ϊ��ť��·��ѡ����ļ�������)
	private FileOutputStream fos;
	private boolean isdefault;
	private JPanel createExpPN()
	{
		JPanel pn = new JPanel();
		JButton saveas = new JButton("���Ϊ...");
		JTextField inspath = new JTextField(20);
		JRadioButton setdefault = new JRadioButton("��ΪĬ��·��");
		if (isdefault)
		{
			setdefault.setSelected(true);
			inspath.setEditable(false);
		}
		else
		{
			setdefault.setSelected(false);
			inspath.setEditable(true);
		}
		setdefault.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (setdefault.isSelected())
				{
					isdefault = true;
					inspath.setEditable(false);
					saveas.setText("����");
				}
				else
				{
					isdefault = false;
					inspath.setEditable(true);
					saveas.setText("���Ϊ...");
				}
			}
		});
		saveas.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				File file = new File("");
				boolean isconfirmed = false;
				if (saveas.getText().equals("���Ϊ..."))
				{
					JFileChooser jfc = new JFileChooser();
			        //����ѡ��·��ģʽ
			        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);  
			        //���öԻ������
			        jfc.setDialogTitle("��ѡ���ļ���");
			        //�û������ȷ��
			        if (JFileChooser.APPROVE_OPTION == jfc.showSaveDialog(null))
		        	{
			        	isconfirmed = true;
		        		file = jfc.getSelectedFile();//ȡ��·��ѡ��
		        	}
				}
				else if (saveas.getText().equals("����"))
				{
					isconfirmed = true;
					file = new File(inspath.getText());
				}
				if (isconfirmed)
				{
					String savepath = file.getAbsolutePath();//��ȡ��ǰ�����õ�·��
					saveas.setEnabled(false);
					inspath.setEditable(false);
					inspath.setText("������ɹ�!��");
					file = nameFile(file);//�ɾ����õ�·�������������������������ɵ��ļ�
		        	saveInto(file);//����
					// ����һ���¼�ʱ��
		            Timer timer = new Timer();  
		            //1��ִ�и�����
		            timer.schedule(new TimerTask()
		            {  
		                public void run()
		                {
		                	saveas.setEnabled(true);
		                	inspath.setEditable(true);
		                    inspath.setText(savepath);//��·����ʾ���ı���
		                }  
		            }, 1000);
				}
			}
		});
		pn.add(saveas);
		pn.add(inspath);
		pn.add(setdefault);
		return pn;
	}
	
	//������ť�Ľ���(���ַ���)
	private JPanel createExpPC()
	{
		JPanel pc = new JPanel();
		pc.setLayout(new BorderLayout());
		pc.add(createExpPCW(), BorderLayout.WEST);
		pc.add(createExpPCC(), BorderLayout.CENTER);
		return pc;
	}
	
	//���Ϊ�ĵ�λѡ��(��λ��ť����λ���������)
	private String expb_label[];
	private JPanel createExpPCW()
	{
		JPanel pcw = new JPanel();
		pcw.setLayout(new GridLayout(2, 2));
		int len = expb_label.length;
		ButtonGroup choice = new ButtonGroup();
		JRadioButton b[] = new JRadioButton[len];
		for (int i = 0; i < len; i++)
		{
			b[i] = new JRadioButton("��" + expb_label[i] + "Ϊ��λ");
			choice.add(b[i]);
			pcw.add(b[i]);
			b[i].addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					expobj = e.getActionCommand();
					for (int i = 0; i < len; i++)
					{
						if (expobj.equals("��" + expb_label[i] + "Ϊ��λ"))
						{
							pccjtf.setText("����\"" + expb_label[i] + "\"��");
						}
					}
					if (expobj == null) pccjtf.setEditable(false);
					else pccjtf.setEditable(true);
				}
			});
		}
		return pcw;
	}
	
	//��λ���������
	private String expobj;//expobj = �� + expb_label[i]  + Ϊ��λ
	private String expobj_name;//��������������
	private JTextField pccjtf;
	private JPanel createExpPCC()
	{
		JPanel pcc = new JPanel();
		pccjtf.setText("���������ѡ�õ�����λ");
		if (expobj == null) pccjtf.setEditable(false);
		else pccjtf.setEditable(true);
		pccjtf.addFocusListener(new FocusListener()
		{
			public void focusLost(FocusEvent e)
			{
				for (int i = 0; i < expb_label.length; i++)
					if (pccjtf.getText().equals("") && expobj.equals("��" + expb_label[i] + "Ϊ��λ")) pccjtf.setText("����\"" + expb_label[i] + "\"��");
			}
			public void focusGained(FocusEvent e)
			{
				pccjtf.setText("");
			}
		});
		pccjtf.addInputMethodListener(new InputMethodListener()
		{
			public void inputMethodTextChanged(InputMethodEvent e)
			{
				expobj_name = pccjtf.getText();
			}
			public void caretPositionChanged(InputMethodEvent e) {}
		});
		pccjtf.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				expobj_name = pccjtf.getText();
			}
		});
		pcc.add(pccjtf);
		return pcc;
	}
	
	//���ߺ���(��ȡ���ش���������Ͳ�������Ӧ���ļ�·��)
	private File nameFile(File file)
	{
		File search[] = file.listFiles();
		int cnt = 1;
		for (int i = 0; i < search.length; i++) if (search[i].getName().equals(expobj_name + cnt + ".txt")) cnt++;
		file = new File(file.getAbsolutePath() + "\\" + expobj_name + cnt + ".txt");
		return file;
	}
		
	//���ߺ���(�������ر�񻺴��ļ�����fis��fos�洢�ļ�����Ӧ��·��)
	private void saveInto(File file)
	{
		try
		{
			fos = new FileOutputStream(file);
			if (!file.exists()) file.createNewFile();
			Object form[][] = getData("ȫ��");
			int lenx = form.length;
			for (int i = 0; i < lenx; i++)
			{
				int leny = form[i].length;
				for (int j = 0; j < leny; j++)
				{
					byte[] contentInBytes = ((form[i][j] + "\t").toString()).getBytes();
					fos.write(contentInBytes);
				}
				fos.write(System.getProperty("line.separator").getBytes());
			}
			fos.flush();
			fos.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	//���ߺ���-���ɱ��(�����)
	private Object[][] getData(String type)
	{
		Object obj[][] = null;
		switch (type)
		{
		default:
		{
			System.out.println("��Error��\t" + "\"" + type + "\"�������ݿ���!");
			break;
		}
		case "ȫ��":
		{
			try {obj = record.getAll();} catch(SQLException e) {e.printStackTrace();}
			break;
		}
		case "ѧ��":
		{
			try {obj = student.allSelect();} catch (Exception e) {e.printStackTrace();}
			break;
		}
		case "���ܼ�¼":
		{
			try{obj = record.getAllRecord();} catch (Exception e) {e.printStackTrace();}
			break;
		}
		}
		return obj;
	}
	
	//���ߺ���-���ɱ���ͷ(�����)
	private String[] getDataColumn(String type)
	{
		String obj[] = null;
		switch (type)
		{
		default:
		{
			System.out.println("��Error��\t" + "\"" + type + "\"�������ݿ���!�޷���ȡ��ͷ!");
			break;
		}
		case "ȫ��":
		{
			obj = new String[] {"ѧУ", "ѧ��", "����", "ѧԺ", "�༶", "����", "��Ч����(m)", "ʱ��(min)", "�ٶ�(m/s)", "����(��)"};
			break;
		}
		case "ѧ��":
		{
			obj = new String[] {"ѧУ", "ѧ��", "����", "ѧԺ", "�༶"};
			break;
		}
		case "���ܼ�¼":
		{
			obj = new String[] {"ѧ��", "����", "��Ч����", "��ʱ", "�ٶ�", "��Ч��"};
			break;
		}
		}
		return obj;
	}
	
	//���ߺ���-���µ�ǰ������(���ݿ⼶�Ķ�)
	private void operateData(String type)//��ע��before���⣬��ɾbefore��ɾobj
	{
		final int x[] = lt.getSelectedRows(), lenx = x.length, leny = lt.getColumnCount();
		if (isaddrow)
		{
			if (lt.getValueAt(lt.getSelectedRow(), lt.getColumnCount()-3) == null) return;
			int len = getDataColumn("���ܼ�¼").length;
			Object obj[] = new Object[len];
			for (int i = 0; i < lenx; i++)
			{
				System.out.println("��ʼ�����ܼ�¼����ǰΪѡ�еĵ�" + (i+1) + "�С���ѡ��" + lenx + "��");
				obj[0] = lt.getValueAt(x[i], 1);//sno
				for (int j = 1; j < len; j++) obj[j] = lt.getValueAt(x[i], leny-len+j);
				try {record.InsertRecord(obj);} catch (Exception e) {e.printStackTrace();}
			}
			System.out.println("���ܼ�¼�����ݿ�ͬ���ɹ�!");
			isaddrow = false;
		}
		else
		{
			switch (type)
			{
			default:
			{
				System.out.println("��Error��\t" + "\"" + type + "\"�������ݿ���!�޷�ˢ��!");
				break;
			}
			case "ȫ��":
			{
				if (!isdelrow && !isaddrow)
				{
					operateData("���ܼ�¼");
					operateData("ѧ��");
				}
				else operateData("���ܼ�¼");
				break;
			}
			case "���ܼ�¼":
			{
				//���ܼ�¼
				int len = getDataColumn(type).length;
				Object obj[] = new Object[len];
				for (int i = 0; i < lenx; i++)
				{
					System.out.println("��ʼ�������ܼ�¼����ǰΪѡ�еĵ�" + (i+1) + "�С���ѡ��" + lenx + "��");
					obj[0] = before[x[i]][1];
					for (int j = 1; j < len; j++) obj[j] = before[x[i]][leny-len+j];
					try {record.DeleteRecord(obj);} catch (Exception e) {e.printStackTrace();}
					
					if (!isdelrow)
					{
						obj[0] = lt.getValueAt(x[i], 1);//sno
						for (int j = 1; j < len; j++) obj[j] = lt.getValueAt(x[i], leny-len+j);
						try {record.InsertRecord(obj);} catch (Exception e) {e.printStackTrace();}
					}
				}
				System.out.println("���ܼ�¼�����ݿ�ͬ���ɹ�!");
				before = getData(lt_name);
				if (isdelrow) isdelrow = false;
				break;
			}
			case "ѧ��":
			{
				int len = getDataColumn(type).length;
				Object obj[] = new Object[len];
				for (int i = 0; i < lenx; i++)
				{
					System.out.println("��ʼ����ѧ������ǰΪѡ�еĵ�" + (i+1) + "�С���ѡ��" + lenx + "��");
					for (int j = 0; j < len; j++) obj[j] = before[x[i]][j];
					try {student.Delete(obj[1].toString());} catch (Exception e) {e.printStackTrace();}
					
					for (int j = 0; j < len; j++) obj[j] = lt.getValueAt(x[i], j);
					try {student.Insert(obj);} catch (Exception e) {e.printStackTrace();}
				}
				System.out.println("ѧ���������ݿ�ͬ���ɹ�!");
				before = getData(lt_name);
				break;
			}
			}
		}
	}

	//���ߺ���-������һ��(JTable)(���ظĶ�)
	private void newRow(JTable table)
	{
		isaddrow = true;
		int len = table.getModel().getColumnCount();
		((DefaultTableModel) table.getModel()).addRow(new Object[len]);
	}
	
	//���ߺ���-���ɾ��һ��(JTable)(���ظĶ�)
	private void delRow(JTable table)
	{
		isdelrow = true;
		int select[] = table.getSelectedRows(), len = select.length;
		if (isaddrow)
		{
			isaddrow = false;
			refreshTable();
			return;
		}
		if (len == 0) JOptionPane.showMessageDialog(table, "û��ѡ���У�");
		else for (int i = 0; i < len; i++) ((DefaultTableModel) table.getModel()).removeRow(select[i]);
	}
	
	//���ߺ���-ˢ��JTable
	private void refreshTable()
	{
		isrefreshed = true;
		((DefaultTableModel) lt.getModel()).setDataVector(getData("ȫ��"), getDataColumn("ȫ��"));
	}
	
	//���Ժ���
	public static void main(String args[])
	{
		new adminGUI("1405170120");
	}
}
