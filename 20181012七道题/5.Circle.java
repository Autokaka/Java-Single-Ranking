package Circle;

public class Circle
{
	private int x, y;
	private double r;
	Circle(int x, int y, double r)
	{
		setx(x);
		sety(y);
		setr(r);
	}
	
	public void setx(int x)
	{
		this.x = x;
	}
	public void sety(int y)
	{
		this.y = y;
	}
	public void setr(double r)
	{
		this.r = r;
	}
	
	public int getx()
	{
		return this.x;
	}
	public int gety()
	{
		return this.y;
	}
	public double getr()
	{
		return this.r;
	}
	
	public boolean equals(Circle c)
	{
		if (this.x == c.x && this.y == c.y && this.r == c.r) return true;
		return false;
	}
	
	public boolean intersect(Circle c)
	{
		if ((this.x-c.x)*(this.x-c.x)+(this.y-c.y)*(this.y-c.y) < (this.r + c.r)*(this.r + c.r) && (this.x-c.x)*(this.x-c.x)+(this.y-c.y)*(this.y-c.y) > (this.r - c.r)*(this.r - c.r)) return true;
		return false;
	}
	
	public static void main(String args[])
	{
		int i, j, flag;
		Circle [] c = new Circle [10];
		for (i = 0; i < 10; i++) c[i] = new Circle((int) (1+Math.random()*5), (int) (1+Math.random()*5), 1);
		flag = 0;
		for (i = 0; i < 10; i++)
		{
			if (flag == 1) break;
			for (j = i + 1; j < 10; j++)
				if (c[i].equals(c[j]))
				{
					System.out.println("Yes there are duplicate circles.");
					flag = 1;
					break;
				}
		}
		if (flag == 0) System.out.println("No there are no duplicate circles.");
	}
}
