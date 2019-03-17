package wage;

class WageList
{
	private double fwage, pwage, gwage, mwage, awage, sum;
	WageList(double f, double p, double g, double m, double a)
	{
		this.fwage = f;
		this.pwage = p;
		this.gwage = g;
		this.mwage = m;
		this.awage = a;
		sum = fwage + pwage + gwage + mwage + awage;
	}
	public String toString()
	{
		String tmp = "";
		tmp += "基础工资：" + this.fwage + "，";
		tmp += "岗位津贴：" + this.pwage + "，";
		tmp += "绩效津贴：" + this.gwage + "，";
		tmp += "医疗补助：" + this.mwage + "，";
		tmp += "独生子女补助：" + this.awage + "，";
		tmp += "工资总和：" + this.sum;
		return tmp;
	}
}

public class main
{
	public static void main(String args[])
	{
		WageList w1 = new WageList(1500, 1000, 500, 130.45, 5);
		System.out.println(w1.toString());
	}
}
