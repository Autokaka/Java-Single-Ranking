package main;

interface Queue
{
	public static final int maxSize = 100;
	public void insert(char x);
	public char delete();
	public boolean empty();
	public boolean full();
}

class CharQueue implements Queue
{
	private char sto[] = new char [maxSize];
	private static int end;
	CharQueue()
	{
		end = 0;
	}
	CharQueue(char[] c)
	{
		sto = c.clone();
	}
	
	public void insert(char x)
	{
		sto[end++] = x;
	}
	public char delete()
	{
		int i;
		char del = sto[0];
		for (i = 0; i <= end; i++) sto[i] = sto[i + 1];
		return del;
	}
	public boolean empty()
	{
		if (end == 0) return true;
		return false;
	}
	public boolean full()
	{
		if (end == maxSize - 1) return true;
		return false;
	}
	public String toString()
	{
		String tmp = "";
		int i;
		for (i = 0; i <= end; i++) tmp += sto[i];
		return tmp;
	}
}

public class main
{
	public static void main (String args[])
	{
		CharQueue test = new CharQueue();
		if (test.empty()) System.out.println("数组初始化成功，结果为空");
		else System.out.println("数组初始化成功，结果不为空");
		test.insert('H');
		test.insert('e');
		test.insert('l');
		test.insert('l');
		test.insert('o');
		System.out.println("数组当下的状态：" + test.toString());
		System.out.println("数组删除字符，删除的字符为：" + test.delete());
		System.out.println("数组当下的状态：" + test.toString());
		if (test.full()) System.out.println("数组full检验，结果为true");
		else System.out.println("数组full检验，结果为false");
	}
}
