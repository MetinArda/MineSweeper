import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MineSweeper{
	public static final int NUMBER_OF_MINES =10;
	public static final int SIZE =8 ;
	
	public static JFrame frame = new JFrame("Mine Sweeper | # of mines: " + NUMBER_OF_MINES);
	public static ToppanelGUI toppanel = new ToppanelGUI();
	public static JPanel mainpanel = new MineSweeperGUI(SIZE, SIZE, NUMBER_OF_MINES);
	public static Timer  timer;
	public static int t=0;



	public static void main(String[] args) {
		timer=new Timer(1000,new ActionListener(){
			public void actionPerformed(ActionEvent e)
		{
			t++;
			toppanel.time.setText(String.valueOf(t));
		}
		});
		
		frame.add(toppanel,BorderLayout.NORTH);
		frame.add(mainpanel,BorderLayout.CENTER);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,400);
		// frame.setResizable(false);
		timer.start();
		frame.setVisible(true);

		
	}
	public static void validat(int facekey) {
		toppanel.validat(facekey);
	}

	public static void setFlagNum(int flagNum){
		toppanel.setFlagNum(flagNum);
		frame.validate();
	}
	public int getFlagNum(){
		return toppanel.getFlagNum();
	}

	public static void setFlaggedMines(int flaggedMines){
        toppanel.setFlaggedMines(flaggedMines);
    }

    public static int getFlaggedMines(){
		return toppanel.getFlaggedMines();
	}

	
}
