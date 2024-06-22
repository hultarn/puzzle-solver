package tectonicSolver;

import java.util.Scanner;

public class TectonicMain 
{
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		int y = s.nextInt();
		int x = s.nextInt();
		s.close();
		new TectonicUI(x, y);
	}
}
