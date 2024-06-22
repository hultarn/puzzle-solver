package sudokuSolver;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import frameWork.PuzzleObject;
import frameWork.Solver;
import frameWork.UI;

public class SudokuUI extends UI
{
	static int count = 0;
	ArrayList<Integer> list;
	static int[] listInt = {0,1,2,6,7,8};
	static int[] listInt2 = {3,4,5};
	
	public SudokuUI(int x, int y) {
		super(x, y, "Sudoku");
	}
	@Override
	protected void addCustomListener(JPanel p) 
	{
		// TODO: Fix this wth
		list = new ArrayList<Integer>(); 
		for (int i : listInt)
		{
			list.add(i);
			list.add(i+9);
			list.add(i+9*2);
			list.add(i+9*6);
			list.add(i+9*7);
			list.add(i+9*8);
		}
		
		for (int i : listInt2)
		{
			list.add(i+9*3);
			list.add(i+9*4);
			list.add(i+9*5);
		}
		
		if(list.contains(count))
			p.setBackground(Color.LIGHT_GRAY);
		count++;
	}

	@Override
	protected void addCustomComponents() 
	{

	}
	@Override
	protected Solver classThing(PuzzleObject[][] arr) 
	{
		return new SudokuSolver(arr);
	}
	@Override
	protected PuzzleObject transformMaker(int parseInt, Color background) {
		return new SudokuObject(parseInt);
	}
}
