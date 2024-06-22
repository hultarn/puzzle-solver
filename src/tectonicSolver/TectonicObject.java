package tectonicSolver;

import java.awt.Color;

import frameWork.PuzzleObject;

public class TectonicObject extends PuzzleObject
{
	public Color color;
	
	public TectonicObject(int val, Color c) {
		this.color = c;
		value = val;
	}

}
