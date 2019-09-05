package gui;


public class GameParameters {
	
	
	public static int maxDepth = 4;
	public static int player1Color = Constants.RED;
	public static int player2Color = Constants.BLACK;
	
	
	public static final String getColorNameByNumber(int number) {
		switch (number) {
			case 1:
				return "RED";
			case 2:
				return "BLACK";
			default:
				return "RED";
		}
	}
	
	
}
