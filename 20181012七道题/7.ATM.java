package main;
import java.util.*;

public class ATM
{
	Scanner cin = new Scanner(System.in);
	private Account [] account = new Account[10];
	
	ATM()
	{
		for (int i = 0; i < 10; i++) account[i] = new Account(100 + i);
	}
	
	public void login()
	{
		int i, key, cnt = 3, flag = 0;
		while (true)
		{
			System.out.print("   ������ʾ��\n   ����������˺ţ�");
			key = cin.nextInt();
			for (i = 0; i < 10; i++)
			{
				if (key == account[i].getID())
				{
					flag = 1;
					break;
				}
			}
			if (flag == 1) menu(i);
			else if (flag == 0 && cnt > 0) System.out.println(" * �˻�����������" + cnt-- + "�λ���...");
			else if (cnt == 0) break;
		}
		if (flag == 0 && cnt == 0) System.out.println("��������������룬��¼ʧ�ܣ�");
	}
	
	private void menu(int x)
	{
		int flag = 0;
		System.out.println("   ��ӭ��");
		while (flag == 0)
		{
			System.out.print("   ���˵���\n   1.��ѯ���\n   2.ȡ��\n   3.���\n   4.�˳�\n   �������ѡ��");
			switch(cin.nextInt())
			{
			case 1:
				System.out.println(" * ���ĵ�ǰ�˻����Ϊ��" + account[x].getBalance());
				break;
			case 2:
				System.out.print("   ������ȡ���");
				account[x].withdraw(cin.nextInt());
				break;
			case 3:
				System.out.print("   ���������");
				account[x].deposit(cin.nextInt());
				break;
			case 4:
				flag = 1;
				break;	
			}
		}
	}
	
	public static void main(String args[])
	{
		ATM atm1 = new ATM();
		atm1.login();
	}
}
