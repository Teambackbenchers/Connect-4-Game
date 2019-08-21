package gui;

import java.util.ArrayList;
import java.util.Random;

public class MiniMax {

		int maxDepth=3;
		int aiPlayer=0;
		//int aiPlayer_2=1;
		
		public MiniMax(int maxDepth,int aiPlayer_1)
		{
			this.maxDepth=maxDepth;
			this.aiPlayer=aiPlayer_1;
		//	this.aiPlayer_2=aiPlayer_2;
		}

		public int getMaxDepth() {
			return maxDepth;
		}

		public void setMaxDepth(int maxDepth) {
			this.maxDepth = maxDepth;
		}

		public int getAiPlayer_1() {
			return aiPlayer;
		}

		public void setAiPlayer_1(int aiPlayer_1) {
			this.aiPlayer = aiPlayer_1;
		}

		
		//Initialize the miniMax algorithm
		
		public Move miniMax(ConsoleBoard board)
		{
			if(aiPlayer==1) {
				return max(new ConsoleBoard(board), 0); // 0 is the exit point of minimax alogorithm
			}
			else
				return min(new ConsoleBoard(board),0);
			
		}

		private Move min(ConsoleBoard board, int depth) {
			// TODO Auto-generated method stub
			
			int player_2=1;
			ArrayList<ConsoleBoard> children = new ArrayList<ConsoleBoard>(board.getChild(player_2));
			Random random = new Random();
			if(board.checkGameOver()||depth==maxDepth)
			{
				Move lastMove = new Move(board.getLastMove().getRow(), board.getLastMove().getCol(), board.evaluate());
				return lastMove;
			}
			Move minMove= new Move(999999999);
			
			for(ConsoleBoard successor:children)
			{
				Move move = max(successor, depth + 1);
				if(move.getValue()>=minMove.getValue())
				{
					if(move.getValue()==minMove.getValue())
					{
						if (random.nextInt(2) == 0) {
							minMove.setRow(successor.getLastMove().getRow());
							minMove.setCol(successor.getLastMove().getCol());
							minMove.setValue(move.getValue());
	                    }
						
						else
						{
							minMove.setRow(successor.getLastMove().getRow());
							minMove.setCol(successor.getLastMove().getCol());
							minMove.setValue(move.getValue());	
						}
					}
				}
			}
			return minMove;
			
		}

		private Move max(ConsoleBoard board, int depth) {
			// TODO Auto-generated method stub
			int player_1=0;
			ArrayList<ConsoleBoard> children = new ArrayList<ConsoleBoard>(board.getChild(player_1));
			Random random = new Random();
			if(board.checkGameOver()||depth==maxDepth)
			{
				Move lastMove = new Move(board.getLastMove().getRow(), board.getLastMove().getCol(), board.evaluate());
				return lastMove;
			}
			Move maxMove= new Move(-999999999);
			
			for(ConsoleBoard successor:children)
			{
				Move move = min(successor, depth + 1);
				if(move.getValue()>=maxMove.getValue())
				{
					if(move.getValue()==maxMove.getValue())
					{
						if (random.nextInt(2) == 0) {
	                        maxMove.setRow(successor.getLastMove().getRow());
	                        maxMove.setCol(successor.getLastMove().getCol());
	                        maxMove.setValue(move.getValue());
	                    }
						
						else
						{
							  maxMove.setRow(successor.getLastMove().getRow());
		                      maxMove.setCol(successor.getLastMove().getCol());
		                      maxMove.setValue(move.getValue());	
						}
					}
				}
			}
			return maxMove;
		}
}
