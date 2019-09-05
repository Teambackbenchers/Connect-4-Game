package gui;

import java.util.ArrayList;
import java.util.Random;

public class MiniMax {
	
	private int maxDepth;
	
	private int aiLetter;

	
	public MiniMax() {
		maxDepth = 4;
		aiLetter = Constants.O;
	}
		
	public MiniMax(int maxDepth, int aiLetter) {
		this.maxDepth = maxDepth;
		this.aiLetter = aiLetter;
	}

	public int getMaxDepth() {
		return maxDepth;
	}

	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}

	public int getAiLetter() {
		return aiLetter;
	}

	public void setAiLetter(int aiLetter) {
		this.aiLetter = aiLetter;
	}

	public Move miniMax(Board board) {
	        if (aiLetter == Constants.X) {
	            return max(new Board(board), 0);
	        }
	        else {
	            return min(new Board(board), 0);
	        }
		}
	public Move max(Board board, int depth) {	       
		Random r = new Random();

		if((board.checkGameOver()) || (depth == maxDepth)) {
			Move lastMove = new Move(board.getLastMove().getRow(), board.getLastMove().getCol(), board.evaluate());
			return lastMove;
		}
        
		ArrayList<Board> children = new ArrayList<Board>(board.getChildren(Constants.X));
		Move maxMove = new Move(Integer.MIN_VALUE);
		for (Board child : children) {
			Move move = min(child, depth + 1);
			if(move.getValue() >= maxMove.getValue()) {
                if ((move.getValue() == maxMove.getValue())) {
                    if (r.nextInt(2) == 0) {
                        maxMove.setRow(child.getLastMove().getRow());
                        maxMove.setCol(child.getLastMove().getCol());
                        maxMove.setValue(move.getValue());
                    }
                }
                else {
                    maxMove.setRow(child.getLastMove().getRow());
                    maxMove.setCol(child.getLastMove().getCol());
                    maxMove.setValue(move.getValue());
                }
			}
		}
		return maxMove;
	}

	public Move min(Board board, int depth) {
        Random r = new Random();

		if((board.checkGameOver()) || (depth == maxDepth)) {
			Move lastMove = new Move(board.getLastMove().getRow(), board.getLastMove().getCol(), board.evaluate());
			return lastMove;
		}
		ArrayList<Board> children = new ArrayList<Board>(board.getChildren(Constants.O));
		Move minMove = new Move(Integer.MAX_VALUE);
		for (Board child : children) {
			Move move = max(child, depth + 1);
			if(move.getValue() <= minMove.getValue()) {
                if ((move.getValue() == minMove.getValue())) {
                    if (r.nextInt(2) == 0) {
                        minMove.setRow(child.getLastMove().getRow());
                        minMove.setCol(child.getLastMove().getCol());
                        minMove.setValue(move.getValue());
                    }
                }
                else {
                        minMove.setRow(child.getLastMove().getRow());
                        minMove.setCol(child.getLastMove().getCol());
                        minMove.setValue(move.getValue());
                }
            }
        }
        return minMove;
	}
	
}
