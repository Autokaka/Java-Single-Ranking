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
			System.out.print("   运行提示：\n   请输入你的账号：");
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
			else if (flag == 0 && cnt > 0) System.out.println(" * 账户错误！您还有" + cnt-- + "次机会...");
			else if (cnt == 0) break;
		}
		if (flag == 0 && cnt == 0) System.out.println("您已输错三次密码，登录失败！");
	}
	
	private void menu(int x)
	{
		int flag = 0;
		System.out.println("   欢迎！");
		while (flag == 0)
		{
			System.out.print("   主菜单：\n   1.查询余额\n   2.取款\n   3.存款\n   4.退出\n   输入你的选择：");
			switch(cin.nextInt())
			{
			case 1:
				System.out.println(" * 您的当前账户余额为：" + account[x].getBalance());
				break;
			case 2:
				System.out.print("   请输入取款金额：");
				account[x].withdraw(cin.nextInt());
				break;
			case 3:
				System.out.print("   请输入存款金额：");
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
