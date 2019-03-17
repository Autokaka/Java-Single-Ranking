package main;
import java.util.*;

public class Complex
{
	float real, imag;
	
	Complex(){}
	
	Complex(float real, float imag)
	{
		this.real = real;
		this.imag = imag;
	}
	
	public void add(Complex c1, Complex c2)
	{
		this.real = c1.real + c2.real;
		this.imag = c1.imag + c2.imag;
	}
	
	public void minus(Complex c1, Complex c2)
	{
		this.real = c1.real - c2.real;
		this.imag = c1.imag - c2.imag;
	}
	
	public void multiply(Complex c1, Complex c2)
	{
		float y;
		if (c1.imag*c2.imag >= 0) y = -c1.imag*c2.imag;
		else y = c1.imag*c2.imag;
		this.real = c1.real*c2.real + y;
		this.imag = c1.real*c2.imag + c1.imag*c2.real;
	}
	
	public String toString()
	{
		String ret;
		if (this.real == 0 && this.imag != 0) ret = this.imag + "i";
		else if (this.imag == 0 && this.real != 0) ret = this.real + "";
		else if (this.real == 0 && this.imag == 0) ret = "";
		else if (imag > 0) ret = this.real + " + " + this.imag + "i";
		else ret = this.real + " - " + -this.imag + "i";
		return ret;
	}
	
	public static void main(String[] args)
	{
		Scanner cin = new Scanner(System.in);
		Complex cal = new Complex();
		float real, imag;
		System.out.println("请输入复数1：");
		real = cin.nextFloat();
		imag = cin.nextFloat();
		Complex c1 = new Complex(real, imag);
		System.out.println("请输入复数2：");
		real = cin.nextFloat();
		imag = cin.nextFloat();
		Complex c2 = new Complex(real, imag);
		cal.add(c1, c2);
		System.out.println("加法结果：" + cal.toString());
		cal.minus(c1, c2);
		System.out.println("减法结果：" + cal.toString());
		cal.multiply(c1, c2);
		System.out.println("乘法结果：" + cal.toString());
	}
}
