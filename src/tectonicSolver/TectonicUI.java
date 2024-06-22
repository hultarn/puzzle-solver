package tectonicSolver;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JPanel;

import frameWork.PuzzleObject;
import frameWork.Solver;
import frameWork.UI;

public class TectonicUI extends UI
{
	static ArrayList<ColorExt> cl = new ArrayList<ColorExt>() { 
        { 
            add(new ColorExt(255,0,0, "Red")); 
            add(new ColorExt(128,255,0, "Green"));  
            add(new ColorExt(0,138,255, "Blue"));  
            add(new ColorExt(255,255,200, "Yellow")); 
            add(new ColorExt(255,51,255, "Pink"));  
            add(new ColorExt(127,0,255, "Purple"));  
            add(new ColorExt(255,255,255, "Black"));  
        } 
    }; 
	
    static Iterator<ColorExt> ite = cl.iterator();
    ColorExt selectedColor;
    
	public TectonicUI(int x, int y) 
	{
		super(x, y, "Tectonic");
	}
	
	private JButton makeColorBtn() 
	{
		JButton btnColor = new JButton("Color...");
		btnColor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!ite.hasNext())
					ite = cl.iterator();				
				selectedColor = ite.next();
				
				btnColor.setText("Color = "+selectedColor.name);				
			}
		});
		return btnColor;
	}

	@Override
	protected void addCustomComponents() 
	{
		JButton btnColor = makeColorBtn();	
		controlPanel_SOUTH.add(btnColor);
	}

	@Override
	protected void addCustomListener(JPanel p) 
	{
		p.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        p.setBackground(selectedColor);
		    }
		});
	}

	@Override
	protected Solver classThing(PuzzleObject[][] arr) 
	{
		return new TectonicSolver(arr);
	}

	@Override
	protected PuzzleObject transformMaker(int parseInt, Color background) {
		return new TectonicObject(parseInt, background);
	}
}
