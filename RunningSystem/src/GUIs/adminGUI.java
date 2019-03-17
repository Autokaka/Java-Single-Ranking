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
	
	//构造函数
	private final String sno;
	public adminGUI(String sno)
	{
		System.out.println("初始化长跑数据...");
		record = new RunningRecordOperator();
		System.out.println("初始化学生表...");
		student = new StuInfo();
		System.out.println("初始化账户信息...");
		account = new Account();
		this.sno = sno;
		
		//BorderLayout.CENTER的JPanel(表格)-数据初始化
		lt_name = "全部";
		before = getData(lt_name);
		td = false;
		isrefreshed = false;
		isaddrow = false;
		isdelrow = false;
		
		//另存为及输入储存路径(另存为按钮，路径选择框，文件管理器)-数据初始化
		isdefault = false;
		
		//另存为的单位选择(单位按钮，单位名称输入框)-数据初始化
		expb_label = new String[]{"学校", "学院", "班级", "个人"};
		
		//单位名称输入框-数据初始化
		expobj = null;
		expobj_name = "未命名表格";
		pccjtf = new JTextField(20);
		
		this.setLayout(new BorderLayout());
		this.add(leftTable(), BorderLayout.CENTER);
		this.add(funcs(), BorderLayout.SOUTH);
		
		this.setTitle("当前管理员：" + sno);
		this.setSize(1000, 600);//必须先setsize
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(3);
	}
	
	//BorderLayout.CENTER的JPanel(表格)
	private JTable lt;
	private String lt_name;//当前显示的表格在数据库内的名字
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
				if (type == 0)//getType()获取编辑状态 ——> -1:删除行 0:编辑 1:增加行
				{
					if (td)
					{
						operateData("全部");
						if (!isaddrow) refreshTable();
					}
					else
					{
						JDialog jd = new JDialog();
						jd.setTitle("确认修改？");
						JPanel content = new JPanel();
						content.setLayout(new BorderLayout());
						JLabel message = new JLabel("数据内容已修改，保存吗？");
						content.add(message, BorderLayout.NORTH);
						JPanel btn = new JPanel();
						JButton yes = new JButton("是的没错！");
						JButton no = new JButton("容我考虑一下");
						
						//yes和no的按钮事件
						yes.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e)
							{
								//接入lc的数据库函数
								operateData("全部");
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
						JRadioButton again = new JRadioButton("不再显示");
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
					try {operateData("全部");} catch(Exception e1) {refreshTable();}
				}
			}
		});
		JScrollPane scroll = new JScrollPane(lt);
		scroll.setSize(250, 250);
		ljpl.add(lt.getTableHeader(), BorderLayout.NORTH);
		ljpl.add(lt, BorderLayout.CENTER);
		return ljpl;
	}
	
	//BorederLayout.SOUTH的JPanel(布局方法)
	private JPanel funcs()
	{
		JPanel fun = new JPanel();
		fun.setLayout(new BorderLayout());
		fun.add(funcSearch(), BorderLayout.WEST);
		fun.add(funcButton(), BorderLayout.CENTER);
		fun.add(info(), BorderLayout.EAST);
		return fun;
	}
	
	//搜索部分(搜索框，搜索按钮)
	private JPanel funcSearch()
	{
		JPanel sch = new JPanel();
		JTextField search = new JTextField(20);
		search.setText("请输入单一字段名");
		sch.add(search);
		JButton sconf = new JButton("搜索");
		sch.add(sconf);
		search.addFocusListener(new FocusListener()
		{
			public void focusLost(FocusEvent e)
			{
				if (search.getText().equals("")) search.setText("请输入单一字段名");
			}
			public void focusGained(FocusEvent e)
			{
				if (search.getText().equals("请输入单一字段名")) search.setText("");
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
	
	//功能按钮(增加，删除，导出长跑记录)
	private JPanel funcButton()
	{
		JPanel btn = new JPanel();
		String b_label[] = {"增加长跑记录", "删除长跑记录", "导出长跑信息..."};
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
	
	//功能按钮(反馈，关于)
	private JPanel info()
	{
		JPanel info = new JPanel();
		String b_label[] = {"反馈", "关于"};
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
	
	//反馈界面
	private void showFeedBack()
	{
		JDialog feed = new JDialog();
		feed.setTitle("反馈");
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
		JLabel star_comment = new JLabel("钟意吗？评个星星吧~");
		pn.add(stargroup);
		pn.add(star_comment);
		String star_label[] = {"口也屎啦雷，啥玩意儿哟", "还过得去吧，不辣眼睛", "哎哟做的有点东西啊", "做得真好，差点就想打钱了", "完美的作品，我要给你打钱！"};
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
		comment.setText("在此处写下您的问题");
		JScrollPane scroll = new JScrollPane(comment);
		JButton submit = new JButton("提交");
		pc.add(scroll, BorderLayout.CENTER);
		pc.add(submit, BorderLayout.SOUTH);
		comment.setRequestFocusEnabled(true);
		comment.addFocusListener(new FocusAdapter()
		{
			public void focusGained(FocusEvent e)
			{
				if (comment.getText().equals("在此处写下您的问题")) comment.setText("");
			}
		});
		submit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String commets = comment.getText();//等待lc的数据库接口
				if (!commets.equals(""))
				{
					System.out.println("账号：" + sno + " 星级：" + (mk+1) + " 评论：" + commets);
					try {account.feedback(sno + "", mk + 1, commets);} catch (SQLException e1) {e1.printStackTrace();}
					System.out.println("评论上传成功！");
					comment.setEditable(false);
					submit.setText("上传成功，感谢您的宝贵建议");
	            	submit.setEnabled(false);
		            Timer timer = new Timer();
		            timer.schedule(new TimerTask()
		            {  
		                public void run()
		                {
		                	comment.setEditable(true);
		                	submit.setEnabled(true);
		                	submit.setText("提交");
		                }
		            }, 1500);
				}
				else JOptionPane.showMessageDialog(null, "您还什么都没有输入呢", "？？？", JOptionPane.ERROR_MESSAGE);
			}
		});
		return pc;
	}
	
	//关于界面
	private void showAbout()
	{
		JDialog about = new JDialog();
		about.setTitle("关于");
		about.setLayout(new BorderLayout());
		JLabel codecontainer = new JLabel("");
		Icon qrcode = new ImageIcon(this.getClass().getResource("myQRCode.png"));
		codecontainer.setIcon(qrcode);
		about.add(codecontainer, BorderLayout.WEST);
		
		JTextArea intro = new JTextArea();
		about.add(intro, BorderLayout.CENTER);
		intro.setText("\n\n\tTeam\n                      狗屎派编程\n\n                       adminGUI\n\t  鲁澳\n\n                        DataBase\n                     刘畅、梁贤俊");
		intro.setEditable(false);
		
		JTextArea ps = new JTextArea();
		ps.setText("\t如果您有任何建设性建议, 请扫这个二维码\n                没有什么是一杯咖啡不能解决的(如果有，那就两杯=v=)");
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
	
	//导出长跑记录(导出界面)
	private void exportOpt()
	{
		JDialog export = new JDialog();
		export.setTitle("导出");
		export.setLayout(new BorderLayout());
		export.add(createExpPN(), BorderLayout.NORTH);
		export.add(createExpPC(), BorderLayout.CENTER);
		export.setModal(true);
		export.setSize(500, 120);
		export.setLocationRelativeTo(this);
		export.setDefaultCloseOperation(2);
		export.setVisible(true);
	}
	
	//另存为及输入储存路径(另存为按钮，路径选择框，文件管理器)
	private FileOutputStream fos;
	private boolean isdefault;
	private JPanel createExpPN()
	{
		JPanel pn = new JPanel();
		JButton saveas = new JButton("另存为...");
		JTextField inspath = new JTextField(20);
		JRadioButton setdefault = new JRadioButton("设为默认路径");
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
					saveas.setText("保存");
				}
				else
				{
					isdefault = false;
					inspath.setEditable(true);
					saveas.setText("另存为...");
				}
			}
		});
		saveas.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				File file = new File("");
				boolean isconfirmed = false;
				if (saveas.getText().equals("另存为..."))
				{
					JFileChooser jfc = new JFileChooser();
			        //设置选择路径模式
			        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);  
			        //设置对话框标题
			        jfc.setDialogTitle("请选择文件夹");
			        //用户点击了确定
			        if (JFileChooser.APPROVE_OPTION == jfc.showSaveDialog(null))
		        	{
			        	isconfirmed = true;
		        		file = jfc.getSelectedFile();//取得路径选择
		        	}
				}
				else if (saveas.getText().equals("保存"))
				{
					isconfirmed = true;
					file = new File(inspath.getText());
				}
				if (isconfirmed)
				{
					String savepath = file.getAbsolutePath();//获取当前决定好的路径
					saveas.setEnabled(false);
					inspath.setEditable(false);
					inspath.setText("【保存成功!】");
					file = nameFile(file);//由决定好的路径，按规则智能命名即将生成的文件
		        	saveInto(file);//保存
					// 创建一个新计时器
		            Timer timer = new Timer();  
		            //1后执行该任务
		            timer.schedule(new TimerTask()
		            {  
		                public void run()
		                {
		                	saveas.setEnabled(true);
		                	inspath.setEditable(true);
		                    inspath.setText(savepath);//将路径显示到文本框
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
	
	//导出按钮的界面(布局方法)
	private JPanel createExpPC()
	{
		JPanel pc = new JPanel();
		pc.setLayout(new BorderLayout());
		pc.add(createExpPCW(), BorderLayout.WEST);
		pc.add(createExpPCC(), BorderLayout.CENTER);
		return pc;
	}
	
	//另存为的单位选择(单位按钮，单位名称输入框)
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
			b[i] = new JRadioButton("以" + expb_label[i] + "为单位");
			choice.add(b[i]);
			pcw.add(b[i]);
			b[i].addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					expobj = e.getActionCommand();
					for (int i = 0; i < len; i++)
					{
						if (expobj.equals("以" + expb_label[i] + "为单位"))
						{
							pccjtf.setText("输入\"" + expb_label[i] + "\"名");
						}
					}
					if (expobj == null) pccjtf.setEditable(false);
					else pccjtf.setEditable(true);
				}
			});
		}
		return pcw;
	}
	
	//单位名称输入框
	private String expobj;//expobj = 以 + expb_label[i]  + 为单位
	private String expobj_name;//储存输入框的名称
	private JTextField pccjtf;
	private JPanel createExpPCC()
	{
		JPanel pcc = new JPanel();
		pccjtf.setText("请先在左侧选好导出单位");
		if (expobj == null) pccjtf.setEditable(false);
		else pccjtf.setEditable(true);
		pccjtf.addFocusListener(new FocusListener()
		{
			public void focusLost(FocusEvent e)
			{
				for (int i = 0; i < expb_label.length; i++)
					if (pccjtf.getText().equals("") && expobj.equals("以" + expb_label[i] + "为单位")) pccjtf.setText("输入\"" + expb_label[i] + "\"名");
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
	
	//工具函数(获取本地储存表格的类型并创建对应的文件路径)
	private File nameFile(File file)
	{
		File search[] = file.listFiles();
		int cnt = 1;
		for (int i = 0; i < search.length; i++) if (search[i].getName().equals(expobj_name + cnt + ".txt")) cnt++;
		file = new File(file.getAbsolutePath() + "\\" + expobj_name + cnt + ".txt");
		return file;
	}
		
	//工具函数(创建本地表格缓存文件并用fis和fos存储文件到对应的路径)
	private void saveInto(File file)
	{
		try
		{
			fos = new FileOutputStream(file);
			if (!file.exists()) file.createNewFile();
			Object form[][] = getData("全部");
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
	
	//工具函数-生成表格(表格名)
	private Object[][] getData(String type)
	{
		Object obj[][] = null;
		switch (type)
		{
		default:
		{
			System.out.println("【Error】\t" + "\"" + type + "\"表不在数据库内!");
			break;
		}
		case "全部":
		{
			try {obj = record.getAll();} catch(SQLException e) {e.printStackTrace();}
			break;
		}
		case "学生":
		{
			try {obj = student.allSelect();} catch (Exception e) {e.printStackTrace();}
			break;
		}
		case "长跑记录":
		{
			try{obj = record.getAllRecord();} catch (Exception e) {e.printStackTrace();}
			break;
		}
		}
		return obj;
	}
	
	//工具函数-生成表格表头(表格名)
	private String[] getDataColumn(String type)
	{
		String obj[] = null;
		switch (type)
		{
		default:
		{
			System.out.println("【Error】\t" + "\"" + type + "\"表不在数据库内!无法获取表头!");
			break;
		}
		case "全部":
		{
			obj = new String[] {"学校", "学号", "姓名", "学院", "班级", "日期", "有效距离(m)", "时间(min)", "速度(m/s)", "次数(次)"};
			break;
		}
		case "学生":
		{
			obj = new String[] {"学校", "学号", "姓名", "学院", "班级"};
			break;
		}
		case "长跑记录":
		{
			obj = new String[] {"学号", "日期", "有效距离", "用时", "速度", "有效性"};
			break;
		}
		}
		return obj;
	}
	
	//工具函数-更新当前更改行(数据库级改动)
	private void operateData(String type)//备注：before问题，先删before再删obj
	{
		final int x[] = lt.getSelectedRows(), lenx = x.length, leny = lt.getColumnCount();
		if (isaddrow)
		{
			if (lt.getValueAt(lt.getSelectedRow(), lt.getColumnCount()-3) == null) return;
			int len = getDataColumn("长跑记录").length;
			Object obj[] = new Object[len];
			for (int i = 0; i < lenx; i++)
			{
				System.out.println("开始增添长跑记录。当前为选中的第" + (i+1) + "行。共选中" + lenx + "行");
				obj[0] = lt.getValueAt(x[i], 1);//sno
				for (int j = 1; j < len; j++) obj[j] = lt.getValueAt(x[i], leny-len+j);
				try {record.InsertRecord(obj);} catch (Exception e) {e.printStackTrace();}
			}
			System.out.println("长跑记录与数据库同步成功!");
			isaddrow = false;
		}
		else
		{
			switch (type)
			{
			default:
			{
				System.out.println("【Error】\t" + "\"" + type + "\"表不在数据库内!无法刷新!");
				break;
			}
			case "全部":
			{
				if (!isdelrow && !isaddrow)
				{
					operateData("长跑记录");
					operateData("学生");
				}
				else operateData("长跑记录");
				break;
			}
			case "长跑记录":
			{
				//长跑记录
				int len = getDataColumn(type).length;
				Object obj[] = new Object[len];
				for (int i = 0; i < lenx; i++)
				{
					System.out.println("开始操作长跑记录。当前为选中的第" + (i+1) + "行。共选中" + lenx + "行");
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
				System.out.println("长跑记录与数据库同步成功!");
				before = getData(lt_name);
				if (isdelrow) isdelrow = false;
				break;
			}
			case "学生":
			{
				int len = getDataColumn(type).length;
				Object obj[] = new Object[len];
				for (int i = 0; i < lenx; i++)
				{
					System.out.println("开始操作学生表。当前为选中的第" + (i+1) + "行。共选中" + lenx + "行");
					for (int j = 0; j < len; j++) obj[j] = before[x[i]][j];
					try {student.Delete(obj[1].toString());} catch (Exception e) {e.printStackTrace();}
					
					for (int j = 0; j < len; j++) obj[j] = lt.getValueAt(x[i], j);
					try {student.Insert(obj);} catch (Exception e) {e.printStackTrace();}
				}
				System.out.println("学生表与数据库同步成功!");
				before = getData(lt_name);
				break;
			}
			}
		}
	}

	//工具函数-表格添加一行(JTable)(本地改动)
	private void newRow(JTable table)
	{
		isaddrow = true;
		int len = table.getModel().getColumnCount();
		((DefaultTableModel) table.getModel()).addRow(new Object[len]);
	}
	
	//工具函数-表格删除一行(JTable)(本地改动)
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
		if (len == 0) JOptionPane.showMessageDialog(table, "没有选定行！");
		else for (int i = 0; i < len; i++) ((DefaultTableModel) table.getModel()).removeRow(select[i]);
	}
	
	//工具函数-刷新JTable
	private void refreshTable()
	{
		isrefreshed = true;
		((DefaultTableModel) lt.getModel()).setDataVector(getData("全部"), getDataColumn("全部"));
	}
	
	//测试函数
	public static void main(String args[])
	{
		new adminGUI("1405170120");
	}
}
