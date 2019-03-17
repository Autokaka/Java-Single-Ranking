import java.util.*;

public class randnums
{
	public static void main (String args[])
	{
		ArrayList randnum = new ArrayList();
		for (int i = 0; i < 20; i++) randnum.add((int)(1 + Math.random()*21));
		Collections.sort(randnum, Collections.reverseOrder());
		for (Object o: randnum) System.out.print(o + "\t");
	}
}
