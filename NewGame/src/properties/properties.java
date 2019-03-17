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
		System.out.println("¡¾ÎäÆ÷ÏµÍ³³õÊ¼»¯²âÊÔ¡¿");
		System.out.println(" * ÎäÆ÷Ãû³Æ£º");
		name = scanf.nextLine();
		System.out.println(" * ÎäÆ÷´úºÅ£º");
		code = scanf.nextLine();
		System.out.println(" * ÎäÆ÷µÈ¼¶£º");
		level = scanf.nextInt();
		System.out.println(" * ÎäÆ÷¹¥»÷£º");
		attack = scanf.nextInt();
		System.out.println(" * ÎäÆ÷·ÀÓù£º");
		defense = scanf.nextInt();
		weapons newep = new weapons(name, code, level, attack, defense);
		System.out.println("¡¾¹ÖÊŞÏµÍ³³õÊ¼»¯²âÊÔ¡¿");
		System.out.println(" * ¹ÖÊŞÃû³Æ£º");
		name = scanf.nextLine();
		System.out.println(" * ¹ÖÊŞ´úºÅ£º");
		code = scanf.nextLine();
		System.out.println(" * ¹ÖÊŞµÈ¼¶£º");
		level = scanf.nextInt();
		System.out.println(" * ¹ÖÊŞ¹¥»÷£º");
		attack = scanf.nextInt();
		System.out.println(" * ¹ÖÊŞ·ÀÓù£º");
		defense = scanf.nextInt();
		System.out.println(" * ¹ÖÊŞ¸ß¶È£º");
		height = scanf.nextInt();
		System.out.println(" * ¹ÖÊŞÖØÁ¿£º");
		weight = scanf.nextInt();
		monsters newmons = new monsters(name, code, level, attack, defense, height, weight);
		System.out.println("¡¾Ó¢ĞÛÏµÍ³³õÊ¼»¯²âÊÔ¡¿");
		System.out.println(" * Ó¢ĞÛÃû³Æ£º");
		name = scanf.nextLine();
		System.out.println(" * Ó¢ĞÛ´úºÅ£º");
		code = scanf.nextLine();
		System.out.println(" * Ó¢ĞÛµÈ¼¶£º");
		level = scanf.nextInt();
		System.out.println(" * Ó¢ĞÛ¹¥»÷£º");
		attack = scanf.nextInt();
		System.out.println(" * Ó¢ĞÛ·ÀÓù£º");
		defense = scanf.nextInt();
		System.out.println(" * Ó¢ĞÛ¸ß¶È£º");
		height = scanf.nextInt();
		System.out.println(" * Ó¢ĞÛÖØÁ¿£º");
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
