package gui;

import java.io.IOException;

public class Launcher {
	
	@SuppressWarnings("static-access")
	public static void main(String [] args) throws IOException {
		UserInterface connect4UI = new UserInterface();
		connect4UI.createNewGame();
	}

}
