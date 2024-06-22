package sudokuSolver;

import frameWork.PuzzleObject;
import frameWork.Solver;

public class SudokuSolver extends Solver
{
	public SudokuSolver(PuzzleObject[][] tArr) {
		super(tArr);
	}

	@Override
	protected int getMaxNrm(int y, int x) 
	{
		return 9;
	}

	@Override
	protected boolean possible(int y, int x, int n) 
	{
		return checkIfValidRow(y, x, n) && checkIfValidGrid(y, x, n) && checkIfValidCol(y, x, n);
	}

	private boolean checkIfValidGrid(int y, int x, int n) 
	{
		  y = (y / 3) * 3 ;
		    x = (x / 3) * 3 ;

		   for( int r = 0; r < 3; r++ )
		      for( int c = 0; c < 3; c++ )
		       if( arr[y+r][x+c].value == n)
		         return false ;

		   return true ;
	}

	private boolean checkIfValidCol(int y, int x, int n) 
	{
		for(int i = 0; i < 9; i++)
		{
			if(arr[i][x].value == n && i != y)
			{
				return false;
			}
		}
		return true;
	}

	private boolean checkIfValidRow(int y, int x, int n) 
	{
		for(int i = 0; i < 9; i++)
		{
			if(arr[y][i].value == n && i != x)
			{
				return false;
			}
		}
		return true;
	}
}
