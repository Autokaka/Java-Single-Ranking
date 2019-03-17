package javaiotest;
import java.io.*;

public class test1
{
	public static void main(String args[])
	{
//		File file = new File("FileTest.java");
		File file1 = new File("D:\\Download");
//		File file2 = new File(file1, "FileTest.java");
		File file3 = new File("D:\\Download", "test.txt");
		System.out.println("文件的长度：" + file3.length());
		System.out.println("文件是否可读：" + (file3.canRead()? "可读" : "不可读"));
		
		File file4 = new File("D:\\Download", "test2.txt");
		File file5 = new File("D:\\Download\\test");
		try
		{
			String s[] = file1.list();
			for(int i = 0; i < s.length; i++) System.out.println(s[i]);
			file4.createNewFile();//设计输入输出问题把这些语句放入try catch内部
			System.out.println("test2.txt已创建");
			file5.mkdirs();
			System.out.println("D:\\Download\\test已创建");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
