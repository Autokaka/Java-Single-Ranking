package javaiotest;
import java.io.*;

public class writertest
{
	public static void main(String[] args) throws Exception
	{
		FileWriter writer = new FileWriter("D:\\Download\\test.txt");
		writer.write("������������������������");
		char buf[] = {'A', 'B', 'C'};
		writer.write(buf);
		writer.flush();//�ӻ����������ļ�
//		writer.close();
	}
}
