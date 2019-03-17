package main;

public class Account {
	private int accountID;
	private double balance;
	
	Account(int x)
	{
		this.accountID = x;
		this.balance = 0;
	}
	
	public void setID(int x)
	{
		this.accountID = x;
	}
	public void setBalance(int x)
	{
		this.balance = x;
	}
	
	public int getID()
	{
		return this.accountID;
	}
	public double getBalance()
	{
		return this.balance;
	}
	
	public void withdraw(double x)
	{
		if (this.balance <= 0 || balance-x < 0) System.out.println(" * �������㣬����������ȡǮ�����˳�...");
		else
		{
			balance -= x;
			System.out.println(" * ȡǮ�ɹ�����ǰ�˻���" + balance + "��");
		}
	}
	
	public void deposit(int x)
	{
		balance += x;
		System.out.println(" * ��Ǯ�ɹ�����ǰ�˻���" + balance + "��");
	}
}
