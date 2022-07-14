import java.util.Random;

public class MineGrid {
    public int[][] mineInfo;
    private static final int MINE = -1;

    public MineGrid(int numRows, int numCols,int numMines){
        mineInfo = new int[numRows][numCols];
        initializeCells();
        placeMines(numMines);
        setMineInformation();
    }

    private void initializeCells(){
        for(int i=0;i<mineInfo.length;i++){
            for(int j=0;j<mineInfo[0].length;j++){
                mineInfo[i][j]=0;
            }
        }
    }

    public void placeMines(int numMines){
        Random rand = new Random();
        for(int i=0;i<numMines;i++){
            int r= rand.nextInt(mineInfo.length);
            int c= rand.nextInt(mineInfo[0].length);
            if(mineInfo[r][c]!=MINE)
                mineInfo[r][c]=MINE;
            else
                i--;
        }
    }

    private void setMineInformation(){
        for(int i = 0; i< mineInfo.length;i++){
            for(int j=0; j<mineInfo[0].length;j++){
                if(mineInfo[i][j]==MINE){
                    inc(i-1, j-1);
                    inc(i-1,j);
                    inc(i-1,j+1);

                    inc(i,j-1);
                    inc(i,j+1);

                    inc(i+1,j-1);
                    inc(i+1,j);
                    inc(i+1,j+1);
                }
            }
        }
    }

    private void inc(int r,int c){
        if(isIns(r, c) && !isMINE(r, c))
            mineInfo[r][c]++;
    }
    
    public boolean isMINE(int i,int j){
        return mineInfo[i][j] == MINE;
    }
    
    public boolean isIns(int r,int c){
        return (r>=0 && r< mineInfo.length)&& 
        (c>=0 && c<mineInfo[0].length);
    }
    
    public int getCellContent(int i,int j){
        return mineInfo[i][j];
    }

    public void printboard(){
        for(int i=0;i<mineInfo.length;i++){
            for(int j=0;j<mineInfo[0].length;j++){
                System.out.print(mineInfo[i][j]+" ");
            }
            System.out.println();
        }
    }
}
