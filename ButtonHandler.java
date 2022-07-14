import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


public class ButtonHandler implements MouseListener {
	private int row;
	private int column;
	private MineGrid mineGrid;
	private int flaggedMines = 0;
	private int someint = 0;
	private int life=0;
	private int numberOfMines;
	private JButton[][] buttons;
	private Image image;
	private Image flagImage;
	private Image blankImage;
	private static boolean[][] flagged;
	private static boolean[][] opened;
	private static int flagNumber;
	

	public ButtonHandler(int row, int column, MineGrid mineGrid, int numberOfMines, JButton[][] buttons) {
		this.row = row;
		this.column = column;
		this.mineGrid = mineGrid;
		this.numberOfMines = numberOfMines;
		this.buttons = buttons;
		flagged = new boolean[MineSweeper.SIZE][MineSweeper.SIZE];
		opened = new boolean[MineSweeper.SIZE][MineSweeper.SIZE];

		try {
			this.blankImage = ImageIO.read(new File("images/blank.gif"));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		try {
			this.flagImage = ImageIO.read(new File("images/bombflagged.gif"));
		} catch (IOException e2) {
			e2.printStackTrace();
		}

	}
	public static int getFlagNumbers(){
		return flagNumber;
	}

	public void opening(int i,int j){
		this.opened[i][j]=true;
	}
	
	public static void countFlags(){
		flagNumber=0;
		for(int r=0;r<flagged.length;r++){
			for(int c=0;c<flagged[0].length;c++){
				if(flagged[r][c])
					flagNumber++;
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 1) {

			if (this.mineGrid.isMINE(this.row, this.column)) {
				MineSweeper.validat(2);
				for(int r=0; r<buttons.length;r++){
					for(int c=0;c<buttons[0].length;c++){
						if(mineGrid.isMINE(r, c)){
							try {
								image = ImageIO.read(new File("images/bombrevealed.gif"));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							buttons[r][c].setIcon(new ImageIcon(image));
						}
					}
				}
				try {
					image = ImageIO.read(new File("images/bombdeath.gif"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				buttons[this.row][this.column].setIcon(new ImageIcon(image));



				JOptionPane.showMessageDialog(null, "You Clicked on a Mine");
				System.exit(0);
			} else if (this.mineGrid.getCellContent(row, column) == 0) {


				expendzeros(row, column);

				if(flagged[row][column]){
					flagged[row][column]=false;
				}
				countFlags();
				MineSweeper.setFlagNum(flagNumber);
				
			} else {
				if (e.getSource() instanceof JButton) {
				opened[this.row][this.column]=true;

					try {
						image = ImageIO.read(new File("images/open"+String.valueOf(this.mineGrid.getCellContent(this.row, this.column))+".gif"));
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					button.setIcon(new ImageIcon(image));
					if(flagged[row][column]){
						flagged[row][column]=false;
					}
				countFlags();
				MineSweeper.setFlagNum(flagNumber);
				}
			}
			
		} else if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {
			button = (JButton) e.getSource();

			if(!opened[this.row][this.column]){	

				if(!flagged[this.row][this.column]){

					System.out.println("number of mines: "+numberOfMines);
					System.out.println("flagged mine : "+MineSweeper.getFlaggedMines());

					button.setIcon(new ImageIcon(flagImage));
					if (mineGrid.isMINE(this.row, this.column)) {
						System.out.println("flagged mines get +1");
						MineSweeper.setFlaggedMines(MineSweeper.getFlaggedMines()+1);
					}
					flagged[this.row][this.column]=true;

					countFlags();
					MineSweeper.setFlagNum(flagNumber);

				}else {
					button.setIcon(new ImageIcon(blankImage));
					flagged[this.row][this.column]=false;
					
					countFlags();
					MineSweeper.setFlagNum(flagNumber);

					if (mineGrid.isMINE(this.row, this.column)) {
						flaggedMines -= 1;
						MineSweeper.setFlaggedMines(MineSweeper.getFlaggedMines()-1);
						System.out.println("flagged mines get -1");

					}
					flagged[this.row][this.column]=false;
				}
				if (flaggedMines == numberOfMines || MineSweeper.getFlaggedMines() == numberOfMines) {
					MineSweeper.validat(3);
					JOptionPane.showMessageDialog(null, "You’re a genius.");
					System.exit(0);
				}
			}
		}
	}

	public int getFlaggedMines(){
		return MineSweeper.getFlaggedMines();
		//return flaggedMines;
	}
	private void expendzeros(int i, int j){

		if(opened[i][j] == false) {
			opened[i][j]=true;
			
			opening(i,j);
			try {
				image = ImageIO.read(new File("images/open"+String.valueOf(this.mineGrid.getCellContent(i, j))+".gif"));
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			buttons[i][j].setIcon(new ImageIcon(image));

			if (this.mineGrid.getCellContent(i, j) == 0) {
				if (i > 0){
					expendzeros(i-1, j);
				}
				if (i < MineSweeper.SIZE-1){
					expendzeros(i+1, j);
				}
				if (j > 0){
					expendzeros(i, j-1);
				}
				if (j < MineSweeper.SIZE-1){
					expendzeros(i,j+1);
				}
				if (i > 0 && j > 0){
					expendzeros(i-1, j-1);
				}
				if (i < MineSweeper.SIZE -1 && j > 0){
					expendzeros(i+1, j-1);
				}
				if (i > 0 && j < MineSweeper.SIZE -1){
					expendzeros(i-1, j+1);
				}
				if (i < MineSweeper.SIZE -1 && j < MineSweeper.SIZE - 1){
					expendzeros(i+1,j+1);
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
