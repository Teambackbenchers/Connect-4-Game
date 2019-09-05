package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;



public class UserInterface {
	
	static Board board = new Board();
	static JFrame frameMainWindow;
	static JFrame frameGameOver;
	
	static JPanel panelBoardNumbers;
	static JLayeredPane layeredGameBoard;

	static MiniMax ai = new MiniMax(GameParameters.maxDepth, Constants.X);
	
	public UserInterface() {
		try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		} catch (Exception e) {
			e.printStackTrace();
		}
		createNewGame();
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
	
	public static void createNewGame() {
		board = new Board();
		
		ai.setMaxDepth(GameParameters.maxDepth);
             
		if (frameMainWindow != null) frameMainWindow.dispose();
		frameMainWindow = new JFrame("Minimax Connect-4");
		centerWindow(frameMainWindow, 740, 640);
		Component compMainWindowContents = createContentComponents();
		frameMainWindow.getContentPane().add(compMainWindowContents, BorderLayout.CENTER);

		frameMainWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
			System.exit(0);
			}
		});
		
		frameMainWindow.setFocusable(true);
		
		frameMainWindow.pack();
		frameMainWindow.setVisible(true);

		if (board.getLastSymbolPlayed() == Constants.X) {
			Move aiMove = ai.miniMax(board);
			board.makeMove(aiMove.getCol(), Constants.O);
			game();
		}

	}
	
	
	public static void centerWindow(Window frame, int width, int height) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) (((dimension.getWidth() - frame.getWidth()) / 2) - (width/2));
	    int y = (int) (((dimension.getHeight() - frame.getHeight()) / 2) - (height/2));
	    frame.setLocation(x, y);
	}
	
	public static void placeChecker(int color, int row, int col) {
		String colorString = GameParameters.getColorNameByNumber(color);
		int xOffset = 96 * col;
		int yOffset = 96 * row;
		//System.out.println(colorString);
		ImageIcon checkerIcon = new ImageIcon("C:\\Users\\Hp\\Games\\Connect-4-Game\\res\\images\\" + colorString + ".gif");
		System.out.println(checkerIcon);
		JLabel checkerLabel = new JLabel(checkerIcon);
		checkerLabel.setBounds(50 + xOffset, 50 + yOffset, checkerIcon.getIconWidth(),checkerIcon.getIconHeight());
		layeredGameBoard.add(checkerLabel, 0, 0);
		frameMainWindow.paint(frameMainWindow.getGraphics());
	}
	
	public static void game() {
	
		int row = board.getLastMove().getRow();
		int col = board.getLastMove().getCol();

		int currentPlayer = board.getLastSymbolPlayed();
		
		if (currentPlayer == Constants.X) {
			placeChecker(GameParameters.player1Color, row, col);
		}
		
		if (currentPlayer == Constants.O) {
			placeChecker(GameParameters.player2Color, row, col);
		}
		
		if (board.checkGameOver()) {
			board.setGameOver(true);
			gameOver();
		}
		

	}
	
	public static void aiMove(){

		if (!board.isGameOver()) {
			if (board.getLastSymbolPlayed() == Constants.X) {
				Move aiMove = ai.miniMax(board);
				//System.out.println(aiMove.getRow() + " " + aiMove.getCol());
				board.makeMove(aiMove.getCol(), Constants.O);
				game();
			}
		}

	}
	
	public static void doActionInButton(int row) {
		board.setOverflowOccured(false);
		int previousRow = board.getLastMove().getRow();
		int previousCol = board.getLastMove().getCol();
		int previousLetter = board.getLastSymbolPlayed();
		board.makeMove(row, Constants.X);
		if (!board.hasOverflowOccured()) {
			game();
			aiMove();
		} else {
			board.getLastMove().setRow(previousRow);
			board.getLastMove().setCol(previousCol);
			board.setLastSymbolPlayed(previousLetter);
		}
		frameMainWindow.requestFocusInWindow();
	}
	
	public static Component createContentComponents() {
		
		panelBoardNumbers = new JPanel();
		panelBoardNumbers.setLayout(new GridLayout(1, 7, 6, 4));
		panelBoardNumbers.setBorder(BorderFactory.createEmptyBorder(2, 22, 2, 22));
		
		JButton col1_button = new JButton("1");
		col1_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doActionInButton(0);
			}
		});
		
		JButton col2_button = new JButton("2");
		col2_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doActionInButton(1);
			}
		});
		
		JButton col3_button = new JButton("3");
		col3_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doActionInButton(2);
			}
		});
		
		JButton col4_button = new JButton("4");
		col4_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doActionInButton(3);
			}
		});
		
		JButton col5_button = new JButton("5");
		col5_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doActionInButton(4);
			}
		});
		
		JButton col6_button = new JButton("6");
		col6_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doActionInButton(5);
			}
		});
		
		JButton col7_button = new JButton("7");
		col7_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doActionInButton(6);
			}
		});
		
		panelBoardNumbers.add(col1_button);
		panelBoardNumbers.add(col2_button);
		panelBoardNumbers.add(col3_button);
		panelBoardNumbers.add(col4_button);
		panelBoardNumbers.add(col5_button);
		panelBoardNumbers.add(col6_button);
		panelBoardNumbers.add(col7_button);

		layeredGameBoard = createLayeredBoard();

		JPanel panelMain = new JPanel();
		panelMain.setLayout(new BorderLayout());
		panelMain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		panelMain.add(panelBoardNumbers, BorderLayout.NORTH);
		panelMain.add(layeredGameBoard, BorderLayout.CENTER);

		frameMainWindow.setResizable(false);
		return panelMain;
	}
	
	public static void gameOver() {
		        		        
		frameGameOver = new JFrame("Game over!");
		frameGameOver.setBounds(620, 400, 350, 128);
		centerWindow(frameGameOver, 0, 0);
		JPanel winPanel = new JPanel(new BorderLayout());
		winPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
		
		JLabel winLabel;
		board.checkWinState();
		if (board.getWinner() == Constants.X) {
			winLabel = new JLabel("You win! Start a new game?");
			winPanel.add(winLabel);
		} else if (board.getWinner() == Constants.O) {
			winLabel = new JLabel("Computer AI wins! Start a new game?");
			winPanel.add(winLabel);
		} else {
			winLabel = new JLabel("It's a draw! Start a new game?");
			winPanel.add(winLabel);
		}
		winLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		winPanel.add(winLabel, BorderLayout.NORTH);
		
		JButton yesButton = new JButton("Yes");
		yesButton.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 30));
		yesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frameGameOver.setVisible(false);
				createNewGame();
			}
		});
		
		JButton quitButton = new JButton("Quit");
		quitButton.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 30));
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frameGameOver.setVisible(false);
				System.exit(0);
			}
		});
		
		JPanel subPanel = new JPanel();
		subPanel.add(yesButton);
		subPanel.add(quitButton);
		
		winPanel.add(subPanel, BorderLayout.CENTER);
		frameGameOver.getContentPane().add(winPanel, BorderLayout.CENTER);
		frameGameOver.setResizable(false);
		frameGameOver.setVisible(true);
	}
	
	@SuppressWarnings("static-access")
	public static void main(String[] args){
		UserInterface connect4 = new UserInterface();
		connect4.createNewGame();
	}
	
}
