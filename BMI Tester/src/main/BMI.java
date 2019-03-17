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
		if (x < 18.5) ret = "轻体重";
		else if (x >= 18.5 && x < 24) ret = "健康体重";
		else if (x >=24 && x < 28) ret = "超重";
		else if (x >= 28) ret = "肥胖";
		return ret;
	}
	
	private final void print()
	{
		double bmi = retBMI();
		System.out.println("此人BMI值为：" + bmi);
		System.out.println("此人体重为" + status(bmi));
	}
	
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		double height, weight;
		System.out.println("请输入身高：");
		height = input.nextDouble();
		System.out.println("请输入体重：");
		weight = input.nextDouble();
		BMI bmi  = new BMI(height, weight);
		bmi.print();
	}
}
