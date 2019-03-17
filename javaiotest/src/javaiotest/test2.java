package javaiotest;
import java.io.*;

public class test2
{
	public static void main(String[] args)
	{
		File file3 = new File("D:\\Download", "test.txt");
		try
		{
			FileReader filereader = new FileReader(file3);
			BufferedReader breader = new BufferedReader(filereader);
			while(true)
			{
//				System.out.println(filereader.read());
				System.out.println(breader.readLine());
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
