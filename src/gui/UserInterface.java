package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.peer.ButtonPeer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.sun.xml.internal.ws.api.ResourceLoader;

public class UserInterface {
	
	static JLayeredPane layeredGameBoard;
	static JFrame mainGameWindow;
	static ConsoleBoard board;
	
	static MiniMax minmax = new MiniMax(4,Player.one);
	
	static JPanel columnPanel;
	
	public UserInterface() {
		try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static JLayeredPane createLayeredBoard() {
		layeredGameBoard = new JLayeredPane();
		layeredGameBoard.setPreferredSize(new Dimension(740, 640));
		layeredGameBoard.setBorder(BorderFactory.createTitledBorder("Connect-4"));

		ImageIcon imageBoard = new ImageIcon("C:\\Users\\Hp\\Games\\Connect-4-Game\\res\\images\\Board1.gif");
		JLabel imageBoardLabel = new JLabel(imageBoard);

		imageBoardLabel.setBounds(20, 20, imageBoard.getIconWidth(), imageBoard.getIconHeight());
		layeredGameBoard.add(imageBoardLabel, 0, 1);

		return layeredGameBoard;
	}
	
	public static void runGame() {
		
		board = new ConsoleBoard();
		
		if(mainGameWindow != null ) {
			mainGameWindow.dispose();
		}
		
		mainGameWindow = new JFrame("Connect-4 Game");
		Component mainWindowComponent = creatContentComponent();
		mainGameWindow.getContentPane().add(mainWindowComponent, BorderLayout.CENTER);
		
		mainGameWindow.setFocusable(true);
		mainGameWindow.pack();
		mainGameWindow.setVisible(true);
	}
	
	public static void placeColorCircle(Color color, int row, int col) {
		
		int xlineOffset = 96 * col;
		int ylineOffset = 96 * row;
		
		ImageIcon playerImageCircle = new ImageIcon(Resource.load("res/images"+color.toString()+".gif"));
		JLabel circleLabel = new JLabel(playerImageCircle);
		circleLabel.setBounds(50+xlineOffset, 50+ylineOffset, playerImageCircle.getIconWidth(), playerImageCircle.getIconHeight());
		layeredGameBoard.add(circleLabel, 0, 0);
		mainGameWindow.paint(mainGameWindow.getGraphics());
	}
	
	public static void doActionInButton(int row) {
		board.setOverflowBoard(false);
		int previousRow = board.getLastMove().getRow();
		int previousCol = board.getLastMove().getCol();
		int previousPlayer = board.getLastPlayerPlayed();
		board.createMove(row, Player.one);
		
		if(!board.hasOverflowOccured()) {
			game();
			computerMove();
		}else {
			board.getLastMove().setRow(previousRow);
			board.getLastMove().setCol(previousCol);
			board.setLastPlayerPlayed(previousPlayer);
		}
		mainGameWindow.requestFocusInWindow();
	}

	private static Component creatContentComponent() {
		// TODO Auto-generated method stub
		
		columnPanel = new JPanel();
		columnPanel.setLayout(new GridLayout(1,7,6,4));
		columnPanel.setBorder(BorderFactory.createEmptyBorder(2, 22, 2, 22));
		JButton col1Button = new JButton("1");
		JButton col2Button = new JButton("2");
		JButton col3Button = new JButton("3");
		JButton col4Button = new JButton("4");
		JButton col5Button = new JButton("5");
		JButton col6Button = new JButton("6");
		JButton col7Button = new JButton("7");
		
		col1Button.addActionListener((ActionEvent ae) ->{
			
			doActionInButton(0);
		});
		
		col2Button.addActionListener((ActionEvent ae)-> {
			doActionInButton(1);
		});
		
		col3Button.addActionListener((ActionEvent ae)->{
			doActionInButton(2);
		});
		
		col4Button.addActionListener((ActionEvent ae)->{
			doActionInButton(3);
		});
		
		col5Button.addActionListener((ActionEvent ae)->{
			doActionInButton(4);
		});
		
		col6Button.addActionListener((ActionEvent ae)->{
			doActionInButton(5);
		});
		
		col7Button.addActionListener((ActionEvent ae)->{
			doActionInButton(6);
		});
		columnPanel.add(col1Button);
		columnPanel.add(col2Button);
		columnPanel.add(col3Button);
		columnPanel.add(col4Button);
		columnPanel.add(col5Button);
		columnPanel.add(col6Button);
		columnPanel.add(col7Button);
		
		layeredGameBoard = createLayeredBoard();
		
		JPanel boardMainPanel = new JPanel();
		boardMainPanel.setLayout(new BorderLayout());
		boardMainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		boardMainPanel.add(columnPanel, BorderLayout.NORTH);
		boardMainPanel.add(layeredGameBoard, BorderLayout.CENTER);
		return boardMainPanel;
	}
	
	public static void computerMove() {
		if(!board.isGameOver()) {
			if(board.getLastPlayerPlayed() == Player.one) {
				Move computerMove = minmax.miniMax(board);
				board.createMove(computerMove.getCol(), Player.two);
				game();
			}
		}
	}
	
	public static void game() {
		int row = board.getLastMove().getRow();
		int col = board.getLastMove().getCol();
		
		int currentPlayer = board.getLastPlayerPlayed();
		
		if(currentPlayer == Player.one) {
			placeColorCircle(Color.BLACK, row, col);
		}
		
		if(currentPlayer == Player.two) {
			placeColorCircle(Color.RED, row, col);
		}
		
		if(board.checkGameOver()) {
			board.setGameOver(true);
		}
	}

}
