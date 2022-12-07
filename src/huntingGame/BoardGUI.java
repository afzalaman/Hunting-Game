package huntingGame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author aliafzal
 */
public class BoardGUI 
{
    JButton[][] buttons;
    Board board;
    JPanel boardPanel;
    JLabel player,attemptL;
    
    int attempt = 0;
    int currI,currJ;
    int boardSize;
    Boolean playerTurn,isOver=false;
    Piece selected;
    String winner;
    
    public BoardGUI(int boardSize)
    {
        this.boardSize = boardSize;
        playerTurn = true;
        selected = Piece.None;
        winner = "";
        board = new Board(boardSize);
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(boardSize,boardSize));
        buttons = new JButton[boardSize][boardSize];
        for (int i=0;i<boardSize;i++)
        {
            for (int j=0;j<boardSize;j++)
            {
                JButton button = new JButton();
                button.setText(board.GetSlot(i, j).GetValue());
                button.addActionListener(new ButtonListener(i, j));
                button.setPreferredSize(new Dimension(80, 40));
                buttons[i][j] = button;
                boardPanel.add(button);
            }
        }
        player = new JLabel("Fugitive");
        player.setHorizontalAlignment(JLabel.CENTER);
        
        attemptL = new JLabel("0");
        attemptL.setHorizontalAlignment(JLabel.CENTER);   
    }
    
    public void update()
    {
        player.setText(playerTurn? "Fugitive":"Hunter");
        int n = board.GetBoardSize();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                buttons[i][j].setText(board.GetSlot(i, j).GetValue());
            }
        }
    }
    
    public JPanel GetBoardPanel(){ return boardPanel; }
    
    public JLabel GetPlayerLabel() { return player; }
    public JLabel GetAttemptLabel() { return attemptL; }
    
    public void restart()
    {
        isOver = false;
        attempt = 0;
        playerTurn = true;
        winner = "";
        selected = Piece.None;
        board = new Board(boardSize);
        attemptL.setText(String.valueOf(attempt));
        player.setText(String.valueOf("Fugitive"));
    }
    
    class ButtonListener implements ActionListener 
    {
        int i,j;
        public ButtonListener(int i,int j){
            this.i = i;
            this.j = j;
        }
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(isOver)
                return;
            
            if(selected == Piece.None)
            {
                if(board.GetSlot(i, j).GetValue().equals(""))
                {
                    return;
                }
                if(!playerTurn && board.GetSlot(i, j).GetValue().equals("F"))
                    return;
                if(playerTurn && board.GetSlot(i, j).GetValue().equals("H"))
                    return;
                
                selected = board.GetSlot(i, j).GetValue().equals("F") && playerTurn ? Piece.Fugitive : Piece.Hunter;
                currI = i;
                currJ = j;
            }
            else if(selected == Piece.Fugitive)
            {
                if(!playerTurn) return;
                if(board.GetSlot(i, j).isEmpty() && (Math.abs(currI-i)<=1 && Math.abs(currJ-j)<=1))
                {
                    System.out.println("Enemy Turn");
                    board.SetPlayerPos(currI,currJ, i, j,"F");
                    selected = Piece.None;
                    playerTurn = false;
                    isOver = board.isOver() || attempt>=board.GetBoardSize()*4 ;
                }
            }
            else
            {
                if(playerTurn) return;
                if(board.GetSlot(i, j).isEmpty() && (Math.abs(currI-i)<=1 && Math.abs(currJ-j)<=1))
                {
                    board.SetPlayerPos(currI,currJ,i, j,"H");
                    attempt++;
                    attemptL.setText(String.valueOf(attempt));
                    System.out.println(attempt);
                    System.out.println("Player Turn");
                    selected = Piece.None;
                    playerTurn = true;
                }
            }
            update();
            //System.out.println(isOver);
            if(isOver)
            {
                winner = !board.isOver() && attempt>=board.GetBoardSize()*4? "Fugitive" : "Hunter";
                System.out.println(winner+" Wins!");
                JOptionPane.showMessageDialog(boardPanel, winner, "Congrats!",
                        JOptionPane.PLAIN_MESSAGE);
                restart();
                update();
            }
        }
    }
}
enum Piece { Fugitive, Hunter, None }
