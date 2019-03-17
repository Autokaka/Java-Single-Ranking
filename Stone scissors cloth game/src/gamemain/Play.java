package gamemain;
import java.util.Scanner;

class Obj
{
	protected static String retName(int x)
	{
		switch(x)
		{
			case 0: return "布";
			case 1: return "剪刀";
			case 2: return "石头";
		}
		return null;
	}
}

public class Play
{
	public static void main(String[] args)
	{
		int you, computer;
		Scanner sc = new Scanner(System.in);
		System.out.println("石头剪子布游戏（布0，剪刀1，石头2）");
		System.out.println("你的选择是：");
		you = sc.nextInt();
		computer = 0 + (int)(Math.random()*(2 - 0 + 1));
		System.out.println(" * 你出的" + Obj.retName(you) + ", 电脑出的" + Obj.retName(computer));
		if (you == computer) System.out.println("平局!");
		if (Math.abs(you - computer) == 1)
		{
			if (you > computer) System.out.println("你赢了!");
			else System.out.println("电脑赢了!");
		}
		else if (you == 0 && computer == 2) System.out.println("你赢了!");
		else if (you == 2 && computer == 0) System.out.println("电脑赢了!");
	}
}
