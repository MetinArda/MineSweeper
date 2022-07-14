import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

class MineSweeperGUI extends JPanel {
	private MineGrid mineGrid;
	private int numberOfRows;
	private int numberOfColumns;
	private int numberOfMines;
	private JButton[][] buttons;
	private boolean[][] flagged;
	private Image image;

	public MineSweeperGUI(int numberOfRows, int numberOfColumns, int numberOfMines) {

		JButton[][] buttons = new JButton[numberOfRows][numberOfColumns];
		this.buttons = buttons;
		this.numberOfRows = numberOfRows;
		this.numberOfColumns = numberOfColumns;
		this.numberOfMines = numberOfMines;
		this.mineGrid = new MineGrid(numberOfRows, numberOfColumns, numberOfMines);
		this.setLayout(new GridLayout(numberOfRows, numberOfColumns));
		flagged = new boolean[MineSweeper.SIZE][MineSweeper.SIZE];
		buttonCreater();
	}

	public boolean[][] getFlagged(){
		return flagged;
	}

	public void buttonCreater() {
		for (int i = 0; i < numberOfRows; ++i) {
			for (int j = 0; j < numberOfColumns; ++j) {
				JButton button = new JButton();
				button.addMouseListener(new ButtonHandler(i, j, this.mineGrid, numberOfMines, buttons));
				try {
					image = ImageIO.read(new File("images/blank.gif"));
					
				} catch (IOException e) {
					e.printStackTrace();
				}
                button.setIcon(new ImageIcon(image));
				buttons[i][j] = button;
				add(button);
			}
		}
	}
}
