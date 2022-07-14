import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class ToppanelGUI extends JPanel {
    JTextField mines;
    JTextField time;
    JButton button;
    public int facekey;
    public Image image;
    String name;
    public int flagNum;
    Font font;

	public int flaggedMines = 0;

    public void setFlaggedMines(int flaggedMines){
        this.flaggedMines = flaggedMines;
    }

    public int getFlaggedMines(){
        return flaggedMines;
    }
	
    public ToppanelGUI(){
        
        String filename = "images/digital-7.ttf";
        
        try {
            font = Font.createFont(Font.TRUETYPE_FONT,new File(filename));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        font = font.deriveFont(Font.BOLD,35);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        
        mines = new JTextField(" "+String.valueOf(MineSweeper.NUMBER_OF_MINES-ButtonHandler.getFlagNumbers()));
        mines.setBackground(Color.BLACK);
        mines.setForeground(Color.RED);
        mines.setFont(font);
        // mines.setFont(font);
        time = new JTextField("0");
        time.setBackground(Color.BLACK);
        time.setForeground(Color.RED);
        time.setFont(font);
        time.setLayout(new FlowLayout());

        button = new JButton();
        creator();
        this.setLayout(new GridLayout(1,5));
        validat(1);
    }

    public void creator(){
        add(mines);
        add(new JLabel());
        add(button);
        setImage();
        add(new JLabel());
        add(time);
    }

    public void setFacekey(int facekey){
        this.facekey=facekey;
    }
    public void setFlagNum(int flagNum){
        this.flagNum=flagNum;
        mines.setText(String.valueOf(MineSweeper.NUMBER_OF_MINES-ButtonHandler.getFlagNumbers()));
    }
    public int getFlagNum() {
        return flagNum;
    }

    public void validat(int facekey){
        this.facekey=facekey;
        setImage();
    }

    private void setImage(){
        if(facekey==1){
            name="facesmile";
            try {
                image = ImageIO.read(new File("images/"+name+".gif"));
                button.setIcon(new ImageIcon(image));
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(facekey==2){
            name="facedead";
            try {
                image = ImageIO.read(new File("images/"+name+".gif"));
                button.setIcon(new ImageIcon(image));
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }if(facekey==3){
            name="facewin";
            try {
                image = ImageIO.read(new File("images/"+name+".gif"));
                button.setIcon(new ImageIcon(image));
            } catch (IOException e) {
                e.printStackTrace();
            }
			
        }
    }

    

}
