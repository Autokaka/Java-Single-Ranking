package gamemain;
import java.util.Scanner;

class Obj
{
	protected static String retName(int x)
	{
		switch(x)
		{
			case 0: return "��";
			case 1: return "����";
			case 2: return "ʯͷ";
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
		System.out.println("ʯͷ���Ӳ���Ϸ����0������1��ʯͷ2��");
		System.out.println("���ѡ���ǣ�");
		you = sc.nextInt();
		computer = 0 + (int)(Math.random()*(2 - 0 + 1));
		System.out.println(" * �����" + Obj.retName(you) + ", ���Գ���" + Obj.retName(computer));
		if (you == computer) System.out.println("ƽ��!");
		if (Math.abs(you - computer) == 1)
		{
			if (you > computer) System.out.println("��Ӯ��!");
			else System.out.println("����Ӯ��!");
		}
		else if (you == 0 && computer == 2) System.out.println("��Ӯ��!");
		else if (you == 2 && computer == 0) System.out.println("����Ӯ��!");
	}
}
