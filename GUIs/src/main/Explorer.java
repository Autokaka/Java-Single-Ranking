package main;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Explorer extends JFrame
{
	private JTree tree;
	
	//���캯��
	public Explorer()
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
		for (int i = 0; i < len; i++)
		{
			rootNode[i] = new DefaultMutableTreeNode((char) ('C' + i) + ":");
			top.add(rootNode[i]);
			if (root[i].isDirectory())
			{
				DefaultMutableTreeNode tmp = new DefaultMutableTreeNode("...");
				tmp.setAllowsChildren(false);
				rootNode[i].add(tmp);
			}
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
            	if (!now_path.equals(null) && new File(now_path).isDirectory()) showChild(now, now_path);
            }
        });
		tree.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (e.getButton() == MouseEvent.BUTTON3)
				{
					showRightKeyMenu(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		
		//Ӧ�õ�pw�����䷵��
		JScrollPane pw = new JScrollPane(tree);
		pw.setPreferredSize(new Dimension(200, 200));
		return pw;
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
	}
	
	//���ߺ���-��ʾ���ڵ�������ӽڵ�(���ڵ㣬 ���ڵ����ȫ·��)
	private void showChild(DefaultMutableTreeNode nowNode, String now_path)
	{
		File now = new File(now_path);
		File next[] = now.listFiles();
		int len = next.length;
		for (int i = 0; i < len; i++)
		{
			DefaultMutableTreeNode nextNode = new DefaultMutableTreeNode(next[i].getName());
			if (next[i].isDirectory())
    		{
    			DefaultMutableTreeNode tmp = new DefaultMutableTreeNode("...");
    			tmp.setAllowsChildren(false);
    			nextNode.add(tmp);
    		}
			nowNode.add(nextNode);
		}
	}
	
	//���ߺ���-��ȡһ���ڵ����ȫ·��(�ڵ�)
	private String getNodePath(DefaultMutableTreeNode now)
	{
		String path = now.toString();
		if (path.equals("�ҵĵ���")) return null;
		String before = now.getParent().toString();
		if (before.equals("�ҵĵ���")) return (path + "\\");
		else
		{
			while (!before.equals("�ҵĵ���"))
			{
				path = before + "\\" + path;
				now = (DefaultMutableTreeNode) now.getParent();
				before = now.getParent().toString();
			}
		}
		return path;
	}
	
	//�ļ��п��ӻ���������
	private JPanel createPC()
	{
		JPanel pe = new JPanel();
		return pe;
	}
	
	//������
	public static void main(String[] args)
	{
		new Explorer();
	}
}
