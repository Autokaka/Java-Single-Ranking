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
	
	//构造函数
	public Explorer()
	{
		this.setTitle("资源管理器");
		this.setLayout(new BorderLayout());
		this.add(createPW(), BorderLayout.WEST);
		this.add(createPC(), BorderLayout.CENTER);
		this.setLocation(250, 100);
		this.setSize(1000, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(3);
	}
	
	//文件夹树状图
	private JScrollPane createPW()
	{
		//获取所有磁盘盘符存在root数组中
		File root[] = File.listRoots();
		int len = root.length;
		
		//根据root数组创建对应磁盘的根节点
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("我的电脑");
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
		
		//将top加入JTree
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
		
		//应用到pw并将其返回
		JScrollPane pw = new JScrollPane(tree);
		pw.setPreferredSize(new Dimension(200, 200));
		return pw;
	}
	
	//工具函数-显示右键菜单(层面， x坐标， y坐标)
	private void showRightKeyMenu(Component layer, int x, int y)
	{
		PopupMenu r_menu = new PopupMenu();
		String item_name[] = {"复制", "剪切", "粘贴", "重命名"};
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
	
	//工具函数-显示父节点的所有子节点(父节点， 父节点的完全路径)
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
	
	//工具函数-获取一个节点的完全路径(节点)
	private String getNodePath(DefaultMutableTreeNode now)
	{
		String path = now.toString();
		if (path.equals("我的电脑")) return null;
		String before = now.getParent().toString();
		if (before.equals("我的电脑")) return (path + "\\");
		else
		{
			while (!before.equals("我的电脑"))
			{
				path = before + "\\" + path;
				now = (DefaultMutableTreeNode) now.getParent();
				before = now.getParent().toString();
			}
		}
		return path;
	}
	
	//文件夹可视化操作界面
	private JPanel createPC()
	{
		JPanel pe = new JPanel();
		return pe;
	}
	
	//主函数
	public static void main(String[] args)
	{
		new Explorer();
	}
}
