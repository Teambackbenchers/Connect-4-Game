package gui;

import java.io.IOException;

public class Launcher {
	
	public static void main(String [] args) throws IOException {
		TestBoard panel = new TestBoard();  
	    panel.setResizable(false);
	    panel.setVisible(true);
	}

}
