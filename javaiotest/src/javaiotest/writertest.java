package javaiotest;
import java.io.*;

public class writertest
{
	public static void main(String[] args) throws Exception
	{
		FileWriter writer = new FileWriter("D:\\Download\\test.txt");
		writer.write("ÎØÀ²À²À²À²À²À²À²À²À²À²À²");
		char buf[] = {'A', 'B', 'C'};
		writer.write(buf);
		writer.flush();//´Ó»º³åÇøËÍÈëÎÄ¼ş
//		writer.close();
	}
}
