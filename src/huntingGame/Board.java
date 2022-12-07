package huntingGame;

import java.lang.Math;
/**
 *
 * @author aliafzal
 */

public class Board 
{
    Slot[][] board;
    final int boardSize;
    int pi,pj;
    
    public Board(int size)
    {
        boardSize = size;
        board = new Slot[boardSize][boardSize];
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++)
            {
                board[i][j] = new Slot();
            }
        }
        board[0][0].SetValue("H");
        board[0][boardSize-1].SetValue("H");
        board[boardSize-1][0].SetValue("H");
        board[boardSize-1][boardSize-1].SetValue("H");
        Integer mid = pi = pj = (boardSize/2);
        System.out.println(mid);
        board[mid][mid].SetValue("F");
    }
    
    public Slot GetSlot(int i,int j){ return board[i][j]; }
    
    public void SetPlayerPos(int oi,int oj,int i,int j,String p)
    { 
        board[oi][oj].SetValue("");
        board[i][j].SetValue(p);
        if(p.equals("F")){
            pi = i;
            pj = j;
        }
    }
    
    public int GetBoardSize(){ return boardSize; }
    
    public Boolean isOver()
    {
        for(int i=0;i<boardSize;i++)
        { 
            for(int j=0;j<boardSize;j++)
            {
                if(board[i][j].GetValue().equals("H"))
                {
                    if(Math.abs(pi-i)<=1 && Math.abs(pj-j)<=1){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
