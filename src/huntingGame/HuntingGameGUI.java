package huntingGame;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


/**
 *
 * @author aliafzal
 */
public class HuntingGameGUI 
{
    JFrame frame;
    BoardGUI boardGUI;
    
    final int INITIAL_BOARD_SIZE = 5;
    
    public HuntingGameGUI() 
    {
        frame = new JFrame("EscapePlan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        boardGUI = new BoardGUI(INITIAL_BOARD_SIZE);
        frame.getContentPane().add(boardGUI.GetBoardPanel(), BorderLayout.CENTER);
        frame.getContentPane().add(boardGUI.GetPlayerLabel(), BorderLayout.NORTH);
        frame.getContentPane().add(boardGUI.GetAttemptLabel(), BorderLayout.SOUTH);
        
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);
        JMenu newMenu = new JMenu("New");
        menuBar.add(newMenu);
        int[] boardSizes = new int[]{ 3,5,7 };
        for(int boardSize : boardSizes){
            JMenuItem sizeMenuItem = new JMenuItem(boardSize + "x" + boardSize);
            newMenu.add(sizeMenuItem);
            sizeMenuItem.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    frame.getContentPane().remove(boardGUI.GetBoardPanel());
                    frame.getContentPane().remove(boardGUI.GetPlayerLabel());
                    frame.getContentPane().remove(boardGUI.GetAttemptLabel());
                    boardGUI = new BoardGUI(boardSize);
                    frame.getContentPane().add(boardGUI.GetBoardPanel(),
                            BorderLayout.CENTER);
                    frame.getContentPane().add(boardGUI.GetPlayerLabel(), BorderLayout.NORTH);
                    frame.getContentPane().add(boardGUI.GetAttemptLabel(), BorderLayout.SOUTH);
                    frame.pack();
                }
            });
        }
        
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        gameMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        frame.pack();
        frame.setVisible(true);
    }
}
