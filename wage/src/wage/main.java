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
		tmp += "�������ʣ�" + this.fwage + "��";
		tmp += "��λ������" + this.pwage + "��";
		tmp += "��Ч������" + this.gwage + "��";
		tmp += "ҽ�Ʋ�����" + this.mwage + "��";
		tmp += "������Ů������" + this.awage + "��";
		tmp += "�����ܺͣ�" + this.sum;
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
