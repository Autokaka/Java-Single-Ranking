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
		System.out.println("�ļ��ĳ��ȣ�" + file3.length());
		System.out.println("�ļ��Ƿ�ɶ���" + (file3.canRead()? "�ɶ�" : "���ɶ�"));
		
		File file4 = new File("D:\\Download", "test2.txt");
		File file5 = new File("D:\\Download\\test");
		try
		{
			String s[] = file1.list();
			for(int i = 0; i < s.length; i++) System.out.println(s[i]);
			file4.createNewFile();//�����������������Щ������try catch�ڲ�
			System.out.println("test2.txt�Ѵ���");
			file5.mkdirs();
			System.out.println("D:\\Download\\test�Ѵ���");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
