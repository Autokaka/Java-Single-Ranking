package main;
import java.util.*;

public class BMI
{
	private double height;
	private double weight;
	
	BMI(double x, double y)
	{
		this.height = x;
		this.weight = y;
	}
	
	private double retBMI()
	{
		return this.weight/(this.height*this.height);
	}
	
	private String status(double x)
	{
		String ret = null;
		if (x < 18.5) ret = "������";
		else if (x >= 18.5 && x < 24) ret = "��������";
		else if (x >=24 && x < 28) ret = "����";
		else if (x >= 28) ret = "����";
		return ret;
	}
	
	private final void print()
	{
		double bmi = retBMI();
		System.out.println("����BMIֵΪ��" + bmi);
		System.out.println("��������Ϊ" + status(bmi));
	}
	
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		double height, weight;
		System.out.println("��������ߣ�");
		height = input.nextDouble();
		System.out.println("���������أ�");
		weight = input.nextDouble();
		BMI bmi  = new BMI(height, weight);
		bmi.print();
	}
}
