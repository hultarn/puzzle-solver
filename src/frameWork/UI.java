package frameWork;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class UI extends JFrame
{
	static ArrayList<JButton> list = new ArrayList<JButton>();
	static ArrayList<JPanel> panelList = new ArrayList<JPanel>();
	protected JPanel controlPanel_SOUTH;
	protected JPanel controlPanel_WEST;
	protected JPanel controlPanel_EAST;
	protected JPanel controlPanel_NORTH;
	protected int x;
	protected int y;
	
	public UI(int x, int y, String windowName) 
	{
		this.x = x;
		this.y = y;
		JFrame frame = new JFrame(windowName);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		controlPanel_SOUTH = new JPanel();
		controlPanel_WEST = new JPanel();
		controlPanel_EAST = new JPanel();
		controlPanel_NORTH = new JPanel();
		
		JPanel panel = makeGridOfButton(x, y);							
		JButton done = makeDoneBtn(x, y, panel);		
		
		addCustomComponents();
		controlPanel_SOUTH.add(done);
		
		mainPanel.add(panel, BorderLayout.CENTER);
		mainPanel.add(controlPanel_SOUTH, BorderLayout.SOUTH);
		mainPanel.add(controlPanel_WEST, BorderLayout.WEST);
		mainPanel.add(controlPanel_EAST, BorderLayout.EAST);
		mainPanel.add(controlPanel_NORTH, BorderLayout.NORTH);
			
		frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
	}

	private JButton makeDoneBtn(int x, int y, JPanel panel) 
	{
		JButton done = new JButton("Done");
		done.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				PuzzleObject[][] arr = transformGraphicsToArray();
				Solver tSolve = classThing(arr);
				tSolve.solve();			
				try {
					showSolution(x, y);
				} 
				catch (Exception ex) {}
			}

			private void showSolution(int x, int y) throws Exception 
			{
				int size = Solver.solutionsList.size();
				if(size < 1)
					System.out.print("No solutions found");
					
				int[][] tArr = Solver.solutionsList.get(0);
				
				for(int[] t : tArr)
				{
					for(int tt : t)
						System.out.print(""+tt+" ");
					System.out.println("");
				}
				
				int idX = 0;
				for(int i = 0; i < x; i++)
					for(int j = 0; j < y; j++)
					{
						JPanel temp = panelList.get(idX);		
						JButton btn = (JButton) temp.getComponent(0);
						int value = tArr[i][j];
						btn.setText(""+value);
						idX++;
					}			
			}

			private PuzzleObject[][] transformGraphicsToArray() 
			{ 
				PuzzleObject[][] arr = new PuzzleObject[x][y];
				
				for(int i = 0; i < x; i++)
					for(int j = 0; j < y; j++)
					{
						JPanel temp = panelList.get(i*x+j);		
						JButton btn = (JButton) temp.getComponent(0);
						PuzzleObject t = transformMaker(Integer.parseInt(btn.getText()), temp.getBackground());
						arr[i][j] = t;
					}
				return arr;
			}
		});
		return done;
	}

	protected abstract PuzzleObject transformMaker(int parseInt, Color background);

	private JPanel makeGridOfButton(int x, int y) 
	{
		JPanel temp = new JPanel();
		temp.setLayout(new GridLayout(x, y));
		
		for(int i = 0; i < x; i++)
		{
			for(int j = 0; j < y; j++)
			{
				JPanel p = new JPanel();
				JButton btn = new JButton("0");
				
				btn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						JButton t = (JButton) e.getSource();
						int counter = Integer.parseInt(t.getText());
						counter++;

						if(counter > 9)
							counter = 0;
						
						t.setText(""+counter);	
					}
				});
				
				p.add(btn);
				
				addCustomListener(p);

				panelList.add(p);
				temp.add(p);
			}			
		}
		return temp;
	}

	protected abstract Solver classThing(PuzzleObject[][] p);
	protected abstract void addCustomListener(JPanel p);
	protected abstract void addCustomComponents();
}
