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
		if (this.balance <= 0 || balance-x < 0) System.out.println(" * 您的余额不足，请重新输入取钱金额或退出...");
		else
		{
			balance -= x;
			System.out.println(" * 取钱成功！当前账户余额：" + balance + "￥");
		}
	}
	
	public void deposit(int x)
	{
		balance += x;
		System.out.println(" * 存钱成功！当前账户余额：" + balance + "￥");
	}
}
