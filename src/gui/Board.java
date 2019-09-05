package gui;

import java.util.ArrayList;

public class Board {
	
    private Move lastMove;
    private int lastSymbolPlayed;
    
    private int winner;
	private int [][] gameBoard;
	private boolean overflowOccured;
	private boolean gameOver;

	public Board() {
		this.lastMove = new Move();
		this.lastSymbolPlayed = Constants.O;
		this.winner = 0;
		this.gameBoard = new int[6][7];
		this.overflowOccured = false;
		this.gameOver = false;
		for(int i=0; i<6; i++) {
			for(int j=0; j<7; j++) {
				gameBoard[i][j] = Constants.EMPTY;
			}
		}
	}
	
	public Board(Board board) {
		lastMove = board.lastMove;
		lastSymbolPlayed = board.lastSymbolPlayed;
		winner = board.winner;
		gameBoard = new int[6][7];
		this.overflowOccured = false;
		this.gameOver = false;
		for(int i=0; i<6; i++) {
			for(int j=0; j<7; j++) {
				gameBoard[i][j] = board.gameBoard[i][j];
			}
		}
	}
	
	
	public Move getLastMove() {
		return lastMove;
	}
	
	public void setLastMove(Move lastMove) {
		this.lastMove.setRow(lastMove.getRow());
		this.lastMove.setCol(lastMove.getCol());
		this.lastMove.setValue(lastMove.getValue());
	}
	
	
	public int getLastSymbolPlayed() {
		return lastSymbolPlayed;
	}
	
	
	public void setLastSymbolPlayed(int lastLetterPlayed) {
		this.lastSymbolPlayed = lastLetterPlayed;
	}
	
	
	public int[][] getGameBoard() {
		return gameBoard;
	}
	
	
	public void setGameBoard(int[][] gameBoard) {
		for(int i=0; i<6; i++) {
			for(int j=0; j<7; j++) {
				this.gameBoard[i][j] = gameBoard[i][j];
			}
		}
	}
	
	
	public int getWinner() {
		return winner;
	}
	
	
	public void setWinner(int winner) {
		this.winner = winner;
	}
	
	
	public boolean isGameOver() {
		return gameOver;
	}


	public void setGameOver(boolean isGameOver) {
		this.gameOver = isGameOver;
	}
	
	
	public boolean hasOverflowOccured() {
		return overflowOccured;
	}

	
	public void setOverflowOccured(boolean overflowOccured) {
		this.overflowOccured = overflowOccured;
	}
	
	public void makeMove(int col, int letter) {
		try {
			this.lastMove = new Move(getRowPosition(col), col);
			this.lastSymbolPlayed = letter;
			this.gameBoard[getRowPosition(col)][col] = letter;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Column " + (col+1) + " is full!");
			setOverflowOccured(true);
		}
	}
	
	public boolean canMove(int row, int col) {
		if ((row <= -1) || (col <= -1) || (row > 5) || (col > 6)) {
			return false;
		}
		return true;
	}
	
	public boolean checkFullColumn(int col) {
		if (gameBoard[0][col] == Constants.EMPTY)
			return false;
		return true;
	}
	
	public int getRowPosition(int col) {
		int rowPosition = -1;
		for (int row=0; row<6; row++) {
			if (gameBoard[row][col] == Constants.EMPTY) {
				rowPosition = row;
				//System.out.println(row + " "+ col);
			}
		}
		return rowPosition;
	}
	
	public ArrayList<Board> getChildren(int letter) {
		ArrayList<Board> children = new ArrayList<Board>();
		for(int col=0; col<7; col++) {
			if(!checkFullColumn(col)) {
				Board child = new Board(this);
				child.makeMove(col, letter);
				children.add(child);
			}
		}
		return children;
	}
	
	
	public int evaluate() {
		
		int Xlines = 0;
		int Olines = 0;

        if (checkWinState()) {
			if(getWinner() == Constants.X) {
				Xlines = Xlines + 100;
			} else if (getWinner() == Constants.O) {
				Olines = Olines + 100;
			}
		}
		
        Xlines  = Xlines + count3InARow(Constants.X) * 10 + count2InARow(Constants.X);
        Olines  = Olines + count3InARow(Constants.O) * 10 + count2InARow(Constants.O);
		
		return Xlines - Olines;
	}
	
