package frameWork;

import java.util.ArrayList;

public abstract class Solver 
{
	boolean stop = false;
	protected static PuzzleObject[][] arr;
	public static ArrayList<int[][]> solutionsList = new ArrayList<int[][]>();
	protected int y;
	protected int x;
	
	public Solver(PuzzleObject[][] tArr)
	{
		arr = tArr;
		this.y = arr.length;
		this.x = arr[0].length;
	}
	
	public void solve() 
	{	
		for(int y = 0; y < arr.length; y ++)
		{						
			for(int x = 0; x < arr[0].length; x ++)
			{
				if(arr[y][x].value == 0)
				{					
					int maxNum = getMaxNrm(y, x);
					for(int n = 1; n <= maxNum; n++)
					{
						if(stop)
							return;
									
						if(possible(y, x, n))
						{
							arr[y][x].value = n;
							solve();
							arr[y][x].value = 0;
						}
					}
					return;
				}
			}
		}
		stop = true;
		add2Solutions();		
	}
	
	protected abstract int getMaxNrm(int y, int x);

	protected abstract boolean possible(int y, int x, int n);

	private void add2Solutions() 
	{	
		int[][] temp = new int[arr.length][arr[0].length]; 
		
		for(int i = 0; i< arr.length; i++)
			  for(int j = 0; j < arr[i].length; j++)
				  temp[i][j] = arr[i][j].value;
		
		solutionsList.add(temp);
	}
}
