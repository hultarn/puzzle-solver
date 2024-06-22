package tectonicSolver;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import frameWork.PuzzleObject;
import frameWork.Solver;

public class TectonicSolver extends Solver
{
	static boolean stop = false;
	
	static Point[] pArr = 
	{
		new Point(-1, -1),
		new Point(-1, 0),
		new Point(-1, 1),
		new Point(0, -1),
		new Point(0, 1),
		new Point(1, -1),
		new Point(1, 0),
		new Point(1, 1),
	};
	
	public TectonicSolver(PuzzleObject[][] tArr)
	{
		super(tArr);
	}
	
	private boolean checkSameNumberInSameSection(int y, int x, int n) 
	{
		ArrayList<TectonicObject> tempArr = getCurrentSection(y , x);
		
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>(tempArr.size());		
		for(int j = 0; j < tempArr.size(); j++)
		{
			int key = tempArr.get(j).value;
			hm.put(key, hm.containsKey(key) ? (hm.get(key)+1) : 1);
		}			
		hm.put(n, hm.containsKey(n) ? (hm.get(n)+1) : 1);
		
		for (Integer key: hm.keySet()) 
			if(hm.get(key) > 1 && key != 0)
				return false;
		return true;
	}

	private ArrayList<TectonicObject> getCurrentSection(int y, int x) 
	{
		ArrayList<TectonicObject> tempArr = new ArrayList<TectonicObject>();
		
		for(int i = 0; i < arr.length; i++)	
			for(int j = 0; j < arr[0].length; j++)
			{
				TectonicObject t = (TectonicObject) arr[i][j];
				Color c = (t.color);
				
				TectonicObject t2 = (TectonicObject) arr[y][x];
				Color c2 = t2.color;
				
				if(c.getRGB() == c2.getRGB())
					tempArr.add(new TectonicObject(arr[i][j].value, c));
			}
		return tempArr;
	}

	private boolean checkSurroundings(int y, int x, int n) 
	{
		for(Point p : pArr)
		{
			try {
					if(arr[y+p.y][x+p.x].value == n)
						return false;
				}
			catch(Exception e){}
		}
		return true;		
	}

	@Override
	protected int getMaxNrm(int y, int x) 
	{
		return getCurrentSection(y, x).size();
	}

	@Override
	protected boolean possible(int y, int x, int n) {
		return checkSurroundings(y, x , n) && checkSameNumberInSameSection(y, x , n) && arr[y][x].value == 0;
	}
}