	public boolean checkWinState() {
		
		// Check for 4 consecutive color in a row, horizontally.
		for (int i=5; i>=0; i--) {
			for (int j=0; j<4; j++) {
				if (gameBoard[i][j] == gameBoard[i][j+1]
						&& gameBoard[i][j] == gameBoard[i][j+2]
						&& gameBoard[i][j] == gameBoard[i][j+3]
						&& gameBoard[i][j] != Constants.EMPTY) {
					setWinner(gameBoard[i][j]);
					return true;
				}
			}
		}
		
		// Check for 4 consecutive color in a row, vertically.
		for (int i=5; i>=3; i--) {
			for (int j=0; j<7; j++) {
				if (gameBoard[i][j] == gameBoard[i-1][j]
						&& gameBoard[i][j] == gameBoard[i-2][j]
						&& gameBoard[i][j] == gameBoard[i-3][j]
						&& gameBoard[i][j] != Constants.EMPTY) {
					setWinner(gameBoard[i][j]);
					return true;
				}
			}
		}
		
		// Check for 4 consecutive color in a row, in descending diagonals.
		for (int i=0; i<3; i++) {
			for (int j=0; j<4; j++) {
				if (gameBoard[i][j] == gameBoard[i+1][j+1]
						&& gameBoard[i][j] == gameBoard[i+2][j+2]
						&& gameBoard[i][j] == gameBoard[i+3][j+3] 
						&& gameBoard[i][j] != Constants.EMPTY) {
					setWinner(gameBoard[i][j]);
					return true;
				}
			}
		}
		
		// Check for 4 consecutive color in a row, in ascending diagonals.
		for (int i=0; i<6; i++) {
			for (int j=0; j<7; j++) {
				if (canMove(i-3,j+3)) {
					if (gameBoard[i][j] == gameBoard[i-1][j+1]
							&& gameBoard[i][j] == gameBoard[i-2][j+2]
							&& gameBoard[i][j] == gameBoard[i-3][j+3] 
							&& gameBoard[i][j] != Constants.EMPTY) {
						setWinner(gameBoard[i][j]);
						return true;
					}
				}
			}
		}
		
		setWinner(Constants.EMPTY); 
		return false;

	}
	
    public boolean checkGameOver() {
    	
    	if (checkWinState()) {
    		return true;
    	}
    	
    	for(int row=0; row<6; row++) {
			for(int col=0; col<7; col++) {
				if(gameBoard[row][col] == Constants.EMPTY) {
                    return false;
                }
            }
        }
    	
    	return true;
    }
	
   	public int count3InARow(int playerSymbol) {
		
		int times = 0;
		
		// Check for 3 consecutive color in a row, horizontally.
		for (int i = 5; i >= 0; i--) {
			for (int j = 0; j < 7; j++) {
				if (canMove(i, j + 2)) {
					if (gameBoard[i][j] == gameBoard[i][j + 1]
							&& gameBoard[i][j] == gameBoard[i][j + 2]
							&& gameBoard[i][j] == playerSymbol) {
						times++;
					}
				}
			}
		}

		// Check for 3 consecutive color in a row, vertically.
		for (int i = 5; i >= 0; i--) {
			for (int j = 0; j < 7; j++) {
				if (canMove(i - 2, j)) {
					if (gameBoard[i][j] == gameBoard[i - 1][j]
							&& gameBoard[i][j] == gameBoard[i - 2][j]
							&& gameBoard[i][j] == playerSymbol) {
						times++;
					}
				}
			}
		}

		// Check for 3 consecutive color in a row, in descending diagonal.
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if (canMove(i + 2, j + 2)) {
					if (gameBoard[i][j] == gameBoard[i + 1][j + 1]
							&& gameBoard[i][j] == gameBoard[i + 2][j + 2]
							&& gameBoard[i][j] == playerSymbol) {
						times++;
					}
				}
			}
		}

		// Check for 3 consecutive color in a row, in ascending diagonal.
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if (canMove(i - 2, j + 2)) {
					if (gameBoard[i][j] == gameBoard[i - 1][j + 1]
							&& gameBoard[i][j] == gameBoard[i - 2][j + 2]
							&& gameBoard[i][j] == playerSymbol) {
						times++;
					}
				}
			}
		}

		return times;
				
	}
	
   	public int count2InARow(int player) {
		
		int times = 0;
		
		// Check for 2 consecutive color in a row, horizontally.
		for (int i = 5; i >= 0; i--) {
			for (int j = 0; j < 7; j++) {
				if (canMove(i, j + 1)) {
					if (gameBoard[i][j] == gameBoard[i][j + 1]
							&& gameBoard[i][j] == player) {
						times++;
					}
				}
			}
		}

		// Check for 2 consecutive color in a row, vertically.
		for (int i = 5; i >= 0; i--) {
			for (int j = 0; j < 7; j++) {
				if (canMove(i - 1, j)) {
					if (gameBoard[i][j] == gameBoard[i - 1][j]
							&& gameBoard[i][j] == player) {
						times++;
					}
				}
			}
		}

		// Check for 2 consecutive color in a row, in descending diagonal.
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if (canMove(i + 1, j + 1)) {
					if (gameBoard[i][j] == gameBoard[i + 1][j + 1]
							&& gameBoard[i][j] == player) {
						times++;
					}
				}
			}
		}

		// Check for 2 consecutive color in a row, in ascending diagonal.
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if (canMove(i - 1, j + 1)) {
					if (gameBoard[i][j] == gameBoard[i - 1][j + 1]
							&& gameBoard[i][j] == player) {
						times++;
					}
				}
			}
		}

		return times;
				
	}


}
