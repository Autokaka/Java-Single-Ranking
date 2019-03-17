package main;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Vector;

public class Explorer2 extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JTree tree;
	private JTable table;
	private String copypath, pastepath, docuname;
	
	//���캯��
	public Explorer2()
	{
		this.setTitle("��Դ������");
		this.setLayout(new BorderLayout());
		this.add(createPW(), BorderLayout.WEST);
		this.add(createPC(), BorderLayout.CENTER);
		this.setLocation(250, 100);
		this.setSize(1000, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(3);
	}
	
	//�ļ�����״ͼ
	private JScrollPane createPW()
	{
		//��ȡ���д����̷�����root������
		File root[] = File.listRoots();
		int len = root.length;
		
		//����root���鴴����Ӧ���̵ĸ��ڵ�
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("�ҵĵ���");
		DefaultMutableTreeNode rootNode[] = new DefaultMutableTreeNode[len];
		//�ж����ļ�����Ŀ¼
		for (int i = 0; i < len; i++)
		{
			rootNode[i] = new DefaultMutableTreeNode(root[i]);
			if (root[i].isDirectory())
			{
				DefaultMutableTreeNode tmp = new DefaultMutableTreeNode("...");
    			tmp.setAllowsChildren(false);
    			rootNode[i].add(tmp);
			}
			top.add(rootNode[i]);
		}
		//��top����JTree
		tree = new JTree(top);
		tree.setShowsRootHandles(true);
		tree.setEditable(true);
		tree.addTreeSelectionListener(new TreeSelectionListener()
		{
            public void valueChanged(TreeSelectionEvent e)
            {
            	DefaultMutableTreeNode now = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            	String now_path = getNodePath(now);
            	if (new File(now_path).isDirectory())
            	{
            		now.remove(0);
            		showChildNode(now, now_path);
            	}
            }
        });
		tree.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (e.getButton() == MouseEvent.BUTTON3)
				{
					TreePath pos = tree.getPathForLocation(e.getX(), e.getY());
					if (!(pos == null)) showRightKeyMenu(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		
		//Ӧ�õ�pw�����䷵��
		JScrollPane pw = new JScrollPane(tree);
		pw.setPreferredSize(new Dimension(200, 200));
		return pw;
	}
	
	//���ߺ���-��ȡ��ǰ�ڵ��·��
	private String getNodePath(DefaultMutableTreeNode now)
	{
		TreeNode tmp[] = now.getPath();
    	int len = tmp.length;
    	String now_path = tmp[1].toString();
    	for (int i = 2; i < len; i++) now_path += tmp[i] + "\\";
    	return now_path;
	}
	
	//���ߺ���-��ʾ��ǰ�ڵ�������ӽڵ�(���ڵ㣬 ���ڵ����ȫ·��)
	private void showChildNode(DefaultMutableTreeNode now, String now_path)
	{
		((DefaultTableModel) table.getModel()).setDataVector(null, column);
    	File next[] = new File(now_path).listFiles();
    	int len = next.length;
    	DefaultMutableTreeNode nextNode[] = new DefaultMutableTreeNode[len];
    	//�ж����ļ�����Ŀ¼
    	for (int i = 0; i < len; i++)
		{
    		nextNode[i] = new DefaultMutableTreeNode(next[i].getName());
    		if (next[i].isDirectory())
    		{
    			DefaultMutableTreeNode tmp = new DefaultMutableTreeNode("...");
    			tmp.setAllowsChildren(false);
    			nextNode[i].add(tmp);
    		}
    		now.add(nextNode[i]);
    		Object content[] = new Object[column.length];
    		content[0] = nextNode[i].toString();
    		content[1] = next[i].lastModified();
    		content[2] = next[i].isDirectory()? "�ļ���" : "�ļ�";
    		content[3] = next[i].isDirectory()? "" : next[i].length();
    		((DefaultTableModel) table.getModel()).addRow(content);
		}
	}
	
	//���ߺ���-��ʾ�Ҽ��˵�(���棬 x���꣬ y����)
	private void showRightKeyMenu(Component layer, int x, int y)
	{
		PopupMenu r_menu = new PopupMenu();
		String item_name[] = {"����", "����", "ճ��", "������"};
		int len = item_name.length;
		MenuItem item[] = new MenuItem[len];
		for (int i = 0; i < len; i++)
		{
			item[i] = new MenuItem(item_name[i]);
			r_menu.add(item[i]);
		}
		layer.add(r_menu);
		r_menu.show(layer, x, y);
		
		//����
		item[0].addActionListener(new ActionListener()//����
		{
			public void actionPerformed(ActionEvent e)
			{
				docuname = tree.getLastSelectedPathComponent().toString();
				copypath = getNodePath((DefaultMutableTreeNode) tree.getLastSelectedPathComponent());
				System.out.println("����·��:"+copypath);
			}
		});
		item[2].addActionListener(new ActionListener()//ճ��
		{
			public void actionPerformed(ActionEvent e)
			{
				if (copypath == null) return;
				DefaultMutableTreeNode now = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				pastepath = getNodePath(now);
				System.out.println("ճ��·��:"+pastepath);
				//�ļ�����
				fileChannelCopy(new File(copypath), docuname, new File(pastepath));
				//��ӽڵ�
				File res = new File(pastepath + "\\" + docuname);
				DefaultMutableTreeNode newdoc = new DefaultMutableTreeNode(res.getName());
				if (res.isFile())
				{
					DefaultMutableTreeNode tmp = new DefaultMutableTreeNode("...");
	    			tmp.setAllowsChildren(false);
	    			newdoc.add(tmp);
				}
				now.add(newdoc);
				tree.updateUI();
			}
		});
	}
	
	//�ļ��п��ӻ���������
	Object column[];
	private JPanel createPC()
	{
		table = new JTable();
		column = new Object[] {"����", "�޸�����", "����", "��С"};
		JPanel pe = new JPanel();
		pe.setLayout(new BorderLayout());
		((DefaultTableModel) table.getModel()).setDataVector(null, column);
		JScrollPane scroll = new JScrollPane(table);
		pe.add(table.getTableHeader(), BorderLayout.NORTH);
		pe.add(table, BorderLayout.CENTER);
		return pe;
	}
	
	//����ճ������
	private void fileChannelCopy(File st, String name, File ed)
	{
		ed = new File(ed + "\\" + name);
		System.out.println("ճ��·������Ϊ:" + ed);
		if (!ed.exists()) try {ed.createNewFile();} catch (Exception e) {e.printStackTrace();}
		try
		{
			FileInputStream fis = new FileInputStream(st);
			FileOutputStream fos = new FileOutputStream(ed);
			FileChannel in = fis.getChannel();
			FileChannel out = fos.getChannel();
			in.transferTo(0, in.size(), out);
			
			fis.close();
            in.close();
            fos.close();
            out.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//������
	public static void main(String[] args)
	{
		new Explorer2();
	}
}