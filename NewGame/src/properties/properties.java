package properties;
import java.util.*;

public class properties
{
	public static void main (String args[])
	{
		Scanner scanf = new Scanner(System.in);
		String name;
		String code;
		int level;
		int attack;
		int defense;
		int height;
		int weight;
		System.out.println("������ϵͳ��ʼ�����ԡ�");
		System.out.println(" * �������ƣ�");
		name = scanf.nextLine();
		System.out.println(" * �������ţ�");
		code = scanf.nextLine();
		System.out.println(" * �����ȼ���");
		level = scanf.nextInt();
		System.out.println(" * ����������");
		attack = scanf.nextInt();
		System.out.println(" * ����������");
		defense = scanf.nextInt();
		weapons newep = new weapons(name, code, level, attack, defense);
		System.out.println("������ϵͳ��ʼ�����ԡ�");
		System.out.println(" * �������ƣ�");
		name = scanf.nextLine();
		System.out.println(" * ���޴��ţ�");
		code = scanf.nextLine();
		System.out.println(" * ���޵ȼ���");
		level = scanf.nextInt();
		System.out.println(" * ���޹�����");
		attack = scanf.nextInt();
		System.out.println(" * ���޷�����");
		defense = scanf.nextInt();
		System.out.println(" * ���޸߶ȣ�");
		height = scanf.nextInt();
		System.out.println(" * ����������");
		weight = scanf.nextInt();
		monsters newmons = new monsters(name, code, level, attack, defense, height, weight);
		System.out.println("��Ӣ��ϵͳ��ʼ�����ԡ�");
		System.out.println(" * Ӣ�����ƣ�");
		name = scanf.nextLine();
		System.out.println(" * Ӣ�۴��ţ�");
		code = scanf.nextLine();
		System.out.println(" * Ӣ�۵ȼ���");
		level = scanf.nextInt();
		System.out.println(" * Ӣ�۹�����");
		attack = scanf.nextInt();
		System.out.println(" * Ӣ�۷�����");
		defense = scanf.nextInt();
		System.out.println(" * Ӣ�۸߶ȣ�");
		height = scanf.nextInt();
		System.out.println(" * Ӣ��������");
		weight = scanf.nextInt();
		heroes newhero = new heroes(name, code, level, attack, defense, height, weight);
	}
}

class weapons
{
	private String name;
	private String code;
	private int level;
	private int attack;
	private int defense;
	weapons(String name, String code, int level, int attack, int defense)
	{
		this.name = name;
		this.code = code;
		this.level = level;
		this.attack = attack;
		this.defense = defense;
	}
	public String getName()
	{
		return this.name;
	}
	public String getCode()
	{
		return this.code;
	}
	public int getAttack()
	{
		return this.attack;
	}
	public int getDefense()
	{
		return this.defense;
	}
}

class monsters
{
	private String name;
	private String code;
	private int level;
	private int attack;
	private int defense;
	private int height;
	private int weight;
	monsters(String name, String code, int level, int attack, int defense, int height, int weight)
	{
		this.name = name;
		this.code = code;
		this.level = level;
		this.attack = attack;
		this.defense = defense;
		this.height = height;
		this.weight = weight;
	}
	public String getName()
	{
		return this.name;
	}
	public String getCode()
	{
		return this.code;
	}
	public int getAttack()
	{
		return this.attack;
	}
	public int getDefense()
	{
		return this.defense;
	}
	public int getHeight()
	{
		return this.height;
	}
	public int getWeight()
	{
		return this.weight;
	}
}

class heroes
{
	private String name;
	private String code;
	private int level;
	private int attack;
	private int defense;
	private int height;
	private int weight;
	heroes(String name, String code, int level, int attack, int defense, int height, int weight)
	{
		this.name = name;
		this.code = code;
		this.level = level;
		this.attack = attack;
		this.defense = defense;
		this.height = height;
		this.weight = weight;
	}
	public String getName()
	{
		return this.name;
	}
	public String getCode()
	{
		return this.code;
	}
	public int getAttack()
	{
		return this.attack;
	}
	public int getDefense()
	{
		return this.defense;
	}
	public int getHeight()
	{
		return this.height;
	}
	public int getWeight()
	{
		return this.weight;
	}
}
