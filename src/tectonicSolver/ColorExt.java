package tectonicSolver;

import java.awt.Color;

public class ColorExt extends Color {

	String name;
	public ColorExt(int r, int g, int b, String name) 
	{
		super(r, g, b);
		this.name = name;
	}
}
